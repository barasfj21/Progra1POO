package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vista.AsignarRequisitoCurso;
import modelo.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

/**
 *Controlador de la ventana AsignarRequisitoCurso
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 * 
 */
public class AsignarRequisitoCursoControlador implements EventHandler<ActionEvent> {
  AsignarRequisitoCurso ventana;
  
  /**
   * 
   * @param pVentana Ventana controlada
   */
  public AsignarRequisitoCursoControlador(AsignarRequisitoCurso pVentana) {
    ventana = pVentana;
  }
  
  /**
   * 
   * @return pCodigoEscuela
   */
  private String obtenerCodigoEscuela() {
    String pNombreEscuela = (String) ventana.comboBoxPropietario.getValue();
    String pCodigoEscuela = "";
    try {
      Conexion.connect();
      String queryCodigoEscuela = "Select codigoEscuela from Escuela where nombreEscuela='"+pNombreEscuela+"'";
      //Se toma el codigo de las escuelas que coinciden con el nombre de la escuela extraido por el combobox.
      ResultSet resultado = Conexion.getSelectData(queryCodigoEscuela);
      resultado.next();
      //Se extrae especificamente el codigo de la escuela
      pCodigoEscuela = resultado.getString(1);
      Conexion.closeConnection();
    } catch(SQLException ex){
      Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
    return pCodigoEscuela;
  }
  
  /**
   * 
   * @param comboBoxCodigoCurso combobox de los codigos de los cursos requisitos y correquisitos
   */
  private void codigoCursosRequisitosCorrequisitosComboBox(ComboBox<String> comboBoxCodigoCurso) {
    try{
      String pCodigoEscuela = obtenerCodigoEscuela();
      String pCodigoCurso = (String) ventana.comboBoxCodigoCurso.getValue();
      Conexion.connect();
      String query = "select codigoCurso from Curso";
      //Se extraen todos los codigos de los cursos
      ResultSet resultado = Conexion.getSelectData(query);
      while(resultado.next()) {
        String codigoEscuelaCompleto = resultado.getString(1); 
        //Se hace un split para separar el String 
        String letrasCodigoEscuela = codigoEscuelaCompleto.split("-")[0];
        //Se valida que las letras del codigo de la escuela sean iguales a las del curso
        if(pCodigoEscuela.equals(letrasCodigoEscuela)) {
            if(!(codigoEscuelaCompleto.equals(pCodigoCurso))) {
              comboBoxCodigoCurso.getItems().add(codigoEscuelaCompleto);
            }
        }
      }
      Conexion.closeConnection();
    } catch(SQLException ex){
        Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * 
   * @param pCodigoCurso
   * @param pCodigoRequisitoCorrequisito
   * @param identificador
   * @return bandera
   * @throws SQLException 
   */
  private boolean requisitoCorrequisitoExistente(String pCodigoCurso, String pCodigoRequisitoCorrequisito, int identificador) throws SQLException{
    String query;
    //Se determina si es requisito o correquisito dependiendo de la variable bandera.
    if (identificador == 1) {
      query = "select * from Curso_Requisito"; 
    } else {
      query = "select * from Curso_Correquisito";
    }
    Conexion.connect();
    //Se extraen los los codigos de los cursos requisitos o correquisitos.
    ResultSet resultado = Conexion.getSelectData(query);
    boolean bandera = true;
    //Ciclo que valida si el curso ya existe en la base de datos.
    while(resultado.next()){
      if(((pCodigoCurso.equals(resultado.getString(1))) &&
          pCodigoRequisitoCorrequisito.equals(resultado.getString(2)))
          || ((pCodigoCurso.equals(resultado.getString(2))) &&
          (pCodigoRequisitoCorrequisito.equals(resultado.getString(1))))) {
        bandera = false; 
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setContentText("El curso ya existe. Favor ingresar otro");
        alert.showAndWait();
      }
    }
      return bandera;
  }
  
  /**
   * 
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
    if (t.getSource() == ventana.comboBoxPropietario) {
      //Se limpian todos los comboboxes antes de empezar a operar.  
      ventana.comboBoxCodigoCurso.getItems().removeAll(ventana.comboBoxCodigoCurso.getItems());
      ventana.comboBoxCodigoCursoRequisito.getItems().removeAll(ventana.comboBoxCodigoCursoRequisito.getItems());
      ventana.comboBoxCodigoCursoCorrequisito.getItems().removeAll(ventana.comboBoxCodigoCursoCorrequisito.getItems());
      try{
        //Se extrae el codigo de la escuela del combobox llamado propietario.  
        String pCodigoEscuela = obtenerCodigoEscuela();
        Conexion.connect();
        String query = "select codigoCurso from Curso";
        ResultSet resultado = Conexion.getSelectData(query);
        //Variables que construyen el codigo completo del curso
        String codigoEscuelaFinal;
        String letrasCodigoEscuela;
        while(resultado.next()) {
          codigoEscuelaFinal = resultado.getString(1); 
          //Se extraen las letras del codigo del curso por medio de un split
          letrasCodigoEscuela = codigoEscuelaFinal.split("-")[0];
          //Se valida que las letras del codigo de la escuela sean iguales a las del curso
          if(pCodigoEscuela.equals(letrasCodigoEscuela)) {
            ventana.comboBoxCodigoCurso.getItems().add(codigoEscuelaFinal);
          }
        }
        Conexion.closeConnection();
      } catch(SQLException ex){
        Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    if(t.getSource() == ventana.comboBoxCodigoCurso) {
      //Se limpian todos los comboboxes antes de empezar a operar. 
      ventana.comboBoxCodigoCursoRequisito.getItems().removeAll(ventana.comboBoxCodigoCursoRequisito.getItems());
      ventana.comboBoxCodigoCursoCorrequisito.getItems().removeAll(ventana.comboBoxCodigoCursoCorrequisito.getItems());
      //Se meten los codigos de los cursos en los comboboxes.
      codigoCursosRequisitosCorrequisitosComboBox(ventana.comboBoxCodigoCursoRequisito);
      codigoCursosRequisitosCorrequisitosComboBox(ventana.comboBoxCodigoCursoCorrequisito);
    }
    
    if (t.getSource() == ventana.btnRegistrarRequisito) {
      //Se valida que haya datos en los combobox
      if ((ventana.comboBoxPropietario.getSelectionModel().selectedItemProperty().getValue() != null)
        && (ventana.comboBoxCodigoCurso.getSelectionModel().selectedItemProperty().getValue() != null) 
        && (ventana.comboBoxCodigoCursoRequisito.getSelectionModel().selectedItemProperty().getValue() != null)) {
        //Se extrae los codigos de los comboboxes.
        String pCodigoCurso = (String) ventana.comboBoxCodigoCurso.getValue();
        String pCodigoCursoRequisito = (String) ventana.comboBoxCodigoCursoRequisito.getValue();
        String query = "insert into Curso_Requisito (codigoCurso, codigoCursoRequisito)"
              + "values('"+pCodigoCurso+"','"+pCodigoCursoRequisito+"')";
        try {
          //Variable que determina si se insertara un requsito o correquisito.   
          boolean indicador = requisitoCorrequisitoExistente(pCodigoCurso, pCodigoCursoRequisito, 1);
          if (indicador != false) {
            Conexion.executeQuery(query);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setContentText("Registro del requisito completado");
            alert.showAndWait();
          }
        } catch(SQLException ex){
          Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
      } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Por favor, llene los espacios correspondientes");
        alert.showAndWait();
      }
    }
    
    if (t.getSource() == ventana.btnRegistrarCorrequisito) {
      //Se valida que haya datos en los combobox  
      if ((ventana.comboBoxPropietario.getSelectionModel().selectedItemProperty().getValue() != null)
        && (ventana.comboBoxCodigoCurso.getSelectionModel().selectedItemProperty().getValue() != null) 
        && (ventana.comboBoxCodigoCursoCorrequisito.getSelectionModel().selectedItemProperty().getValue() != null)) {
        //Se extraen los datos de los combobox
        String pCodigoCurso = (String) ventana.comboBoxCodigoCurso.getValue();
        String pCodigoCursoCorrequisito = (String) ventana.comboBoxCodigoCursoCorrequisito.getValue();
        String query = "insert into Curso_Correquisito (codigoCurso, codigoCursoCorrequisito)"
              + "values('"+pCodigoCurso+"','"+pCodigoCursoCorrequisito+"')";
        try {
          //Variable que determina si se insertara un requsito o correquisito.    
          boolean indicador = requisitoCorrequisitoExistente(pCodigoCurso, pCodigoCursoCorrequisito, 2);
          if (indicador != false) {
            Conexion.executeQuery(query);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setContentText("Registro del correquisito completado");
            alert.showAndWait();
          }
        } catch(SQLException ex){
          Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
      } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Por favor, llene los espacios correspondientes");
        alert.showAndWait();
      }  
    }
  }
  
  
}

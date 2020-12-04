package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vista.RegistrarPlanEstudio;
import modelo.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

/**
 *
 * Controlador de la ventana RegistrarPlanEstudio
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */

public class RegistrarPlanEstudioControlador implements EventHandler<ActionEvent> {
  RegistrarPlanEstudio ventana;
  
  /**
   * 
   * @param pVentana Ventana controlada
   */
  public RegistrarPlanEstudioControlador(RegistrarPlanEstudio pVentana) {
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
      String queryCodigoEscuela = "select codigoEscuela from Escuela where nombreEscuela='"+pNombreEscuela+"'";
      ResultSet resultado = Conexion.getSelectData(queryCodigoEscuela);
      resultado.next();
      //Se obtiene el codigo de la escuela por medio del select
      pCodigoEscuela = resultado.getString(1);
      Conexion.closeConnection();
    } catch(SQLException ex){
      Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
    return pCodigoEscuela;
  }
  /**
   * Metodo para registrar un nuevo plan de estudio
   */
  private void registrarPlanDeEstudio() {
    //Se obtienen los valores que ingreso el usuario  
    String pNombreEscuela = (String) ventana.comboBoxPropietario.getValue();
    String pCodigoPlanEstudio = ventana.textFieldCodigoPlan.getText(); 
      
    //Se valida que el textfield no este vacio.
    if((ventana.comboBoxPropietario.getSelectionModel().selectedItemProperty().getValue() != null) && 
       (pNombreEscuela != null && !pNombreEscuela.isEmpty()) &&
       (pCodigoPlanEstudio != null && !pCodigoPlanEstudio.isEmpty()) &&
       (ventana.vigencia.getValue() != null)) {
      try {
        //Se valida que el codigo sea una variable de tipo int
        Integer pCodigo = Integer.parseInt(pCodigoPlanEstudio);
              
        //Se valida que el codigo sea de cuatro digitos.
        if(Integer.toString(pCodigo).length() == 4){
          String pFechaVigencia = (ventana.vigencia.getValue()).toString();
          String query = "select * from PlanDeEstudio";
            
          //Se valida que el curso no este registrado en la base de datos.
          try{
            Conexion.connect();
            ResultSet resultado = Conexion.getSelectData(query);
            boolean bandera = true;

            while(resultado.next()){
              if(pCodigoPlanEstudio.equals(resultado.getString("numeroPlanEstudio"))){ 
                bandera = false; 
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Aviso");
                alert.setContentText("El plan de estudios ya existe. Favor ingresar otro");
                alert.showAndWait();
              }
            }
              
            //Variable que permita registrar el curso en caso que no exista en la base.
            if (bandera != false){
              String queryInsertPlanDeEstudio = "Insert into PlanDeEstudio(numeroPlanEstudio, "
                       + "vigencia) "+ "values("+pCodigo+",'"+pFechaVigencia+"')";
              Conexion.executeQuery(queryInsertPlanDeEstudio);
               
              registrarEscuela_PlanEstudio(pCodigo);
               
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Aviso");
              alert.setContentText("Plan de estudio registrado exitosamente");
              alert.showAndWait();
            }
          }catch(SQLException ex){
            Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
          }
          } else {
              Alert alert = new Alert(AlertType.ERROR);
              alert.setTitle("ERROR");
              alert.setContentText("Por favor ingrese un codigo de 4 digitos");
              alert.showAndWait();
          }
        }catch(NumberFormatException e){
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("ERROR");
          alert.setContentText("Por favor, ingrese numeros enteros en el codigo");
          alert.showAndWait();
        }
        
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Por favor, llene los espacios correspondientes");
        alert.showAndWait();
      }
  }
  /**
   * 
   * @param pCodigoPlanEstudios codigo de plan de estudios
   */
  private void registrarEscuela_PlanEstudio(int pCodigoPlanEstudios) {
    try {
      String pCodigoEscuela = obtenerCodigoEscuela();
      String query = "INSERT INTO Escuela_PlanEstudio(codigoEscuela, "
                + "numeroPlanEstudio) "+ "VALUES('"+pCodigoEscuela+"',"+pCodigoPlanEstudios+");";
      Conexion.executeQuery(query);
    } catch(SQLException ex){
        Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * 
   * @param comboBoxCodigoCurso combobox de los codigos de los cursos
   */
  private void codigoCursosComboBox(ComboBox<String> comboBoxCodigoCurso) {
    try{
      String pCodigoEscuela = obtenerCodigoEscuela();
      Conexion.connect();
      String query = "select codigoCurso from Curso";
      ResultSet resultado = Conexion.getSelectData(query);
      while(resultado.next()) {
        //Se realiza un ciclo que rellene el combobox 
        String codigoEscuelaCompleto = resultado.getString(1); 
        //Se separa el codigo de la escuela entre letras y numeros
        String letrasCodigoEscuela = codigoEscuelaCompleto.split("-")[0];
        //Se valida que el las letras sean iguales al codigo de la escuela.
        if(pCodigoEscuela.equals(letrasCodigoEscuela)) {
            comboBoxCodigoCurso.getItems().add(codigoEscuelaCompleto);
        }
      }
      Conexion.closeConnection();
    } catch(SQLException ex){
        Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * 
   * @param comboBoxPlanEscuela combobox de los codigos de plan de estudios asignados a una escuela
   */
  private void codigoPlanEstudiosEscuela(ComboBox<String> comboBoxPlanEscuela) {
    try {
      String pCodigoEscuela = obtenerCodigoEscuela();
      Conexion.connect();
      String query = "Select numeroPlanEstudio from Escuela_PlanEstudio where codigoEscuela = '"+pCodigoEscuela+"'";
      ResultSet resultado = Conexion.getSelectData(query);
      while(resultado.next()) {
        //Ciclo para rellenar los comboboxes  
        String pCodigoPlanEscuela = resultado.getString(1);
        comboBoxPlanEscuela.getItems().add(pCodigoPlanEscuela);
      }
    } catch(SQLException ex){
        Logger.getLogger(RegistrarPlanEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /**
   * Metodo para registrar un curso al plan de estudio
   */
  private void registrarCursoPlanEstudio() {
    //Se valida que los comboboxes tengan informacion  
    if((ventana.comboBoxPlanEscuela.getSelectionModel().selectedItemProperty().getValue() != null) 
      && (ventana.comboBoxCodigoCurso.getSelectionModel().selectedItemProperty().getValue() != null)
      && (ventana.comboBoxBloque.getSelectionModel().selectedItemProperty().getValue() != null)) {
      int pNumeroPlanEstudio = Integer.parseInt(ventana.comboBoxPlanEscuela.getValue().toString());
      String pCodigoCurso = (String) ventana.comboBoxCodigoCurso.getValue();
      String pBloque = (String) ventana.comboBoxBloque.getValue();
      String query = "insert into PlanEstudio_Curso(numeroPlanEstudio, codigoCurso) values ("
              + pNumeroPlanEstudio + ", '"+pCodigoCurso+"')";
      String queryBloque = "update Curso set bloque = '"+pBloque+"' where codigoCurso = '"+pCodigoCurso+"'";
      try {
        boolean bandera = cursoExistentePlanEstudio(pNumeroPlanEstudio, pCodigoCurso);
        if(bandera!=false) {
          Conexion.executeQuery(query);
          Conexion.executeQuery(queryBloque);
          
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Aviso");
          alert.setContentText("Registro de curso al plan de estudios completado");
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
  
  /**
   * 
   * @param pNumeroPlanEstudio
   * @param pCodigoCurso
   * @return bandera
   * @throws SQLException 
   */
  private boolean cursoExistentePlanEstudio(int pNumeroPlanEstudio, String pCodigoCurso) throws SQLException{
    String query = "select * from PlanEstudio_Curso";
    Conexion.connect();
    ResultSet resultado = Conexion.getSelectData(query);
    boolean bandera = true;

    while(resultado.next()){
      if((pCodigoCurso.equals(resultado.getString(2))) &&
            pNumeroPlanEstudio == resultado.getInt(1)){
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
   * Metodo rellenar comboBox y limpiarlos con los datos de la base.
   */
  private void rellenarCombobox() {
    ventana.comboBoxCodigoCurso.getItems().removeAll(ventana.comboBoxCodigoCurso.getItems());
    ventana.comboBoxPlanEscuela.getItems().removeAll(ventana.comboBoxPlanEscuela.getItems());
    codigoCursosComboBox(ventana.comboBoxCodigoCurso);
    codigoPlanEstudiosEscuela(ventana.comboBoxPlanEscuela);
  }
  
  /**
   * 
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
    if (t.getSource() == ventana.btnRegistrarPlanEstudio) {
      registrarPlanDeEstudio();
      rellenarCombobox();
    }
    
    if (t.getSource() == ventana.comboBoxPropietario) {
      rellenarCombobox();
    }
    
    if (t.getSource() == ventana.btnRegistrarCurso) {
      registrarCursoPlanEstudio();
    }
    
    if (t.getSource() == ventana.btnRegresar) {
      ventana.stage.close();
    }
  }
    
    
}
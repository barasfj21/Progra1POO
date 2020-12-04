package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vista.RegistroCurso;
import modelo.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * Es el controlador de la clase RegistroCurso
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class RegistroCursoControlador implements EventHandler<ActionEvent> {
  RegistroCurso ventana;
  
  /**
   * 
   * @param pVentana Ventana controlada
   */
  public RegistroCursoControlador(RegistroCurso pVentana) {
    ventana = pVentana;
  }
  
  /**
   * Permite actualizar el label de la primera parte del codigo
   * del curso, con respecto el usuario va escogiendo de escuela.
   * @param comboBoxEscuelas combobox de las escuelas
   */
  private void actualizarCodigo(ComboBox<String> comboBoxEscuelas, Label codigo) throws SQLException{
    //Se extrae la escuela del combobox
    String escuelaComboBox = comboBoxEscuelas.getValue();
    //Se conecta a la base
    Conexion.connect();
    String query = "Select codigoEscuela from Escuela where nombreEscuela='"+escuelaComboBox+"'";
    //Se extrae la tabla resultante del select
    ResultSet resultado = Conexion.getSelectData(query);
    //Se toma el codigo de la escuela de la tabla resultante
    while (resultado.next()){
      String escuela = resultado.getString(1);
      //Se actualiza el label del codigo dependiendo de la escuela
      codigo.setText(escuela);

    }
  }
  
  /**
   * 
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
      
    if (t.getSource()== ventana.comboBoxEscuela) {
      try {
        //Se actualiza el codigo de la escuela
        actualizarCodigo(ventana.comboBoxEscuela,ventana.lblTextoCodigo);
      } catch (SQLException ex) {
        Logger.getLogger(RegistroCursoControlador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
      
    if (t.getSource()== ventana.btnRegistrar) {
      String pNombreCurso = ventana.textFieldNombre.getText();
      String pCodigoCurso = ventana.textFieldCodigo.getText();
      
      //validando que los textfields no esten vacios
      if((pNombreCurso != null && !pNombreCurso.isEmpty()) &&
            (pCodigoCurso != null && !pCodigoCurso.isEmpty())){
          
        //validando que el nombre sea un String
        if (pNombreCurso instanceof String) {
            
          try {
            //validando que el codigo sea un integer
            Integer pCodigo = Integer.parseInt(pCodigoCurso);
              
            //validando que el codigo tenga 4 digitos exclusivamente
            if(Integer.toString(pCodigo).length() == 4) {

              String codigoCompleto = ventana.lblTextoCodigo.getText()+"-"+Integer.toString(pCodigo);
              String escuela = (String) ventana.comboBoxEscuela.getValue();
              int creditos = (int) ventana.comboBoxCreditos.getValue();
              int horasLectivas = (int) ventana.comboBoxHoras.getValue();
              String query = "Select * from Curso";

              //validando que el curso no exista ya
              try {
                Conexion.connect();
                ResultSet resultado = Conexion.getSelectData(query);
                boolean bandera = true;

                while(resultado.next()){
                  if(codigoCompleto.equals(resultado.getString("codigoCurso"))){ 
                    bandera = false; 
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Aviso");
                    alert.setContentText("El curso ya existe. Favor ingresar otro");
                    alert.showAndWait();
                  }
                }

                if (bandera != false){
                  String queryInsert = "Insert into Curso(codigoCurso, "
                    + "nombreCurso, cantidadCreditos, cantidadHorasLectivas) "+
                    "values('"+codigoCompleto+"','"+pNombreCurso+
                    "',"+creditos+","+horasLectivas+")";  
                  Conexion.executeQuery(queryInsert);

                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Aviso");
                  alert.setContentText("Curso registrado exitosamente");
                  alert.showAndWait();
                }
              } catch(SQLException ex) {
                Logger.getLogger(RegistroCursoControlador.class.getName()).log(Level.SEVERE, null, ex);
              }

            } else {
              //no es un numero de 4 digitos
              Alert alert = new Alert(AlertType.ERROR);
              alert.setTitle("ERROR");
              alert.setContentText("Por favor ingrese un codigo de 4 digitos");
              alert.showAndWait();
            }
              
          } catch(NumberFormatException e) {
            //no es int
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Por favor, ingrese numeros enteros en el codigo");
            alert.showAndWait();
          }
        } else {
            //nombre no es string
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Por favor, escriba un nombre valid"
                    + "para el curso");
            alert.showAndWait();
        }
      }else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Por favor, llene los espacios correspondientes");
        alert.showAndWait();
      }
    }
    
    if (t.getSource()== ventana.btnLimpiar) {
      ventana.comboBoxEscuela.setPromptText("Escoja una escuela");
      ventana.textFieldNombre.setText("");
      ventana.textFieldCodigo.setText("");
      ventana.lblTextoCodigo.setText("---");
      ventana.comboBoxCreditos.getSelectionModel().selectFirst();
      ventana.comboBoxHoras.getSelectionModel().selectFirst();
      ventana.comboBoxEscuela.getItems().removeAll(ventana.comboBoxEscuela.getItems());
      try {
        ventana.escuelasComboBox(ventana.comboBoxEscuela);
      } catch (SQLException ex) {
        Logger.getLogger(RegistroCursoControlador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
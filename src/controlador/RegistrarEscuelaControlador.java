package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vista.RegistroEscuela;
import modelo.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Es el controlador de la clase RegistroEscuela
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class RegistrarEscuelaControlador implements EventHandler<ActionEvent> {
  RegistroEscuela ventana;
  
  /**
   * 
   * @param pVentana Ventana controlada
   */
  public RegistrarEscuelaControlador(RegistroEscuela pVentana) {
    ventana = pVentana;
  }
  
  /**
   * 
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
    //Se extraen los datos que el usuario ingreso en los textfields
    String pCodigoEscuela = ventana.textFieldCodigo.getText();
    String pNombreEscuela = ventana.textFieldNombre.getText();
    
    if(t.getSource()== ventana.btnRegistrar) {
      //Se valida que el usuario haya ingresado datos en los textfields.
      if ((pCodigoEscuela != null && !pCodigoEscuela.isEmpty()) &&
      (pNombreEscuela != null && !pNombreEscuela.isEmpty())) {
        String querySelect = "select * from Escuela";
        try {
          Conexion.connect();
          
          ResultSet result = Conexion.getSelectData(querySelect);
          //Variable bandera que define si el codigo de la escuela
          //ya se encuentra en la base
          boolean indicador = true;
          //ciclo para verificar que el codigo de la escuela no este registrado.
          while (result.next()) {
            //Se valida que el codigo de la escuela no exista en la base  
            if (pCodigoEscuela.equals(result.getString("codigoEscuela"))) {
              indicador = false;
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Aviso");
              alert.setContentText("El codigo ingresado ya existe, por favor"
                 + "ingrese otro.");
              alert.showAndWait();
            } 
            Conexion.closeConnection();
            }
          if (indicador != false) {
            //Si es diferente de false, no esta en la base, entonces se inserta.    
            String queryInsert = "Insert into Escuela(codigoEscuela,"
              + "nombreEscuela) values('"+pCodigoEscuela+"','"+pNombreEscuela+"')"; 
            Conexion.connect();
            Conexion.executeQuery(queryInsert);
            Conexion.closeConnection();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Aviso");
            alert.setContentText("Escuela registrada exitosamente");
            alert.showAndWait();
            }

        } catch (SQLException ex) {
          Logger.getLogger(RegistrarEscuelaControlador.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          
      } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Por favor ingrese datos en los campos de texto.");
        alert.showAndWait();
        }    
    } 
    
    if(t.getSource()== ventana.btnLimpiar) {
      
      ventana.textFieldCodigo.setText("");
      ventana.textFieldNombre.setText("");
    }
    
  }
}

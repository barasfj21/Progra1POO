package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import modelo.Conexion;
import vista.EliminarCurso;

/**
 * Es el controlador de la clase EliminarCurso
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class EliminarCursoControlador implements EventHandler<ActionEvent> {
  EliminarCurso ventana;
    
  /**
   * 
   * @param pVentana Ventana controlada
   */
  public EliminarCursoControlador(EliminarCurso pVentana) {
    ventana = pVentana;
  }
    
  /**
   * 
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
      
    if (t.getSource() == ventana.btnEliminar) { 
      String pCodigoCurso = ventana.textFieldCodigo.getText();
      
      //validando que el codigo del curso no este vacio
      if (pCodigoCurso != null && !pCodigoCurso.isEmpty()) {
          //validando que el codigo del curso sea un String 
        if(pCodigoCurso instanceof String) {
           
          String querySelect = "Select * from Curso";
            
          try{
            Conexion.connect();
            ResultSet resultado = Conexion.getSelectData(querySelect);
            //Variable que valida si el curso a eliminar existe
            boolean bandera = false;
                    
            while(resultado.next()) {
              if(pCodigoCurso.equals(resultado.getString("codigoCurso"))){ 
                bandera = true;
                Conexion.closeConnection();
                //Se ejecuta la eliminacion tomando ocmo parametro el codigo del textfield
                String queryDelete = "Delete from Curso where codigoCurso='"+pCodigoCurso+"'";
                Conexion.executeQuery(queryDelete);
                        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aviso");
                alert.setContentText("Curso eliminado satisfacotoriamente");
                alert.showAndWait();
                //Se limpia el textfield        
                ventana.textFieldCodigo.setText("");
              }
            }
            if (bandera == false) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("ERROR");
              alert.setContentText("ERROR>El curso a borrar no existe");
              alert.showAndWait();
            }
                
          }catch(SQLException ex) {
           Logger.getLogger(EliminarCursoControlador.class.getName()).log(Level.SEVERE, null, ex);
          }
        } else {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("ERROR");
          alert.setContentText("Por favor, ingrese un codigo correcto");
          alert.showAndWait();
        }
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Por favor, ingrese los datos respectivos");
        alert.showAndWait(); 
      }
    }
    
    if(t.getSource()== ventana.btnRegresar){
      ventana.stage.close();
    }
  }
}

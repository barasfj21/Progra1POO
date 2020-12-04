package controlador;

import vista.ConsultarEliminarCursoPlanEstudios;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import modelo.Conexion;
import modelo.PlanDeEstudio;

/**
 * Es el controlador de la clase ConsultarEliminarCursoPlanEstudios
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class ConsultarEliminarCursoPlanEstudiosController implements EventHandler<ActionEvent>{
  ConsultarEliminarCursoPlanEstudios ventana;
  
  /**
   *
   * @param pVentana Ventana controlada
   */
  public ConsultarEliminarCursoPlanEstudiosController(ConsultarEliminarCursoPlanEstudios pVentana){
    ventana=pVentana;
  }
  
  /**
   *
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
    if(t.getSource()==ventana.btnEliminar){
      //Variable que almacena el objeto que el usuario selecciono en la tabla.
      PlanDeEstudio planEstudio = ventana.tblPlanes.getSelectionModel().getSelectedItem();
      String query = "delete from PlanEstudio_Curso where codigoCurso='"+ventana.codigoCurso+"' "
            + "and numeroPlanEstudio="+planEstudio.getNumeroPlanEstudio();
      try {
        Conexion.executeQuery(query);
        //Elimina el objeto en la tabla que se elimino de la base.
        ventana.tblPlanes.getItems().remove(planEstudio);
      } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Ocurrió un error mientras se intentaba eliminar el curso del plan");
        alert.showAndWait();
      }
    }
    
    if(t.getSource()==ventana.btnRegresar){
      ventana.stage.close();
    }
    
   
    if(t.getSource()==ventana.btnConsultarPlanes){
      ventana.codigoCurso = ventana.textFieldCodigo.getText();
      //Se limpia la tabla antes de empezar a utilizarla.
      ventana.tblPlanes.getItems().clear();
      String query1 = "select PlanEstudio_Curso.numeroPlanEstudio,Escuela.nombreEscuela,PlanDeEstudio.vigencia"
              + " from PlanEstudio_Curso inner join PlanDeEstudio on PlanEstudio_Curso.numeroPlanEstudio="
              + "PlanDeEstudio.numeroPlanEstudio inner join Escuela_PlanEstudio on PlanDeEstudio.numeroPlanEstudio="
              + "Escuela_PlanEstudio.numeroPlanEstudio inner join Escuela on Escuela_PlanEstudio.codigoEscuela="
              + "Escuela.codigoEscuela where PlanEstudio_Curso.codigoCurso = '"+ventana.codigoCurso+"'";
      try {
        Conexion.connect();
        ResultSet resultado = Conexion.getSelectData(query1);
        //Ciclo para extraer datos
        while(resultado.next()) {
          //Se extraen los datos que se incertaran en la tabla  
          int numeroPlanEstudio = resultado.getInt(1);
          String nombreEscuela = resultado.getString(2);
          String vigencia = resultado.getString(3);
          //Construye nuevo query de consulta sobre cantidadCursos y creditosTotales
          String queryCantidadCreditosTotales = "select COUNT(Curso.codigoCurso) as cantidadCursos,SUM(Curso.cantidadCreditos) as "
                  + "creditosTotales from PlanEstudio_Curso inner join Curso on PlanEstudio_Curso.codigoCurso="
                  + "Curso.codigoCurso where numeroPlanEstudio ="+numeroPlanEstudio;
          ResultSet resultadoCantidadCreditosTotales = Conexion.getSelectData(queryCantidadCreditosTotales);
          int totalCursos = resultadoCantidadCreditosTotales.getInt(1);
          int totalCreditos = resultadoCantidadCreditosTotales.getInt(2);
          //Instancia nuevo objeto con los resultados del query
          PlanDeEstudio planInsertar = new PlanDeEstudio(numeroPlanEstudio,vigencia,nombreEscuela,totalCursos,
              totalCreditos);
          //Se pobla la tabla
          ventana.tblPlanes.getItems().add(planInsertar);
        }
        Conexion.closeConnection();
      } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("ERROR:Ocurrió un error mientras se buscaban los resultados");
        alert.showAndWait();
      }
    }
  }
}
package controlador;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vista.ConsultarEliminarRequisitoCorrequisito;
import modelo.Conexion;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import modelo.Curso;

/**
 * Es el controlador de la clase ConsultarEliminarRequisitoCorrequisito
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class ConsultarEliminarRequisitoCorrequisitoController implements EventHandler<ActionEvent>{
  ConsultarEliminarRequisitoCorrequisito ventana;
    
  /**
   *
   * @param pVentana Ventana controlada
   */
  public ConsultarEliminarRequisitoCorrequisitoController(ConsultarEliminarRequisitoCorrequisito
      pVentana){
    ventana = pVentana;
  }

  /**
   *
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
    if(t.getSource()==ventana.btnEliminar) {
      //Variable que almacena el objeto que el usuario selecciono en la tabla.
      Curso cursoSeleccionado = ventana.tblCursos.getSelectionModel().getSelectedItem();
      String query;
      //Se construye un query depndiendo de la opcion escogida.
      if(ventana.requisito){
        query = "delete from Curso_Requisito where codigoCurso='"+ventana.codigoCurso+"' "
            + "and codigoCursoRequisito='"+cursoSeleccionado.getCodigoCurso()+"'";
      }else{
        query = "delete from Curso_Correquisito where codigoCurso='"+ventana.codigoCurso+"' "
            + "and codigoCursoCorrequisito='"+cursoSeleccionado.getCodigoCurso()+"'";
      }
      try {
        Conexion.executeQuery(query);
        //Elimina el objeto en la tabla que se elimino de la base.
        ventana.tblCursos.getItems().remove(cursoSeleccionado);
      } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Ocurrió un error mientras se intentaba eliminar el curso");
        alert.showAndWait();
      }
    }
    
    if(t.getSource()==ventana.btnRegresar) {
      ventana.stage.close();
    }
    
    if(t.getSource()==ventana.btnConsultarRequisitos) {
      ventana.requisito = true;
      ventana.tblCursos.getItems().clear();
      ventana.codigoCurso = ventana.textFieldCodigo.getText();
      String query = "select codigoCursoRequisito,nombreCurso,cantidadHorasLectivas,"
        + "cantidadCreditos from Curso_Requisito,Curso where Curso_Requisito.codigoCurso="
        + "'"+ventana.codigoCurso+"' and codigoCursoRequisito=Curso.codigoCurso";
      try {
        Conexion.connect();
        ResultSet resultado = Conexion.getSelectData(query);
        while(resultado.next()){
          //Se crea un nuevo objeto de tipo Curso con los resultados del select
          Curso nuevoCurso = new Curso(resultado.getString(1),resultado.getString(2),
              resultado.getInt(3),resultado.getInt(4));
          //Se pobla la tabla con el objeto curso.
          ventana.tblCursos.getItems().add(nuevoCurso);
        }
        Conexion.closeConnection();
      } catch (SQLException ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("ERROR:Ocurrió un error mientras se buscaban los resultados");
        alert.showAndWait();
      }
    }
    
    if(t.getSource()==ventana.btnConsultarCorrequisitos) {
      ventana.requisito = false;
      ventana.tblCursos.getItems().clear();
      ventana.codigoCurso = ventana.textFieldCodigo.getText();
      //Se construye un query para extraer los correquisitos
      String query = "select codigoCursoCorrequisito,nombreCurso,cantidadHorasLectivas,"
        + "cantidadCreditos from Curso_Correquisito,Curso where Curso_Correquisito.codigoCurso="
        + "'"+ventana.codigoCurso+"' and codigoCursoCorrequisito=Curso.codigoCurso";
      try {
        Conexion.connect();
        //Se ejecuta el query
        ResultSet resultado = Conexion.getSelectData(query);
        while(resultado.next()){
          //Se crea un nuevo objeto de tipo Curso con los resultados del select
          Curso nuevoCurso = new Curso(resultado.getString(1),resultado.getString(2),
              resultado.getInt(3),resultado.getInt(4));
          //Se pobla la tabla con el objeto curso.
          ventana.tblCursos.getItems().add(nuevoCurso);
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
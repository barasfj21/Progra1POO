package controlador;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import vista.AsignarRequisitoCurso;
import vista.ConsultarEliminarCursoPlanEstudios;
import vista.ConsultarEliminarRequisitoCorrequisito;
import vista.ConsultarPlan;
import vista.EliminarCurso;
import vista.Principal;
import vista.RegistrarPlanEstudio;
import vista.RegistroCurso;
import vista.RegistroEscuela;

/**
 * Es el controlador de la clase Principal
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class PrincipalControlador implements EventHandler<ActionEvent> {
  Principal ventana;
    
  /**
   * 
   * @param pVentana Ventana controlada
   */
  public PrincipalControlador(Principal pVentana) {
    ventana = pVentana; 
  }
    
  /**
   * 
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
    if(t.getSource() == ventana.btnRegistrarEscuela){
      //Se crea una nueva ventana del tipo de la clase respectiva de vista  
      RegistroEscuela ventanaNueva = new RegistroEscuela();
      //Se crea un instancia stage para abrir la ventana
      Stage stage = new Stage();
      try {
        //Se ejecuta la ventana creada  
        ventanaNueva.start(stage);
      } catch (Exception ex) {
        Logger.getLogger(PrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    if(t.getSource() == ventana.btnRegistrarCurso){
      RegistroCurso ventanaNueva = new RegistroCurso();
      Stage stage = new Stage();
      try {
        ventanaNueva.start(stage);
      } catch (Exception ex) {
        Logger.getLogger(PrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
      }          
    }
    if(t.getSource() == ventana.btnRegistrarPlan){
      RegistrarPlanEstudio ventanaNueva = new RegistrarPlanEstudio();
      Stage stage = new Stage();
      try {
        ventanaNueva.start(stage);
      } catch (Exception ex) {
        Logger.getLogger(PrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
      }         
    }
    if(t.getSource() == ventana.btnConsultarEliminarCursoPlan){
      ConsultarEliminarCursoPlanEstudios ventanaNueva = new ConsultarEliminarCursoPlanEstudios();
      Stage stage = new Stage();
      try {
        ventanaNueva.start(stage);
      } catch (Exception ex) {
        Logger.getLogger(PrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
      }          
    }
    if(t.getSource() == ventana.btnConsultarEliminarCursoReCo){
      ConsultarEliminarRequisitoCorrequisito ventanaNueva = new ConsultarEliminarRequisitoCorrequisito();
      Stage stage = new Stage();
        try {
            ventanaNueva.start(stage);
        } catch (Exception ex) {
            Logger.getLogger(PrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    if(t.getSource() == ventana.btnConsultarPlan){
      ConsultarPlan ventanaNueva = new ConsultarPlan();
      Stage stage = new Stage();
      try {
        ventanaNueva.start(stage);
      } catch (Exception ex) {
        Logger.getLogger(PrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
      }          
    }
    if(t.getSource() == ventana.btnEliminarCurso){
      EliminarCurso ventanaNueva = new EliminarCurso();
      Stage stage = new Stage();
      try {
        ventanaNueva.start(stage);
      } catch (Exception ex) {
        Logger.getLogger(PrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
      }         
    }
    if(t.getSource() == ventana.btnAsignarCurso){
      AsignarRequisitoCurso ventanaNueva = new AsignarRequisitoCurso();
      Stage stage = new Stage();
      try {
        ventanaNueva.start(stage);
      } catch (Exception ex) {
        Logger.getLogger(PrincipalControlador.class.getName()).log(Level.SEVERE, null, ex);
      }          
    }
  }
}

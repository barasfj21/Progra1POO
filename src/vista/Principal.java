package vista;

import controlador.PrincipalControlador;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Principal es la clase main que contiene el menu principal,
 * permitiendo así navegar entre las distintas funcionalidades del programa.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class Principal extends Application{
  private AnchorPane panel;
  public Label lblTitulo;
  public Label lblTitulo2;
  public Label lblTitulo3;
  public Label lblTitulo4;
  public Label lblTitulo5;
  public Button btnRegistrarEscuela;
  public Button btnRegistrarCurso;
  public Button btnRegistrarPlan;
  public Button btnConsultarEliminarCursoPlan;
  public Button btnConsultarEliminarCursoReCo;
  public Button btnConsultarPlan;
  public Button btnEliminarCurso;
  public Button btnAsignarCurso;
  public TextField textFieldCodigo;
  private EventHandler<ActionEvent> controlador;
  public Stage stage;
    
  /**
   * 
   * @param pStage El stage donde van a estar almacenado los objetos de la 
   * ventana.
   * @throws Exception Excepcion de stage
   */
  @Override
  public void start(Stage pStage) throws Exception {
    stage = pStage;
    crearObjetos();
    controlador = new PrincipalControlador(this);
    agregarObjetos();
    agregarEvento();
    Scene scene = new Scene(panel,600.0,400.0);
    stage.setScene(scene);
    stage.show();
    }
  
  /**
   * Crea los objetos que van adjuntos a la ventana
   */
  private void crearObjetos() {
    panel = new AnchorPane();
        
    lblTitulo = new Label("Gestor de Planes de Estudio");
    lblTitulo.setLayoutX(230.0);
    lblTitulo.setLayoutY(30.0);

    lblTitulo2 = new Label("Elaborado por:");
    lblTitulo2.setLayoutX(14.0);
    lblTitulo2.setLayoutY(320.0);

    lblTitulo3 = new Label("Andres Barahona Coto");
    lblTitulo3.setLayoutX(80.0);
    lblTitulo3.setLayoutY(340.0);

    lblTitulo4 = new Label("Andres Gutierrez Espinoza");
    lblTitulo4.setLayoutX(80.0);
    lblTitulo4.setLayoutY(360.0); 

    lblTitulo5 = new Label("Menu principal");
    lblTitulo5.setLayoutX(260.0);
    lblTitulo5.setLayoutY(50.0); 

    btnRegistrarEscuela = new Button("Registrar Escuela");
    btnRegistrarEscuela.setLayoutX(50.0);
    btnRegistrarEscuela.setLayoutY(120.0);

    btnRegistrarCurso = new Button("Registrar Curso");
    btnRegistrarCurso.setLayoutX(50.0);
    btnRegistrarCurso.setLayoutY(160.0);

    btnRegistrarPlan = new Button("Registrar Plan");
    btnRegistrarPlan.setLayoutX(50.0);
    btnRegistrarPlan.setLayoutY(200.0);

    btnConsultarEliminarCursoPlan = new Button("Consultar/Eliminar\n  Curso de Plan");
    btnConsultarEliminarCursoPlan.setLayoutX(240.0);
    btnConsultarEliminarCursoPlan.setLayoutY(120.0);

    btnConsultarEliminarCursoReCo = new Button("Consultar/Eliminar\nCurso Requisito/Correquisito");
    btnConsultarEliminarCursoReCo.setLayoutX(240.0);
    btnConsultarEliminarCursoReCo.setLayoutY(180.0);

    btnConsultarPlan = new Button("Consultar Plan");
    btnConsultarPlan.setLayoutX(240.0);
    btnConsultarPlan.setLayoutY(240.0);

    btnEliminarCurso = new Button("Eliminar Curso");
    btnEliminarCurso.setLayoutX(240.0);
    btnEliminarCurso.setLayoutY(280.0);

    btnAsignarCurso = new Button("Asignar Curso\nRequisito/Correquisito");
    btnAsignarCurso.setLayoutX(430.0);
    btnAsignarCurso.setLayoutY(120.0);     
    }
  
  /**
   * agrega los objetos previamente creados a la ventana
   */
  private void agregarObjetos() {
    panel.getChildren().add(lblTitulo);
    panel.getChildren().add(lblTitulo2);
    panel.getChildren().add(lblTitulo3);
    panel.getChildren().add(lblTitulo4);
    panel.getChildren().add(lblTitulo5);

    panel.getChildren().add(btnRegistrarEscuela);
    panel.getChildren().add(btnRegistrarCurso);
    panel.getChildren().add(btnRegistrarPlan);

    panel.getChildren().add(btnConsultarEliminarCursoPlan);
    panel.getChildren().add(btnConsultarEliminarCursoReCo);
    panel.getChildren().add(btnConsultarPlan);
    panel.getChildren().add(btnEliminarCurso);

    panel.getChildren().add(btnAsignarCurso);
       
  }
    
  /**
   * agregar los distintos eventos que puede ocurrir al controlador 
   * de la clase.
   */
  private void agregarEvento() {
    btnRegistrarEscuela.setOnAction(controlador);
    btnRegistrarCurso.setOnAction(controlador);
    btnRegistrarPlan.setOnAction(controlador);
    btnConsultarEliminarCursoPlan.setOnAction(controlador);
    btnConsultarEliminarCursoReCo.setOnAction(controlador);
    btnConsultarPlan.setOnAction(controlador);
    btnEliminarCurso.setOnAction(controlador);
    btnAsignarCurso.setOnAction(controlador);
    }
    
  /**
   * Funcion main
   * @param args argumento del main
   */
  public static void main(String[] args) {
    launch(args);
  }
}

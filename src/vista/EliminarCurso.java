package vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import controlador.EliminarCursoControlador;

/**
 * EliminarCurso permite eliminar un curso.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class EliminarCurso extends Application {
  private AnchorPane panel;
  public Label lblTitulo;
  public Label lblCodigo;
  public Button btnEliminar;
  public Button btnRegresar;
  public TextField textFieldCodigo;
  private EventHandler<ActionEvent> controlador;
  public Stage stage;
    
  /**
   * 
   * @param pStage El stage donde van a estar almacenado los objetos de la ventana.
   * @throws Exception Excepcion de stage
   */
  @Override
  public void start(Stage pStage) throws Exception {
    stage = pStage;
    crearObjetos();
    controlador = new EliminarCursoControlador(this);
    agregarObjetos();
    agregarEvento();
    Scene scene = new Scene(panel,244.0,118.0);
    stage.setScene(scene);
    stage.show();
  }
    
  /**
   * Crea los objetos que van adjuntos a la ventana
   */
  private void crearObjetos() {
    panel = new AnchorPane();

    lblTitulo = new Label("Eliminar un curso");
    lblTitulo.setLayoutX(74.0);
    lblTitulo.setLayoutY(14.0);

    lblCodigo = new Label("Código del curso:");
    lblCodigo.setLayoutX(14.0);
    lblCodigo.setLayoutY(44.0);

    btnEliminar = new Button("Eliminar curso");
    btnEliminar.setLayoutX(15.0);
    btnEliminar.setLayoutY(79.0);

    btnRegresar = new Button("Regresar");
    btnRegresar.setLayoutX(169.0);
    btnRegresar.setLayoutY(79.0);

    textFieldCodigo = new TextField();
    textFieldCodigo.setPrefSize(113.0, 25.0);
    textFieldCodigo.setLayoutX(119.0);
    textFieldCodigo.setLayoutY(40.0);      
  }
  
  /**
   * agrega los objetos previamente creados a la ventana
   */
  private void agregarObjetos() {
    panel.getChildren().add(lblTitulo);
    panel.getChildren().add(lblCodigo);
    panel.getChildren().add(btnEliminar);
    panel.getChildren().add(btnRegresar);
    panel.getChildren().add(textFieldCodigo);
  }
    
  /**
   * agregar los distintos eventos que puede ocurrir al controlador 
   * de la clase.
   */
  private void agregarEvento() {
    btnEliminar.setOnAction(controlador);
    btnRegresar.setOnAction(controlador);
  }

}

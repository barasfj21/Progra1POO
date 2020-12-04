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
import controlador.RegistrarEscuelaControlador;

/**
 * Registro Escuela permite registrar una escuela
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class RegistroEscuela extends Application{
  private AnchorPane panel;
  private Label lblTitulo;
  private Label lblNombre;
  private Label lblCodigo;
  public Button btnRegistrar;
  public Button btnLimpiar;  
  public TextField textFieldNombre;
  public TextField textFieldCodigo;
  private EventHandler<ActionEvent> controlador;
    
  /**
   * 
   * @param stage El stage donde van a estar almacenado los objetos de la 
   * ventana.
   * @throws Exception Excepcion del stage
  */
  @Override
  public void start(Stage stage) throws Exception {
    crearObjetos();
    controlador = new RegistrarEscuelaControlador(this);
    agregarObjetos();
    agregarEvento();
    Scene scene = new Scene(panel,300.0,254.0);
    stage.setScene(scene);
    stage.show();   
  }
    
  /**
  * Crea los objetos que van adjuntos a la ventana 
  */
  private void crearObjetos() {
    panel = new AnchorPane();
        
    lblTitulo = new Label("Registro de Escuela o Área académica");
    lblTitulo.setLayoutX(55.0);
    lblTitulo.setLayoutY(23.0); 
        
    lblNombre = new Label("Nombre:");
    lblNombre.setLayoutX(15.0);
    lblNombre.setLayoutY(66.0);
      
    lblCodigo = new Label("Código:");
    lblCodigo.setLayoutX(18.0);
    lblCodigo.setLayoutY(111.0);

    btnRegistrar = new Button("Registrar");
    btnRegistrar.setLayoutX(55.0);
    btnRegistrar.setLayoutY(156.0);

    btnLimpiar = new Button("Limpiar");
    btnLimpiar.setLayoutX(155.0);
    btnLimpiar.setLayoutY(156.0);

    textFieldNombre = new TextField();
    textFieldNombre.setPrefSize(200.0, 25.0);
    textFieldNombre.setLayoutX(71.0);
    textFieldNombre.setLayoutY(62.0);

    textFieldCodigo = new TextField();
    textFieldCodigo.setPrefSize(200.0, 25.0);
    textFieldCodigo.setLayoutX(71.0);
    textFieldCodigo.setLayoutY(107.0);
  }
    
  /**
  * agrega los objetos previamente creados a la ventana
  */
  private void agregarObjetos() {
    panel.getChildren().add(lblTitulo);
    panel.getChildren().add(lblNombre);
    panel.getChildren().add(lblCodigo);
    panel.getChildren().add(btnRegistrar);
    panel.getChildren().add(btnLimpiar);
    panel.getChildren().add(textFieldNombre);
    panel.getChildren().add(textFieldCodigo);
  }
    
  /**
  * agregar los distintos eventos que puede ocurrir al controlador 
  * de la clase.
  */
  private void agregarEvento() {
    btnRegistrar.setOnAction(controlador);
    btnLimpiar.setOnAction(controlador);
  }
    
  /**
  * 
  * @param args Excepcion del stage
  */
  public static void main(String[] args) {
    launch(args);
  }
}

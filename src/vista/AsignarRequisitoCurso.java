package vista;

import controlador.AsignarRequisitoCursoControlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import modelo.Conexion;

/**
 *
 * Permite asignar requisitos a los cursos
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class AsignarRequisitoCurso extends Application {
  private AnchorPane panel;
  private Label lblTitulo;
  private Label lblPropietario;
  private Label lblCodigoCurso;
  private Label lblRequisito;
  private Label lblCorrequisito;
  private Label lblCodigoCursoRequisito;
  private Label lblCodigoCursoCorrequisito;
  public Button btnRegistrarRequisito;
  public Button btnRegistrarCorrequisito;
  public ComboBox comboBoxPropietario; 
  public ComboBox comboBoxCodigoCurso;
  public ComboBox comboBoxCodigoCursoRequisito;
  public ComboBox comboBoxCodigoCursoCorrequisito;
  public Stage stage;
  private EventHandler<ActionEvent> controlador;
  
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
    controlador = new AsignarRequisitoCursoControlador(this);
    agregarObjetos();
    agregarEvento();
    escuelasComboBox(comboBoxPropietario);
    Scene scene = new Scene(panel,513,297);
    stage.setScene(scene);
    stage.show();
    }
  
  /**
   * Crea los objetos que van adjuntos a la ventana
   * @throws SQLException 
   */
  private void crearObjetos() throws SQLException {
    panel = new AnchorPane();
        
    lblTitulo = new Label("Asignar requisitos y correquisitos a un curso");
    lblTitulo.setLayoutX(118);
    lblTitulo.setLayoutY(14);
    
    lblPropietario = new Label("Escuela propietaria del curso:");
    lblPropietario.setLayoutX(24.0);
    lblPropietario.setLayoutY(56.0);
    
    lblCodigoCurso = new Label("Código del curso:");
    lblCodigoCurso.setLayoutX(24.0);
    lblCodigoCurso.setLayoutY(86.0);
    
    lblRequisito = new Label("Requisitos del  curso");
    lblRequisito.setLayoutX(62.0);
    lblRequisito.setLayoutY(151.0);
    
    lblCorrequisito = new Label("Correquisitos del curso");
    lblCorrequisito.setLayoutX(317.0);
    lblCorrequisito.setLayoutY(151.0);
    
    lblCodigoCursoRequisito = new Label("Código del curso:");
    lblCodigoCursoRequisito.setLayoutX(14.0);
    lblCodigoCursoRequisito.setLayoutY(181.0);
    
    lblCodigoCursoCorrequisito = new Label("Código del curso:");
    lblCodigoCursoCorrequisito.setLayoutX(278.0);
    lblCodigoCursoCorrequisito.setLayoutY(181.0);
    
    btnRegistrarRequisito = new Button("Registrar requisito");
    btnRegistrarRequisito.setLayoutX(61.0);
    btnRegistrarRequisito.setLayoutY(217.0);
    
    btnRegistrarCorrequisito = new Button("Registrar correquisito");
    btnRegistrarCorrequisito.setLayoutX(313.0);
    btnRegistrarCorrequisito.setLayoutY(217.0);
    
    comboBoxPropietario = new ComboBox();
    comboBoxPropietario.setPrefSize(223.0,25.0);
    comboBoxPropietario.setLayoutX(194.0);
    comboBoxPropietario.setLayoutY(52.0);
    comboBoxPropietario.setPromptText("Escoja una escuela");
    
    comboBoxCodigoCurso = new ComboBox();
    comboBoxCodigoCurso.setPrefSize(130.0,25.0);
    comboBoxCodigoCurso.setLayoutX(194.0);
    comboBoxCodigoCurso.setLayoutY(91.0);
    comboBoxCodigoCurso.setPromptText("Codigo del curso");
    
    comboBoxCodigoCursoRequisito = new ComboBox();
    comboBoxCodigoCursoRequisito.setPrefSize(113.0,25.0);
    comboBoxCodigoCursoRequisito.setLayoutX(117.0);
    comboBoxCodigoCursoRequisito.setLayoutY(177.0);
    comboBoxCodigoCursoRequisito.setPromptText("Codigo del curso");
    
    comboBoxCodigoCursoCorrequisito = new ComboBox();
    comboBoxCodigoCursoCorrequisito.setPrefSize(122.0,25.0);
    comboBoxCodigoCursoCorrequisito.setLayoutX(378.0);
    comboBoxCodigoCursoCorrequisito.setLayoutY(177.0);
    comboBoxCodigoCursoCorrequisito.setPromptText("Codigo del curso");
  }
  
  /**
   * agrega los objetos previamente creados a la ventana
   */
  public void agregarObjetos() {
    panel.getChildren().add(lblTitulo);
    panel.getChildren().add(lblPropietario);
    panel.getChildren().add(lblCodigoCurso);
    panel.getChildren().add(lblRequisito);
    panel.getChildren().add(lblCorrequisito);
    panel.getChildren().add(lblCodigoCursoRequisito);
    panel.getChildren().add(lblCodigoCursoCorrequisito);
    panel.getChildren().add(btnRegistrarRequisito);
    panel.getChildren().add(btnRegistrarCorrequisito);
    panel.getChildren().add(comboBoxPropietario);
    panel.getChildren().add(comboBoxCodigoCurso);
    panel.getChildren().add(comboBoxCodigoCursoRequisito);
    panel.getChildren().add(comboBoxCodigoCursoCorrequisito);
  }
  
  /**
   * agregar los distintos eventos que puede ocurrir al controlador 
   * de la clase.
   */
  private void agregarEvento() {
    comboBoxPropietario.setOnAction(controlador);
    comboBoxCodigoCurso.setOnAction(controlador);
    btnRegistrarRequisito.setOnAction(controlador);
    btnRegistrarCorrequisito.setOnAction(controlador);
  }
  
  /**
   * Mete las escuelas en el combobox justo al iniciar la ventana, para que
   *  le precarguen al usuario que desee registrar un nuevo curso.
   * @param comboBoxPropietario combobox de las escuelas
   * @throws SQLException Excepcion por errores de conexion o de CRUDS en la base
   */
  public void escuelasComboBox(ComboBox comboBoxPropietario) throws SQLException{
    //se crea la sentencia Select 
    String query = "Select * from Escuela";
    //se establece la conexion con la base de datos
    Conexion.connect();
    ResultSet resultado = Conexion.getSelectData(query);
    
    //ciclo para agarrar todas las escuelas y meterlas en el combobox 
    while(resultado.next()){
      String escuela = resultado.getString(2);
      comboBoxPropietario.getItems().add(escuela);
    }
  }
}

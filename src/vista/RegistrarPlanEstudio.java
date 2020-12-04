package vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import controlador.RegistrarPlanEstudioControlador;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Conexion;

/**
 * Permite registrar un plan de estudios
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 * 
 */
public class RegistrarPlanEstudio extends Application {
  private AnchorPane panel;
  private Label lblTitulo;
  private Label lblPropietario;
  private Label lblCodigoPlan;
  private Label lblVigencia;
  private Label lblCursoDePlan;
  private Label lblBloque;
  private Label lblPlanEscuela;
  public Button btnRegistrarCurso;
  public Button btnRegistrarPlanEstudio;
  public Button btnRegresar;
  public ComboBox comboBoxPropietario;
  public ComboBox comboBoxBloque;
  public ComboBox comboBoxCodigoCurso;
  public ComboBox comboBoxPlanEscuela;
  public TextField textFieldCodigoPlan;
  public DatePicker vigencia;
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
    controlador = new RegistrarPlanEstudioControlador(this);
    agregarObjetos();
    agregarEvento();
    escuelasComboBox(comboBoxPropietario);
    Scene scene = new Scene(panel,456.0,292.0);
    stage.setScene(scene);
    stage.show();
  }
  
  /**
   * Crea los objetos que van adjuntos a la ventana
   */
  private void crearObjetos() throws SQLException {
    panel = new AnchorPane();
        
    lblTitulo = new Label("Registro de planes de estudio");
    lblTitulo.setLayoutX(139.0);
    lblTitulo.setLayoutY(20.0);
      
    lblPropietario = new Label("Escuela propietaria del plan:");
    lblPropietario.setLayoutX(14.0);
    lblPropietario.setLayoutY(59.0);
      
    lblCodigoPlan = new Label("Código del plan de estudios:");
    lblCodigoPlan.setLayoutX(13.0);
    lblCodigoPlan.setLayoutY(88.0);
      
    lblVigencia = new Label("Vigencia del plan de estudios:");
    lblVigencia.setLayoutX(14.0);
    lblVigencia.setLayoutY(117.0);
      
    lblCursoDePlan = new Label("Código del curso que forma parte del plan:");
    lblCursoDePlan.setLayoutX(10.0);
    lblCursoDePlan.setLayoutY(156.0);
      
    lblBloque = new Label("Bloque:");
    lblBloque.setLayoutX(356.0);
    lblBloque.setLayoutY(156.0);
      
    lblPlanEscuela = new Label ("Codigo del plan de estudios:");
    lblPlanEscuela.setLayoutX(10.0);
    lblPlanEscuela.setLayoutY(200);
      
    btnRegistrarCurso = new Button("Registrar curso al plan de estudios");
    btnRegistrarCurso.setLayoutX(140.0);
    btnRegistrarCurso.setLayoutY(260.0);
      
    btnRegistrarPlanEstudio = new Button ("Registrar plan de estudios");
    btnRegistrarPlanEstudio.setLayoutX(300.0);
    btnRegistrarPlanEstudio.setLayoutY(113.0);
      
    btnRegresar = new Button("Regresar");
    btnRegresar.setLayoutX(385.0);
    btnRegresar.setLayoutY(258.0);
      
    comboBoxPropietario = new ComboBox();
    comboBoxPropietario.setPrefSize(199.0,25.0);
    comboBoxPropietario.setLayoutX(177.0);
    comboBoxPropietario.setLayoutY(55.0);
    comboBoxPropietario.setPromptText("Escoja una escuela");
      
    comboBoxBloque = new ComboBox();
    comboBoxBloque.setPrefSize(120.0,25.0);
    comboBoxBloque.setLayoutX(321.0);
    comboBoxBloque.setLayoutY(176.0);
    comboBoxBloque.setPromptText("Semestre");
    comboBoxBloque.getItems().addAll("Semestre 0","Semestre 1","Semestre 2","Semestre 3","Semestre 4",
      "Semestre 5","Semestre 6","Semestre 7","Semestre 8","Semestre 9","Semestre 10");
              
    comboBoxCodigoCurso = new ComboBox();
    comboBoxCodigoCurso.setPrefSize(140.0,25.0);
    comboBoxCodigoCurso.setLayoutX(77.0);
    comboBoxCodigoCurso.setLayoutY(176.0);
    comboBoxCodigoCurso.setPromptText("Codigo curso");
      
    comboBoxPlanEscuela = new ComboBox();
    comboBoxPlanEscuela.setPrefSize(140.0,25.0);
    comboBoxPlanEscuela.setLayoutX(77.0);
    comboBoxPlanEscuela.setLayoutY(220.0);
    comboBoxPlanEscuela.setPromptText("Codigo plan");
      
    textFieldCodigoPlan = new TextField();
    textFieldCodigoPlan.setPrefSize(52.0, 25.0);
    textFieldCodigoPlan.setLayoutX(177.0);
    textFieldCodigoPlan.setLayoutY(84.0);
      
    vigencia = new DatePicker();
    vigencia.setPrefSize(115.0, 25.0);
    vigencia.setLayoutX(177.0);
    vigencia.setLayoutY(113.0);
  }
  
  /**
   * agrega los objetos previamente creados a la ventana
   */  
  private void agregarObjetos() {
    panel.getChildren().add(lblTitulo);
    panel.getChildren().add(lblPropietario);
    panel.getChildren().add(lblCodigoPlan);
    panel.getChildren().add(lblVigencia);
    panel.getChildren().add(lblCursoDePlan);
    panel.getChildren().add(lblBloque);
    panel.getChildren().add(lblPlanEscuela);
    panel.getChildren().add(btnRegistrarCurso);
    panel.getChildren().add(btnRegistrarPlanEstudio);
    panel.getChildren().add(btnRegresar);
    panel.getChildren().add(comboBoxPropietario);
    panel.getChildren().add(comboBoxBloque);
    panel.getChildren().add(comboBoxCodigoCurso);
    panel.getChildren().add(comboBoxPlanEscuela);
    panel.getChildren().add(textFieldCodigoPlan);
    panel.getChildren().add(vigencia);
  }
  
  /**
   * agregar los distintos eventos que puede ocurrir al controlador 
   * de la clase.
   */
  private void agregarEvento() {
    btnRegistrarPlanEstudio.setOnAction(controlador);
    btnRegistrarCurso.setOnAction(controlador);
    btnRegresar.setOnAction(controlador);
    comboBoxPropietario.setOnAction(controlador);
  }
    
 /**
   * Metodo para agregar las escuelas al combobox
   * @param comboBoxPropietario combobox de las escuelas
   * @throws SQLException Excepcion por errores de conexion o de CRUDS en la base
   */
  public void escuelasComboBox(ComboBox comboBoxPropietario) throws SQLException{
    //crea la sentencia SQL
    String query = "Select * from Escuela";
    //establece la conexion con la base de datos
    Conexion.connect();
    ResultSet resultado = Conexion.getSelectData(query);
    
    //ciclo para agarrar todas las escuelas y meterlas en el combobox 
    while(resultado.next()){
      //agarra el nombre de la escuela de la tabla retornada de la base de datos
      String escuela = resultado.getString(2);
      comboBoxPropietario.getItems().add(escuela);
    }
  }

  /**
    * 
    * @param args Argumentos del main
    */
  public static void main(String[] args) {
      launch(args);
  }
   
}

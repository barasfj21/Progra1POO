package vista;

import controlador.RegistroCursoControlador;
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
import javafx.scene.control.TextField;
import modelo.Conexion;

/**
 * RegistroCurso permite registrar nuevos cursos.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class RegistroCurso extends Application{
  private AnchorPane panel;
  public Label lblTitulo;
  public Label lblEscuela;
  public Label lblNombre;
  public Label lblCodigo;
  public Label lblCreditos;
  public Label lblHoras;
  public Label lblTextoCodigo;
  public Button btnRegistrar;
  public Button btnLimpiar;
  public ComboBox comboBoxEscuela;
  public ComboBox comboBoxCreditos;
  public ComboBox comboBoxHoras;
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
    controlador = new RegistroCursoControlador(this);
    agregarObjetos();
    agregarEvento();
    escuelasComboBox(comboBoxEscuela);
    Scene scene = new Scene(panel,420.0,266.0);
    stage.setScene(scene);
    stage.show();
    }
    
  /**
   * Crea los objetos que van adjuntos a la ventana
   * @throws SQLException 
   */
  private void crearObjetos() throws SQLException {
    panel = new AnchorPane();

    lblTitulo = new Label("Registro de cursos");
    lblTitulo.setLayoutX(163.0);
    lblTitulo.setLayoutY(25.0);

    lblEscuela = new Label("Escuela propietaria del curso:");
    lblEscuela.setLayoutX(17.0);
    lblEscuela.setLayoutY(64.0);

    lblNombre = new Label("Nombre del curso:");
    lblNombre.setLayoutX(17.0);
    lblNombre.setLayoutY(96.0);

    lblCodigo = new Label("Código del curso:");
    lblCodigo.setLayoutX(20.0);
    lblCodigo.setLayoutY(132.0);

    lblCreditos = new Label("Créditos:");
    lblCreditos.setLayoutX(20.0);
    lblCreditos.setLayoutY(163.0);

    lblHoras = new Label("Horas lectivas:");
    lblHoras.setLayoutX(17.0);
    lblHoras.setLayoutY(192.0);

    lblTextoCodigo = new Label("---");
    lblTextoCodigo.setLayoutX(179.0);
    lblTextoCodigo.setLayoutY(132.0);

    btnRegistrar = new Button("Registrar");
    btnRegistrar.setLayoutX(132.0);
    btnRegistrar.setLayoutY(230.0);

    btnLimpiar = new Button("Limpiar campos");
    btnLimpiar.setLayoutX(212.0);
    btnLimpiar.setLayoutY(230.0);

    comboBoxEscuela = new ComboBox<String>();
    comboBoxEscuela.setPrefSize(216.0,25.0);
    comboBoxEscuela.setLayoutX(179.0);
    comboBoxEscuela.setLayoutY(60.0);
    comboBoxEscuela.setPromptText("Escoja una escuela");

    comboBoxCreditos = new ComboBox();
    comboBoxCreditos.setPrefWidth(60.0);
    comboBoxCreditos.setLayoutX(179.0);
    comboBoxCreditos.setLayoutY(159.0);
    comboBoxCreditos.getItems().addAll(0,1,2,3,4);
    comboBoxCreditos.getSelectionModel().selectFirst();

    comboBoxHoras = new ComboBox();
    comboBoxHoras.setPrefWidth(60.0);
    comboBoxHoras.setLayoutX(179.0);
    comboBoxHoras.setLayoutY(188.0);
    comboBoxHoras.getItems().addAll(1,2,3,4,5);
    comboBoxHoras.getSelectionModel().selectFirst();

    textFieldNombre = new TextField();
    textFieldNombre.setPrefSize(216.0, 25.0);
    textFieldNombre.setLayoutX(179.0);
    textFieldNombre.setLayoutY(92.0);

    textFieldCodigo = new TextField();
    textFieldCodigo.setPrefSize(50.0, 25.0);
    textFieldCodigo.setLayoutX(210.0);
    textFieldCodigo.setLayoutY(128.0);
  }
    
  /**
   * agrega los objetos previamente creados a la ventana
   */
  private void agregarObjetos() {
    panel.getChildren().add(lblTitulo);
    panel.getChildren().add(lblEscuela);
    panel.getChildren().add(lblNombre);
    panel.getChildren().add(lblCodigo);
    panel.getChildren().add(lblCreditos);
    panel.getChildren().add(lblHoras);
    panel.getChildren().add(lblTextoCodigo);
    panel.getChildren().add(btnRegistrar);
    panel.getChildren().add(btnLimpiar);
    panel.getChildren().add(comboBoxEscuela);
    panel.getChildren().add(comboBoxCreditos);
    panel.getChildren().add(comboBoxHoras);
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
    comboBoxEscuela.setOnAction(controlador);
  }
    
  /**
   * Mete las escuelas en el combobox justo al iniciar la ventana, para que
   * le precarguen al usuario que desee registrar un nuevo curso.
   * @param comboBoxEscuelas combobox de las escuelas
   * @throws SQLException Excepcion por errores de conexion o de CRUDS en la base
   */
  public void escuelasComboBox(ComboBox comboBoxEscuelas) throws SQLException {
   //se crea la sentencia Select 
    String query = "Select * from Escuela";
   //se establece la conexion con la base de datos
    Conexion.connect();
    ResultSet resultado = Conexion.getSelectData(query);
    
    //ciclo para agarrar todas las escuelas y meterlas en el combobox 
    while(resultado.next()){
      //agarra el nombre de la escuela de la tabla retornada.
      String escuela = resultado.getString(2);
      comboBoxEscuelas.getItems().add(escuela);
    }
  }
    
  /**
   * 
   * @param args Argumentos del main
   * @throws SQLException Excepcion por errores de conexion o de CRUDS en la base
   */
  public static void main(String[] args) throws SQLException {
    launch(args);
  }

}
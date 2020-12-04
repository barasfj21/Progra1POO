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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import controlador.ConsultarEliminarRequisitoCorrequisitoController;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Curso;

/**
 * Esta clase permite consultar cuales son los requisitos y correquisitos 
 * de un curso especifico. Ademas, permite eliminarlos del curso consultado.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class ConsultarEliminarRequisitoCorrequisito extends Application{
  public AnchorPane panel;
  public Label lblTitulo;
  public Label lblCodigo;
  public Label lblTabla;
  public Button btnEliminar;
  public Button btnRegresar;
  public Button btnConsultarRequisitos;
  public Button btnConsultarCorrequisitos;
  public TextField textFieldCodigo;
  public TableView<Curso> tblCursos;
  private EventHandler<ActionEvent> controlador;
  public boolean requisito = false;
  public String codigoCurso = "";
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
    agregarObjetos();
    agregarEvento();
    Scene scene = new Scene(panel,600.0,254.0);
    pStage.setScene(scene);
    pStage.show();   
  }
    
  /**
   * Crea los objetos que van adjuntos a la ventana
   */
  private void crearObjetos() {
    panel = new AnchorPane();

    lblTitulo = new Label("Consultar o eliminar un requisito/correquisito de un curso");
    lblTitulo.setLayoutX(160.0);
    lblTitulo.setLayoutY(14.0);

    lblCodigo = new Label("Código del curso:");
    lblCodigo.setLayoutX(14.0);
    lblCodigo.setLayoutY(42.0);

    lblTabla = new Label("Requisitos/Correquitos del curso:");
    lblTabla.setLayoutX(14.0);
    lblTabla.setLayoutY(82.0);

    btnEliminar = new Button("Eliminar requisito/correquisito seleccionado");
    btnEliminar.setLayoutX(14.0);
    btnEliminar.setLayoutY(219.0);

    btnRegresar = new Button("Regresar");
    btnRegresar.setLayoutX(534.0);
    btnRegresar.setLayoutY(219.0);

    btnConsultarRequisitos = new Button("Consultar requisitos");
    btnConsultarRequisitos.setLayoutX(305.0);
    btnConsultarRequisitos.setLayoutY(70.0);

    btnConsultarCorrequisitos = new Button("Consultar correquisitos");
    btnConsultarCorrequisitos.setLayoutX(440.0);
    btnConsultarCorrequisitos.setLayoutY(70.0);

    textFieldCodigo = new TextField();
    textFieldCodigo.setPrefSize(93.0, 25.0);
    textFieldCodigo.setLayoutX(120.0);
    textFieldCodigo.setLayoutY(38.0);

    tblCursos = new TableView();
    tblCursos.setPrefSize(579.0, 102.0);
    tblCursos.setLayoutX(16.0);
    tblCursos.setLayoutY(106.0);
    
    TableColumn codigo = new TableColumn("Código del curso");
    codigo.setCellValueFactory(new PropertyValueFactory<Curso,String>("codigoCurso"));
    codigo.setPrefWidth(100.0);
    TableColumn nombre = new TableColumn("Nombre del curso");
    nombre.setCellValueFactory(new PropertyValueFactory<Curso,String>("nombreCurso"));
    nombre.setPrefWidth(271.0);
    TableColumn horas = new TableColumn("Horas de clase");
    horas.setCellValueFactory(new PropertyValueFactory<Curso,Integer>("cantidadHorasLectivas"));
    horas.setPrefWidth(91.0);
    TableColumn creditos = new TableColumn("Créditos");
    creditos.setCellValueFactory(new PropertyValueFactory<Curso,Integer>("cantidadCreditos"));
    creditos.setPrefWidth(52.0);
    
    tblCursos.getColumns().addAll(codigo,nombre,horas,creditos);
    
    controlador = new ConsultarEliminarRequisitoCorrequisitoController(this);
  }
    
  /**
   * agrega los objetos previamente creados a la ventana
   */
  private void agregarObjetos() {
    panel.getChildren().add(lblTitulo);
    panel.getChildren().add(lblCodigo);
    panel.getChildren().add(lblTabla);
    panel.getChildren().add(btnEliminar);
    panel.getChildren().add(btnRegresar);
    panel.getChildren().add(btnConsultarRequisitos);
    panel.getChildren().add(btnConsultarCorrequisitos);
    panel.getChildren().add(textFieldCodigo);
    panel.getChildren().add(tblCursos);
  }

  /**
   * agregar los distintos eventos que puede ocurrir al controlador 
   * de la clase.
   */
  private void agregarEvento() {
    btnRegresar.setOnAction(controlador);
    btnConsultarCorrequisitos.setOnAction(controlador);  
    btnConsultarRequisitos.setOnAction(controlador);
    btnEliminar.setOnAction(controlador);
  }
}
package vista;

import controlador.ConsultarEliminarCursoPlanEstudiosController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.PlanDeEstudio;

/**
 * Esta clase permite consultar o eliminar un curso de un plan de estudios
 * especifico.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class ConsultarEliminarCursoPlanEstudios extends Application {
  private AnchorPane panel;
  private Label lblTitulo;
  private Label lblCodigo;
  private Label lblTabla;
  public Button btnEliminar;
  public Button btnRegresar;
  public Button btnConsultarPlanes;
  public TextField textFieldCodigo;
  public TableView<PlanDeEstudio> tblPlanes;
  private EventHandler<ActionEvent> controlador;
  public String codigoCurso;
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

    lblTitulo = new Label("Consultar/eliminar un curso de un plan de estudios");
    lblTitulo.setLayoutX(160.0);
    lblTitulo.setLayoutY(14.0);
    
    lblCodigo = new Label("Código del curso:");
    lblCodigo.setLayoutX(14.0);
    lblCodigo.setLayoutY(42.0);
    
    lblTabla = new Label("Planes de estudio a los que pertenece:");
    lblTabla.setLayoutX(14.0);
    lblTabla.setLayoutY(82.0);
    
    btnEliminar = new Button("Eliminar Curso");
    btnEliminar.setLayoutX(14.0);
    btnEliminar.setLayoutY(219.0);
    
    btnRegresar = new Button("Regresar");
    btnRegresar.setLayoutX(534.0);
    btnRegresar.setLayoutY(219.0);
    
    btnConsultarPlanes = new Button("Consultar");
    btnConsultarPlanes.setLayoutX(482.0);
    btnConsultarPlanes.setLayoutY(78.0);
    
    textFieldCodigo = new TextField();
    textFieldCodigo.setPrefSize(93.0, 25.0);
    textFieldCodigo.setLayoutX(120.0);
    textFieldCodigo.setLayoutY(38.0);
    
    tblPlanes = new TableView();
    tblPlanes.setPrefSize(578.0, 101.0);
    tblPlanes.setLayoutX(16.0);
    tblPlanes.setLayoutY(106.0);
    
    TableColumn codigo = new TableColumn("Código del plan");
    codigo.setCellValueFactory(new PropertyValueFactory<PlanDeEstudio,Integer>("numeroPlanEstudio"));
    codigo.setPrefWidth(95.0);
    TableColumn vigencia = new TableColumn("Fecha vigencia");
    vigencia.setCellValueFactory(new PropertyValueFactory<PlanDeEstudio,String>("vigencia"));
    vigencia.setPrefWidth(95.0);
    TableColumn propietario = new TableColumn("Nombre Escuela");
    propietario.setCellValueFactory(new PropertyValueFactory<PlanDeEstudio,String>("escuelaPropietaria"));
    propietario.setPrefWidth(120.0);
    TableColumn cursos = new TableColumn("Cursos totales");
    cursos.setCellValueFactory(new PropertyValueFactory<PlanDeEstudio,Integer>("cursosTotales"));
    cursos.setPrefWidth(80.0);
    TableColumn creditos = new TableColumn("Créditos totales");
    creditos.setCellValueFactory(new PropertyValueFactory<PlanDeEstudio,Integer>("creditosTotales"));
    creditos.setPrefWidth(80.0);
    
    tblPlanes.getColumns().addAll(codigo,vigencia,propietario,cursos,creditos);
    
    controlador = new ConsultarEliminarCursoPlanEstudiosController(this);
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
    panel.getChildren().add(btnConsultarPlanes);
    panel.getChildren().add(textFieldCodigo);
    panel.getChildren().add(tblPlanes);
  }

  /**
   * agregar los distintos eventos que puede ocurrir al controlador 
   * de la clase.
   */
  private void agregarEvento() {
    btnEliminar.setOnAction(controlador);
    btnRegresar.setOnAction(controlador);
    btnConsultarPlanes.setOnAction(controlador);
  }

  public static void main(String[] args) {
      launch(args);
  }
}
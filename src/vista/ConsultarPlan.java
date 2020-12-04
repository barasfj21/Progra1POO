package vista;

import modelo.Conexion;
import modelo.Curso;
import java.sql.SQLException;
import java.sql.ResultSet;
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
import javafx.scene.control.ComboBox;
import controlador.ConsultarPlanDeEstudioControlador;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * ConsultarPlan permite consultar los planes de estudio
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class ConsultarPlan extends Application {
    private AnchorPane panel;
    private Label lblTitulo;
    private Label lblPropietario;
    private Label lblCodigoPlan;
    private Label lblVigencia;
    public Button btnConsultar;
    public Button btnGenerarPDF;
    public TextField textFieldCodigo;
    public DatePicker vigencia;
    public TableView tblCursosPlan;
    public ComboBox comboBoxPropietario;
    private EventHandler<ActionEvent> controlador;
    
    /**
     * 
     * @param stage El stage donde van a estar almacenado los objetos de la 
     * ventana.
     * @throws Exception Excepcion de stage
     */
    @Override
    public void start(Stage stage) throws Exception {
      crearObjetos();
      controlador = new ConsultarPlanDeEstudioControlador(this);
      agregarObjetos();
      agregarEvento();
      escuelasComboBox();
      Scene scene = new Scene(panel,600.0,350.0);
      stage.setScene(scene);
      stage.show();
    }
    
    /**
    * Crea los objetos que van adjuntos a la ventana
    */
    private void crearObjetos() {
      panel = new AnchorPane();
        
      lblTitulo = new Label("Consultar el plan de estudio");
      lblTitulo.setLayoutX(226.0);
      lblTitulo.setLayoutY(23.0);
        
      lblPropietario = new Label("Escuela propietaria del plan:");
      lblPropietario.setLayoutX(14.0);
      lblPropietario.setLayoutY(70.0);
        
      lblCodigoPlan = new Label("Código del plan de estudios:");
      lblCodigoPlan.setLayoutX(373.0);
      lblCodigoPlan.setLayoutY(70.0);
        
      lblVigencia = new Label("Vigencia del plan de estudios:");
      lblVigencia.setLayoutX(14.0);
      lblVigencia.setLayoutY(109.0);
      
      btnConsultar = new Button("Consultar");
      btnConsultar.setLayoutX(14.0);
      btnConsultar.setLayoutY(170.0);
      
      btnGenerarPDF = new Button("Generar pdf y enviar correo");
      btnGenerarPDF.setLayoutX(415.0);
      btnGenerarPDF.setLayoutY(130.0);
        
      textFieldCodigo = new TextField();
      textFieldCodigo.setPrefSize(52.0, 25.0);
      textFieldCodigo.setLayoutX(525.0);
      textFieldCodigo.setLayoutY(66.0);
        
      vigencia = new DatePicker();
      vigencia.setPrefSize(120, 25.0);
      vigencia.setLayoutX(179.0);
      vigencia.setLayoutY(105.0);
        
      comboBoxPropietario = new ComboBox();
      comboBoxPropietario.setPrefSize(195.0,25.0);
      comboBoxPropietario.setLayoutX(165.0);
      comboBoxPropietario.setLayoutY(66.0);
        
      tblCursosPlan = new TableView();
      tblCursosPlan.setPrefSize(566.0, 102.0);
      tblCursosPlan.setLayoutX(11.0);
      tblCursosPlan.setLayoutY(205.0);
        
      TableColumn codigo = new TableColumn("Código del curso");
      codigo.setCellValueFactory(new PropertyValueFactory<Curso,String>("codigoCurso"));
      codigo.setPrefWidth(100.0);
      TableColumn nombre = new TableColumn("Nombre del curso");
      nombre.setCellValueFactory(new PropertyValueFactory<Curso,String>("nombreCurso"));
      nombre.setPrefWidth(170.0);
      TableColumn creditos = new TableColumn("Créditos");
      creditos.setCellValueFactory(new PropertyValueFactory<Curso,Integer>("cantidadCreditos"));
      creditos.setPrefWidth(53.0);
      TableColumn horas = new TableColumn("Horas lectivas");
      horas.setCellValueFactory(new PropertyValueFactory<Curso,Integer>("cantidadHorasLectivas"));
      horas.setPrefWidth(89.0);
      TableColumn bloque = new TableColumn("Bloque");
      bloque.setCellValueFactory(new PropertyValueFactory<Curso,String>("bloque"));
      bloque.setPrefWidth(100.0);
      
        
      tblCursosPlan.getColumns().addAll(codigo,nombre,creditos,horas,bloque);
    }
    
    /**
    * agrega los objetos previamente creados a la ventana
    */
    private void agregarObjetos() {
      panel.getChildren().add(lblTitulo);
      panel.getChildren().add(lblPropietario);
      panel.getChildren().add(lblCodigoPlan);
      panel.getChildren().add(lblVigencia);
      panel.getChildren().add(btnConsultar);
      panel.getChildren().add(btnGenerarPDF);
      panel.getChildren().add(textFieldCodigo);
      panel.getChildren().add(vigencia);
      panel.getChildren().add(comboBoxPropietario);
      panel.getChildren().add(tblCursosPlan);
    }
    
    /**
    * agrega los objetos previamente creados a la ventana
    */
    private void agregarEvento() {
      btnGenerarPDF.setOnAction(controlador);
      btnConsultar.setOnAction(controlador);
      comboBoxPropietario.setOnAction(controlador);
    }
    
    /**
     *  Metodo para agregar las escuelas al combobox
     * @param comboBoxEscuelas El combobox de la escuelas
     * @throws SQLException 
     */
    private void escuelasComboBox() throws SQLException {
      String query = "select nombreEscuela from Escuela "
              + "inner join Escuela_PlanEstudio on "
              + "Escuela.codigoEscuela=Escuela_PlanEstudio.codigoEscuela;";
      //Establece la conexion con la base de datos
      Conexion.connect();
      //guarda el resultado en un objeto ResultSet 
      ResultSet resultado = Conexion.getSelectData(query);
    
      //ciclo para agarrar todas las escuelas y meterlas en el combobox 
      while(resultado.next()) {
        comboBoxPropietario.getItems().add(resultado.getString("nombreEscuela"));
      }
      Conexion.closeConnection();
    }

}

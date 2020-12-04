package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vista.ConsultarPlan;
import modelo.Conexion;
import modelo.Curso;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Es el controlador de la clase ConsultarPlan
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class ConsultarPlanDeEstudioControlador implements EventHandler<ActionEvent> {
  ConsultarPlan ventana;  
  
  /**
   * 
   * @param pVentana Ventana controlada
   */
  public ConsultarPlanDeEstudioControlador(ConsultarPlan pVentana) {
    ventana = pVentana;
  }    
  
  /**
   * 
   * @param pCorreoUsuario Correo del usuario
   * @param pNumeroPlanCorreo Numero del plan de estudios
   * @throws FileNotFoundException Excepcion si no encuentra el archivo pdf
   * @throws DocumentException Excepcion si se construye mal el pdf
   */
  public void enviarCorreo(Optional<String> pCorreoUsuario,String pNumeroPlanCorreo) throws FileNotFoundException, DocumentException {
    //Se definen las propiedades del correo
    Properties propiedad = new Properties();
    propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
    propiedad.setProperty("mail.smtp.starttls.enable", "true");
    propiedad.setProperty("mail.smtp.port", "587");
    propiedad.setProperty("mail.smtp.auth", "true");
    //Se inicia una sesion con las propiedaes especificadas    
    Session sesion = Session.getDefaultInstance(propiedad);
    //Se ingresan los datos del correo que enviara    
    String correoEnvia = "ramenelseptimo@gmail.com";
    String contrasena = "doflamingo01";
    String destinatario = pCorreoUsuario.get();   //Correo del cliente
    String asunto = "Reporte plan de estudio";
        
    MimeMessage mail = new MimeMessage(sesion);
        
    try {
      mail.setFrom(new InternetAddress (correoEnvia));
      mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
      mail.setSubject(asunto);
            
      Multipart emailContent = new MimeMultipart();
			
      
      MimeBodyPart textBodyPart = new MimeBodyPart();
      textBodyPart.setText("Generación automática de PDF. Por favor no responder este correo.");
	
      MimeBodyPart pdfAttachment = new MimeBodyPart();
      try {
        //Ruta en la que se guarda el pdf generado     
        pdfAttachment.attachFile("C:\\Users\\Andrés Barahona\\Documents\\TEC\\IV Semestre"
          + "\\Programación Orientada a Objetos\\Proyectos Programados\\Proyecto 1\\Progra1\\"
          +pNumeroPlanCorreo+".pdf");
                
      } catch (IOException ex) {
        Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error de concatenacion de pdf");
      }		
            
      //Se adjunta el pdf al cuerpo del correo
      emailContent.addBodyPart(textBodyPart);
      emailContent.addBodyPart(pdfAttachment);
			
      //Se define el contenido del correo, el cual es el anterior cuerpo
      mail.setContent(emailContent);
      //Metodos de envio del correo      
      Transport transporte = sesion.getTransport("smtp");
      transporte.connect(correoEnvia,contrasena);
      transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
      transporte.close();
      
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Reporte");
      alert.setContentText("Correo enviado exitosamente.");
      alert.showAndWait();
   
    } catch (AddressException ex) {
      Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (MessagingException ex) {
      Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
    }
           
  }
  
  /**
   * 
   * @param t Corresponde al los elementos que ocasionaron el evento
   */
  @Override
  public void handle(ActionEvent t) {
    if(t.getSource()==ventana.btnConsultar){
      ventana.tblCursosPlan.getItems().clear();
      //Se valida que los inputs tengan informacion
      if ((ventana.vigencia.getValue() != null) &&
        (ventana.comboBoxPropietario.getSelectionModel().selectedItemProperty().getValue() != null) &&
        (ventana.textFieldCodigo.getText() != null && !ventana.textFieldCodigo.getText().isEmpty())) {
          //Este try valida que el numero del plan sea un integer.
          try {
            //Se extraen los valores de los inputs de info del usuario  
            String pFechaVigencia = (ventana.vigencia.getValue()).toString();
            int pNumeroPlan = Integer.parseInt(ventana.textFieldCodigo.getText());
            String pNombreEscuela = (ventana.comboBoxPropietario.getSelectionModel().selectedItemProperty().getValue()).toString();
            //Se crea un query de los cursos que estan asignados a un plan, y de los planes que esten
            //asignados a una escuela.
            String querySelect = "select Curso.codigoCurso,Curso.nombreCurso,Curso.cantidadCreditos,"
            + "Curso.cantidadHorasLectivas,Curso.bloque from PlanEstudio_Curso inner join "
            + "PlanDeEstudio on PlanEstudio_Curso.numeroPlanEstudio=PlanDeEstudio.numeroPlanEstudio\n" 
            + "inner join Escuela_PlanEstudio on PlanDeEstudio.numeroPlanEstudio="
            + "Escuela_PlanEstudio.numeroPlanEstudio inner join Escuela on "
            + "Escuela_PlanEstudio.codigoEscuela=Escuela.codigoEscuela inner join "
            + "Curso on PlanEstudio_Curso.codigoCurso=Curso.codigoCurso where "
            + "Escuela.nombreEscuela='"+pNombreEscuela+"' and  "
            + "Escuela_PlanEstudio.numeroPlanEstudio="+pNumeroPlan+" and "
            + "PlanDeEstudio.vigencia='"+pFechaVigencia+"'";
            try {
              //Inicio del codigo para generar el PDF
              FileOutputStream archivo = new FileOutputStream(pNumeroPlan + ".pdf");
              Document documento = new Document();
              PdfWriter.getInstance(documento, archivo);
              documento.open();
              PdfPTable tabla = new PdfPTable(5);
            
              Paragraph parrafo = new Paragraph("Datos del plan de estudios: "+pNumeroPlan);
              parrafo.setAlignment(1);
              documento.add(parrafo);
              documento.add(new Paragraph(""));
    
              float[] mediaCeldas ={3.70f,3.70f,3.50f,3.50f,3.70f};
              //Se construye una tabla en el pdf, y se agregan las columnas.
              tabla.setWidths(mediaCeldas);
              tabla.addCell(new Paragraph("Codigo del Curso", FontFactory.getFont("Arial",12)));
              tabla.addCell(new Paragraph("Nombre del Curso", FontFactory.getFont("Arial",12)));
              tabla.addCell(new Paragraph("Créditos", FontFactory.getFont("Arial",12)));
              tabla.addCell(new Paragraph("Horas lectivas", FontFactory.getFont("Arial",12)));
              tabla.addCell(new Paragraph("Bloque", FontFactory.getFont("Arial",12)));
              //Fin del codigo para generar el PDF

              Conexion.connect();  
              ResultSet result = Conexion.getSelectData(querySelect);
              while (result.next()) {
                //Se construye un curso con lo que retorna la base
                Curso curso = new Curso(result.getString("codigoCurso"),
                result.getString("nombreCurso"),result.getInt("cantidadCreditos"),
                result.getInt("cantidadHorasLectivas"),result.getString("bloque"));
                //Se incerta cada uno de esos cursos en la tabla
                ventana.tblCursosPlan.getItems().add(curso);
                
                //Se agrega la info al pdf, para aprovechar la función del ciclo.
                tabla.addCell(new Paragraph(curso.getCodigoCurso(), FontFactory.getFont("Arial",10)));                  
                tabla.addCell(new Paragraph(curso.getNombreCurso(), FontFactory.getFont("Arial",10)));
                String cantidadCreditos = String.valueOf(curso.getCantidadCreditos());
                tabla.addCell(new Paragraph(cantidadCreditos,FontFactory.getFont("Arial",10)));
                String cantidadHorasLectivas = String.valueOf(curso.getCantidadHorasLectivas());
                tabla.addCell(new Paragraph(cantidadHorasLectivas,FontFactory.getFont("Arial",10)));
                tabla.addCell(new Paragraph(curso.getBloque(), FontFactory.getFont("Arial",10)));
              }
              //Se cierra el documento pdf para guardar los cambios.
              documento.add(tabla);              
              documento.close();
              archivo.close();
              Conexion.closeConnection();
            } catch (SQLException ex) {
              Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                  Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
              } catch (DocumentException ex) {
                  Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
              } catch (IOException ex) {
                  Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
              }
          } catch (NumberFormatException e){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Por favor ingrese un número de cuatro dígitos"
                    + " como código del plan.");
            alert.showAndWait(); 
          }
      } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Por favor ingrese datos en los campos de texto.");
        alert.showAndWait();
      }
            
    }
    if(t.getSource()==ventana.btnGenerarPDF){
      //Se obtiene el correo del usuario por medio de un mensaje pop-up
      TextInputDialog entradaCorreoUsuario = new TextInputDialog("");
      entradaCorreoUsuario.setHeaderText("Ingrese su correo electrónico.");
      entradaCorreoUsuario.setContentText("Su correo:");
      //Se valida que el campo de texto no este vacio
        try {
          Optional<String> pCorreoUsuario = entradaCorreoUsuario.showAndWait();
          String pNumeroPlanCorreo =  ventana.textFieldCodigo.getText();
          enviarCorreo(pCorreoUsuario,pNumeroPlanCorreo); 
        } catch (FileNotFoundException ex) {
          Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
          Logger.getLogger(ConsultarPlanDeEstudioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
}
package modelo;

/**
 * Escuela es la clase que permite manejar el objeto de una escuela.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class Escuela {
  private String codigoEscuela;
  private String nombreEscuela;

  /**
   * Metodo constructor default
   */
  public Escuela() {
    setCodigoEscuela("");
    setNombreEscuela("");    
  }
  
  /**
   *  Metodo constructor para la clase Escuela
   * @param pCodigoEscuela Es el codigo de la escuela
   * @param pNombreEscuela Es el nombre de la escuela
   */
  public Escuela(String pCodigoEscuela, String pNombreEscuela) {
    setCodigoEscuela(pCodigoEscuela);
    setNombreEscuela(pNombreEscuela);
  }

  /**
   *  Metodo para establecer el atributo de codigo en el objeto de Escuela
   * @param pCodigoEscuela Codigo unico de una escuela
   */
  public void setCodigoEscuela(String pCodigoEscuela) {
    codigoEscuela= pCodigoEscuela;
  }

  /**
   *  Metodo para retornar el atributo de codigo en el objeto de Escuela
   * @return retorna el codigo de la escuela
   */
  public String getCodigoEscuela() {
    return codigoEscuela;
  }

  /**
   * Metodo para establecer el atributo de nombre en el objeto de Escuela
   * @param pNombreEscuela El nombre de la escuela
   */
  public void setNombreEscuela(String pNombreEscuela) {
    nombreEscuela = pNombreEscuela;
  }

  /**
   * Metodo para retornar el atributo de nombre en el objeto de Escuela
   * @return retorna el nombre de la escuela
   */
  public String getNombreEscuela() {
    return nombreEscuela;
  }
  
  /**
   * Metodo que retorna la info de la escuela
   * @return retorna un toString 
   */
  public String toString() {
    String msg = "";
    msg = "CodigoEscuela="+codigoEscuela+
      "NombreEscuela="+nombreEscuela;
    return msg;
  }
  
}

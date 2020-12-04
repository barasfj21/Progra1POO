package modelo;

/**
 * Curso es la clase que permite manejar el objeto de curso.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public final class Curso {
  private String codigoCurso;
  private String nombreCurso;
  private int cantidadCreditos;
  private int cantidadHorasLectivas;
  private String bloque;
  
  /**
   * Metodo constructor default
   */
  public Curso() {
    setCodigoCurso("");
    setNombreCurso("");
    setCantidadCreditos(0);
    setCantidadHorasLectivas(0);
    setBloque("");  
  }
  
  /**
   * Metodo constructor para crear un curso
   * @param pCodigoCurso Es el codigo del curso
   * @param pNombreCurso Es el nombre del curso
   * @param pCantidadCreditos Es la cantidad de creditos que posee el curso
   * @param pCantidadHorasLectivas es la cantidad de horas lectivas del curso
   */
  public Curso(String pCodigoCurso, String pNombreCurso,
      int pCantidadHorasLectivas,int pCantidadCreditos) {
    setCodigoCurso(pCodigoCurso);
    setNombreCurso(pNombreCurso);
    setCantidadCreditos(pCantidadCreditos);
    setCantidadHorasLectivas(pCantidadHorasLectivas);
  }
  
  /**
   * 
   * @param pCodigoCurso Es el codigo del curso
   * @param pNombreCurso Es el nombre del curso
   * @param pCantidadHorasLectivas es la cantidad de horas lectivas del curso
   * @param pCantidadCreditos Es la cantidad de creditos que posee el curso
   * @param bloque Es el bloque semestral del curso
   */
  public Curso(String pCodigoCurso, String pNombreCurso,
      int pCantidadHorasLectivas,int pCantidadCreditos,String bloque) {
    setCodigoCurso(pCodigoCurso);
    setNombreCurso(pNombreCurso);
    setCantidadCreditos(pCantidadCreditos);
    setCantidadHorasLectivas(pCantidadHorasLectivas);
    setBloque(bloque);
  }
  
  /**
   *   Metodo para establecer el atributo de codigo en el objeto de Curso
   * @param pCodigoCurso El codigo del curso
   */
  public void setCodigoCurso(String pCodigoCurso) {
    codigoCurso = pCodigoCurso;
  }
  
  /**
   *  Metodo para retornar el atributo de codigo en el objeto de Curso
   * @return retorna el codigo del curso
   */
  public String getCodigoCurso() {
    return codigoCurso;
  }
  
  /**
   *  Metodo para establecer el atributo de nombre en el objeto de Curso
   * @param pNombreCurso El nombre del curso
   */
  public void setNombreCurso(String pNombreCurso) {
    nombreCurso = pNombreCurso;
  }
  
  /**
   *  Metodo para retornar el atributo de nombre en el objeto de Curso
   * @return retorna el nombre del curso
   */
  public String getNombreCurso() {
    return nombreCurso;
  }

  /**
   *  Metodo para establecer el atributo de cantidadCreditos en el objeto de Curso
   * @param pCantidadCreditos La cantidad de creditos
   */
  public void setCantidadCreditos(int pCantidadCreditos) {
    cantidadCreditos = pCantidadCreditos;
  }
  
  /**
   *  Metodo para retornar el atributo de cantidadCreditos en el objeto de Curso
   * @return retorna la cantidad de creditos del curso
   */
  public int getCantidadCreditos() {
    return cantidadCreditos;
  }
  
  /**
   *  Metodo para establecer el atributo de cantidadHorasLectivas en el objeto de Curso
   * @param pCantidadHorasLectivas Cantidad de horas lectivas de un curso
   */
  public void setCantidadHorasLectivas(int pCantidadHorasLectivas) {
    cantidadHorasLectivas = pCantidadHorasLectivas;
  }
  
  /**
   * Metodo para retornar el atributo de cantidadHorasLectivas en el objeto de Curso
   * @return retorna la cantidad de horas lectivas del curso
   */
  public int getCantidadHorasLectivas() {
    return cantidadHorasLectivas;
  }
  
  /**
   * Metodo para establecer el atributo de bloque en el objeto de Curso
   * @param pBloque Bloque semestral de un semestre
   */
  public void setBloque(String pBloque) {
    bloque = pBloque;
  }
  
  /**
   * Metodo para retornar el atributo de bloque en el objeto de Curso
   * @return retorna el bloque en que se encuentra el curso
   */
  public String getBloque() {
    return bloque; 
  }
  
  /**
   * Metodo que retorna info del curso
   * @return retorna un toString
   */
  public String toString() {
    String msg = "";
    msg = "CodigoCurso="+codigoCurso+
      "NombreCurso="+nombreCurso+
      "CantidadCreditos="+cantidadCreditos+
      "CantidadHorasLectivas"+cantidadHorasLectivas+
      "Bloque="+bloque;  
    return msg;
  }
  
}

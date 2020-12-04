package modelo;

/**
 * PlanDeEstudio es la clase que permite manejar el objeto de un plan de estudio.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public final class PlanDeEstudio {
  private int numeroPlanEstudio;
  private String vigencia;
  private String escuelaPropietaria;
  private int cursosTotales;
  private int creditosTotales;
  
  /**
   * Metodo constructor default
   */
  public PlanDeEstudio() {
    setNumeroPlanEstudio(0);
    setVigencia("");
    setEscuelaPropietaria("");
    setCursosTotales(0);
    setCreditosTotales(0);    
  }
  
  /**
   * Metodo constructor para crear un plan de estudio
   * @param pNumeroPlanEstudio Es el numero de plan de estudio de la carrera
   * @param pVigenciaPlan Es la fecha de vigencia del plan
   * @param pEscuelaPropietaria Escuela dueña del plan
   * @param pCursosTotales Cursos totales de un plan 
   * @param pCreditosTotales Suma de los creditos de los cursos de un plan
   */
  public PlanDeEstudio(int pNumeroPlanEstudio, String pVigenciaPlan, String pEscuelaPropietaria,
      int pCursosTotales, int pCreditosTotales) {
    setNumeroPlanEstudio(pNumeroPlanEstudio);
    setVigencia(pVigenciaPlan);
    setEscuelaPropietaria(pEscuelaPropietaria);
    setCursosTotales(pCursosTotales);
    setCreditosTotales(pCreditosTotales);
  }
  
  /**
   *  Metodo para establecer el atributo de numero del objeto de Plan de Estudio
   * @param pNumeroPlanEstudio El numero de plan de estudio
   */
  public void setNumeroPlanEstudio(int pNumeroPlanEstudio) {
    numeroPlanEstudio = pNumeroPlanEstudio;
  }
  
  /**
   *  Metodo para retornar el atributo de numero del objeto de Plan de Estudio
   * @return retorna el numero de plan de estudio
   */
  public int getNumeroPlanEstudio() {
    return numeroPlanEstudio;
  }
 
  /**
   * Metodo para establecer el atributo de vigencia del objeto de Plan de Estudio
   * @param pVigenciaPlan Es la fecha de vigencia del plan
   */
  public void setVigencia(String pVigenciaPlan) {
    vigencia = pVigenciaPlan;
  }
  
  /**
   *  Metodo para retornar el atributo de vigencia del objeto de Plan de Estudio
   * @return retorna la fecha de vigencia del plan de estudio
   */
  public String getVigencia() {
    return vigencia;
  }
  
  /**
   *  Metodo para establecer el atributo de escuelaPropietaria del objeto de Plan de Estudio
   * @param pEscuelaPropietaria Es la escuela propietaria
   */
  public void setEscuelaPropietaria(String pEscuelaPropietaria) {
    escuelaPropietaria=pEscuelaPropietaria;
  }
  
  /**
   * Metodo para retornar el atributo de escuelaPropietaria del objeto de Plan de Estudio
   * @return escuelaPropietaria
   */
  public String getEscuelaPropietaria() {
    return escuelaPropietaria;
  }
  
   /**
   * Metodo para establecer el atributo de cursosTotales del objeto de Plan de Estudio
   * @param pCursosTotales Los cursos totales
   */
  public void setCursosTotales(int pCursosTotales){
    cursosTotales=pCursosTotales;
  }
  
  /**
   * Metodo para retornar el atributo de cursosTotales del objeto de Plan de Estudio
   * @return CursosTotales Los cursos totales 
   */
  public int getCursosTotales() {
    return cursosTotales;
  }
  
  /**
   *  Metodo para establecer el atributo de creditosTotales del objeto de Plan de Estudio
   * @param pCreditosTotales Los creditos totales del plan de estudio
   */
  public void setCreditosTotales(int pCreditosTotales) {
    creditosTotales = pCreditosTotales;
  } 
  
  /**
   *  Metodo para retornar el atributo de creditosTotales del objeto de Plan de Estudio
   * @return creditosTotales Los creditos totales del plan
   */
  public int getCreditosTotales() {
    return creditosTotales;
  } 
  
  /**
   * Metodo que retorna info del plan de estudio
   * @return retorna un toString
   */
  public String toString() {
    String msg = "";
    msg = "NumeroPlan="+numeroPlanEstudio+
      "Fechavigencia="+vigencia+
      "EscuelaPropietaria="+escuelaPropietaria+
      "CursosTotales"+cursosTotales+
      "CreditosTotales="+creditosTotales;  
    return msg;
  }
  
}

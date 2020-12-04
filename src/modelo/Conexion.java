package modelo;

import java.sql.*;

/**
 * Conexion permite establecer un vinculo con la base de datos, en donde 
 * se guardaran los datos manejados por el programa.
 * 
 * @author AndresGutierrez_AndresBarahona
 * @version 1.0
 */
public class Conexion {
    
  private static Connection connection = null;
  private static Statement stmt = null;

  /**
   * Metodo para realizar la conexion para la base de datos
   */
  public static void connect(){
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:base.db");
      
    }
    //validando que no haya ningun error con la conexion
    catch (ClassNotFoundException | SQLException e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    //mandando el mensaje que se puso conectar con la base de datos correctamente
    System.out.println("Connection to SQLite has been established.");
    System.out.println("Records created successfully");
  }
  
  /**
  * Metodo solamente para sentencias de Select, ya que la base devuelve datos.
  * @param query Es el select que se le mandará a la base de datos
  * @return los datos que devuelve la base de datos
  * @throws SQLException Excepcion por errores de conexion o de CRUDS en la base
  */
  public static ResultSet getSelectData(String query) throws SQLException{
    stmt = connection.createStatement();
    //ejecutando el query
    ResultSet resultado = stmt.executeQuery(query);
    return resultado;
  }
  
  /**
   *  Metodo para inserts, deletes y updates, que no retornan nada
   * @param query Es el select que se le mandará a la base de datos
   * @throws SQLException Excepcion por errores de conexion o de CRUDS en la base
   */
  public static void executeQuery(String query) throws SQLException{
    connect();
    stmt = connection.createStatement();
    stmt.executeUpdate(query);
    stmt.close();
    connection.close();
  }
  
  /**
   * Metodo para cerrar la conexion con la base de datos
   * @throws SQLException Excepcion por errores de conexion o de CRUDS en la base
   */
  public static void closeConnection() throws SQLException{
    //cerrando el statement
    stmt.close();
    //cerrando la conexion con la base de datos
    connection.close();
  }
}

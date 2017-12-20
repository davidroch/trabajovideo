package modelo;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Conexion {
//    public static Connection getConexion() {
//        Connection connection = null;
//        try {
//           String driverClassName = "com.mysql.jdbc.Driver";
//           String driverUrl="jdbc:mysql://localhost/tiendapelicula";
//           Class.forName(driverClassName);
//           connection = DriverManager.getConnection(driverUrl, "root","12341234");
//           System.out.println("Conexion exitosa!");
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.err.println(e.getMessage());
//        }
//        return connection;
//    }
//}


import java.sql.*;
import java.util.*;

/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Conexion {

    /* DATOS PARA LA CONEXION */
    /**
     * base de datos por defecto es test
     */
    private String db = "tiendapelicula";
    /**
     * usuario
     */
    private String user = "root";
    /**
     * contraseña de MySql
     */
    private String password = "8462";
    /**
     * Cadena de conexion
     */
    private String url = "jdbc:mysql://localhost:3306" + db;
    /**
     * variable para trabajar con la conexion a la base de datos
     */
    private Connection conn = null;

    /**
     * Constructor de clase
     */
    public Conexion() {
        this.url = "jdbc:mysql://localhost/" + this.db;
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos la conexión
            conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public Connection getConexion() {
        return this.conn;
    }

}

package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import org.apache.log4j.Logger;
//public static final Logger logger = Logger.getLogger(BD.class);
//logger.info("Conexion fallida");

public class BD {
    public static final Logger logger = Logger.getLogger(BD.class);
    private static final String SQL_TABLE_CREATE = "DROP TABLE IF EXISTS ODONTOLOGOS;" +
            "CREATE TABLE ODONTOLOGOS (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR(100) NOT NULL," +
            "APELLIDO VARCHAR(100) NOT NULL," +
            "MATRICULA BIGINT NOT NULL)";
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/evaluacion", "sa", "sa");
    }

    public static void createTable(){
        Connection connection = null;


        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            logger.info("Conexion existosa");
            statement.execute(SQL_TABLE_CREATE);
        } catch (Exception e){
            e.printStackTrace();
            logger.info("Conexion fallida");
        } finally {
            try{
                connection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
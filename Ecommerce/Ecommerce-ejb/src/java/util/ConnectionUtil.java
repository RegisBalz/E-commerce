package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection con;
    
    public static Connection getConnection() throws Exception {
        if (con == null) {
            try {
                Class.forName("org.firebirdsql.jdbc.FBDriver");
            } catch (ClassNotFoundException ex) {
                throw new Exception(ex);
            }
            
            String ip = "192.168.2.104";
            String database = "C:\\Users\\User\\Documents\\Banco de Dados\\ecommerce.fdb";
            String url = "jdbc:firebirdsql:" + ip + "/3050:" + database + "?encoding=WIN1252";
            String user = "SYSDBA";
            String password = "masterkey";
            
            try {
                con = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                throw new Exception(ex);
            }
        }
        
        return con;
    }
}

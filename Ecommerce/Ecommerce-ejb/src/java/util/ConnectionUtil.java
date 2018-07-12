package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionUtil {
    private static Connection con;
    
    public static Connection getConnection() throws Exception {
        InitialContext ctx;
        Connection con = null;

        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/Ecommerce-pool");

            con = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            throw new Exception("Erro de conexão ao banco de dados! Verifique o log do aplicativo.", ex);
        }

        return con;
//        if (con == null) {
//            try {
//                Class.forName("org.firebirdsql.jdbc.FBDriver");
//            } catch (ClassNotFoundException ex) {
//                throw new Exception(ex);
//            }
//            
//            String ip = "192.168.21.49";
//            String database = "C:\\Users\\User\\Documents\\Banco de Dados\\ecommerce.fdb";
//            String url = "jdbc:firebirdsql:" + ip + "/3050:" + database + "?encoding=WIN1252";
//            String user = "SYSDBA";
//            String password = "masterkey";
//            
//            try {
//                con = DriverManager.getConnection(url, user, password);
//            } catch (SQLException ex) {
//                throw new Exception(ex);
//            }
//        }
//        
//        return con;
    }
}

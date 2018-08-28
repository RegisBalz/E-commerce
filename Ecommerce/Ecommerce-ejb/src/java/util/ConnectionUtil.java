package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static Connection connection;
    
    public static Connection getConnection() throws Exception {
        
        if (connection == null) {
            try {
                Class.forName("org.firebirdsql.jdbc.FBDriver");
            } catch (ClassNotFoundException ex) {
                throw new Exception(ex);
            }
            
            String ip = "192.168.2.105";
            String database = "C:\\Users\\User\\Documents\\Banco de Dados\\ecommerce.fdb";
            String url = "jdbc:firebirdsql:" + ip + "/3050:" + database + "?encoding=WIN1252";
            String usuario = "SYSDBA";
            String senha = "masterkey";
            
            try {
                connection = DriverManager.getConnection(url, usuario, senha);
            } catch (SQLException ex) {
                throw new Exception(ex);
            }
        }
        
        return connection;
    }
    
//    public static Connection getConnection() throws Exception {
//        InitialContext ctx;
//        Connection con = null;
//
//        try {
//            ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup("jdbc/Ecommerce-pool");
//
//            con = ds.getConnection();
//        } catch (NamingException | SQLException ex) {
//            throw new Exception("Erro de conexão ao banco de dados! Verifique o log do aplicativo.", ex);
//        }
//
//        return con;
//    }
    
    public static void main(String[] args) {
        System.out.println("Testando conexão...");
        
        try {
            System.out.println("OK: " + ConnectionUtil.getConnection());
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Login;
import util.ConnectionUtil;

public class LoginDAO {
    private Connection con;
    
    public LoginDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Login login) throws Exception {
        String SQL = "INSERT INTO LOGIN(USUARIO, SENHA) VALUES(?, ?)";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, login.getUsuario());
            p.setString(2, login.getSenha());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Login login) throws Exception {
        String SQL = "UPDATE LOGIN SET USUARIO=?, SENHA=? WHERE LOGIN_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, login.getUsuario());
            p.setString(2, login.getSenha());
            p.setInt(3, login.getLoginId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Login login) throws Exception {
        String SQL = "DELETE FROM LOGIN WHERE LOGIN_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, login.getLoginId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Login> findAll() throws Exception {
        List<Login> list = new ArrayList<>();
        Login objeto;
        
        String SQL = "SELECT * FROM LOGIN";
        
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                objeto = new Login();
                objeto.setLoginId(rs.getInt("login_id"));
                objeto.setUsuario(rs.getString("usuario"));
                objeto.setSenha(rs.getString("senha"));
                list.add(objeto);
            }
            
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        return list;
    }
}
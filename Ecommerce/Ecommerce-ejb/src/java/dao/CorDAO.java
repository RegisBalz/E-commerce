package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cor;
import util.ConnectionUtil;

public class CorDAO {
    private Connection con;
    
    public CorDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Cor cor) throws Exception {
        String SQL = "INSERT INTO COR(COR) VALUES(?)";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, cor.getCor());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Cor cor) throws Exception {
        String SQL = "UPDATE COR SET COR=? WHERE COR_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, cor.getCor());
            p.setInt(2, cor.getCorId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Cor cor) throws Exception {
        String SQL = "DELETE FROM COR WHERE COR_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, cor.getCorId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Cor> findAll() throws Exception {
        List<Cor> list = new ArrayList<>();
        Cor objeto;
        
        String SQL = "SELECT * FROM COR";
        
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                objeto = new Cor();
                objeto.setCorId(rs.getInt("cor_id"));
                objeto.setCor(rs.getString("cor"));
                list.add(objeto);
            }
            
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        return list;
    }
    
    public Cor findById(int id) throws Exception {
        try {
            String SQL = "SELECT * FROM COR WHERE COR_ID=?";
            
            Cor cor = new Cor();
            
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, id);
            
            ResultSet rs = p.executeQuery();
            
            if (rs.next()) {
                cor.setCorId(rs.getInt("cor_id"));
                cor.setCor(rs.getString("cor"));
            }
            
            con.close();
            
            return cor;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Verifique o log do aplicativo.", ex);
        }
    }
}
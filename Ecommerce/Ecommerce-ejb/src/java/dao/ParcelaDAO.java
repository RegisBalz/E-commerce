package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Parcela;
import util.ConnectionUtil;

public class ParcelaDAO {
    private Connection con;
    
    public ParcelaDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Parcela parcela) throws Exception {
        String SQL = "INSERT INTO PARCELA(PARCELA) VALUES(?)";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, parcela.getParcela());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Parcela parcela) throws Exception {
        String SQL = "UPDATE PARCELA SET PARCELA=? WHERE PARCELA_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, parcela.getParcela());
            p.setInt(2, parcela.getParcelaId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Parcela parcela) throws Exception {
        String SQL = "DELETE FROM PARCELA WHERE PARCELA_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, parcela.getParcelaId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Parcela> findAll() throws Exception {
        List<Parcela> list = new ArrayList<>();
        Parcela objeto;
        
        String SQL = "SELECT * FROM PARCELA";
        
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                objeto = new Parcela();
                objeto.setParcelaId(rs.getInt("parcela_id"));
                objeto.setParcela(rs.getString("parcela"));
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
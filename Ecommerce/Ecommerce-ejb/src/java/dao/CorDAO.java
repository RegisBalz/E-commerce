package dao;

import dto.CorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cor;
import util.ConnectionUtil;

public class CorDAO {
    
    Connection con;
    
    public CorDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Cor cor) throws Exception {
        String sql = "INSERT INTO COR (COR) VALUES (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cor.getCor());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Cor cor) throws Exception {
        String sql = "UPDATE COR SET COR=? WHERE COR_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cor.getCor());
            ps.setInt(2, cor.getCorId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Cor cor) throws Exception {
        String sql = "DELETE FROM COR WHERE COR_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cor.getCorId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<CorDTO> findAll() throws Exception {
        List<CorDTO> list = new ArrayList<>();
        CorDTO objeto;
        
        String sql = "SELECT * FROM COR";
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    objeto = new CorDTO();
                    objeto.setCorId(rs.getInt("cor_id"));
                    objeto.setCor(rs.getString("cor"));
                    list.add(objeto);
                }
                
                rs.close();
                ps.close();
            }
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        return list;
    }
}

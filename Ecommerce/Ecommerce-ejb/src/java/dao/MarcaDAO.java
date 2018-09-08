package dao;

import dto.MarcaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Marca;
import util.ConnectionUtil;

public class MarcaDAO {
    
    Connection con;
    
    public MarcaDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Marca marca) throws Exception {
        String sql = "INSERT INTO MARCA (MARCA_NOME) VALUES (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, marca.getMarcaNome());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Marca marca) throws Exception {
        String sql = "UPDATE MARCA SET MARCA_NOME=? WHERE MARCA_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, marca.getMarcaNome());
            ps.setInt(2, marca.getMarcaId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Marca marca) throws Exception {
        String sql = "DELETE FROM MARCA WHERE MARCA_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, marca.getMarcaId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<MarcaDTO> findAll() throws Exception {
        List<MarcaDTO> list = new ArrayList<>();
        MarcaDTO objeto;
        
        String sql = "SELECT * FROM MARCA";
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    objeto = new MarcaDTO();
                    objeto.setMarcaId(rs.getInt("marca_id"));
                    objeto.setMarcaNome(rs.getString("marca_nome"));
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

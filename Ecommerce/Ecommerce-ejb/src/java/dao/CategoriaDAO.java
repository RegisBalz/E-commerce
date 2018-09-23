package dao;

import dto.CategoriaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import util.ConnectionUtil;

public class CategoriaDAO {
    Connection con;
    
    public CategoriaDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Categoria categoria) throws Exception {
        String sql = "INSERT INTO CATEGORIA (CATEGORIA) VALUES (?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getCategoria());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Categoria categoria) throws Exception {
        String sql = "UPDATE CATEGORIA SET CATEGORIA=? WHERE CATEGORIA_ID=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getCategoria());
            ps.setInt(2, categoria.getCategoriaId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Categoria categoria) throws Exception {
        String sql = "DELETE FROM CATEGORIA WHERE CATEGORIA_ID=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoria.getCategoriaId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<CategoriaDTO> findAll() throws Exception {
        List<CategoriaDTO> list = new ArrayList<>();
        CategoriaDTO objeto;
        
        String sql = "SELECT * FROM CATEGORIA";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                objeto = new CategoriaDTO();
                objeto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
                objeto.setCategoria(rs.getString("CATEGORIA"));
                list.add(objeto);
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        return list;
    }
}

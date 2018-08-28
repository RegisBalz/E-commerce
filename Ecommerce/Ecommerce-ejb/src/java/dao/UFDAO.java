package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UF;
import util.ConnectionUtil;

public class UFDAO {
    
    Connection con;
    
    public UFDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(UF uf) throws Exception {
        String sql = "INSERT INTO UF (UF, NOME_ESTADO) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uf.getUf());
            ps.setString(2, uf.getNomeEstado());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(UF uf) throws Exception {
        String sql = "UPDATE UF SET UF=?, NOME_ESTADO=? WHERE UF_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uf.getUf());
            ps.setString(2, uf.getNomeEstado());
            ps.setInt(3, uf.getUfId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(UF uf) throws Exception {
        String sql = "DELETE FROM UF WHERE UF_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, uf.getUfId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<UF> findAll() throws Exception {
        List<UF> list = new ArrayList<>();
        UF objeto;
        String sql = "SELECT * FROM UF";
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    objeto = new UF();
                    objeto.setUfId(rs.getInt("UF_ID"));
                    objeto.setUf(rs.getString("UF"));
                    objeto.setNomeEstado(rs.getString("NOME_ESTADO"));
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

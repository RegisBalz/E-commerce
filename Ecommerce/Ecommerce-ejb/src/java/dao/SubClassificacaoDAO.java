package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SubClassificacao;
import util.ConnectionUtil;

public class SubClassificacaoDAO {
    
    Connection con;
    
    public SubClassificacaoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(SubClassificacao subClass) throws Exception {
        String sql = "INSERT INTO SUB_CLASSIFICACAO (SUB_CLASSIFICACAO) VALUES (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subClass.getSubClassificacao());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(SubClassificacao subClass) throws Exception {
        String sql = "UPDATE SUB_CLASSIFICACAO SET SUB_CLASSIFICACAO=? WHERE SUB_CLASS_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subClass.getSubClassificacao());
            ps.setInt(2, subClass.getSubClassId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(SubClassificacao subClass) throws Exception {
        String sql = "DELETE FROM SUB_CLASSIFICACAO WHERE SUB_CLASS_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, subClass.getSubClassId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<SubClassificacao> findAll() throws Exception {
        List<SubClassificacao> list = new ArrayList<>();
        SubClassificacao objeto;
        
        String sql = "SELECT * FROM SUB_CLASSIFICACAO";
        
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    objeto = new SubClassificacao();
                    objeto.setSubClassId(rs.getInt("sub_class_id"));
                    objeto.setSubClassificacao(rs.getString("sub_classificacao"));
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

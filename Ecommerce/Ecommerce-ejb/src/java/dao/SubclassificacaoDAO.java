package dao;

import dto.SubclassificacaoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Subclassificacao;
import util.ConnectionUtil;

public class SubclassificacaoDAO {
    
    Connection con;
    
    public SubclassificacaoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Subclassificacao subclass) throws Exception {
        String sql = "INSERT INTO SUBCLASSIFICACAO (SUBCLASS_NOME) VALUES (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subclass.getSubclassNome());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Subclassificacao subclass) throws Exception {
        String sql = "UPDATE SUBCLASSIFICACAO SET SUBCLASS_NOME=? WHERE SUBCLASS_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subclass.getSubclassNome());
            ps.setInt(2, subclass.getSubclassId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Subclassificacao subclass) throws Exception {
        String sql = "DELETE FROM SUBCLASSIFICACAO WHERE SUBCLASS_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, subclass.getSubclassId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<SubclassificacaoDTO> findAll() throws Exception {
        List<SubclassificacaoDTO> list = new ArrayList<>();
        SubclassificacaoDTO objeto;
        
        String sql = "SELECT * FROM SUBCLASSIFICACAO";
        
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    objeto = new SubclassificacaoDTO();
                    objeto.setSubclassId(rs.getInt("subclass_id"));
                    objeto.setSubclassNome(rs.getString("subclass_nome"));
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

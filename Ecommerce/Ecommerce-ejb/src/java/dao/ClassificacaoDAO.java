package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Classificacao;
import model.SubClassificacao;
import util.ConnectionUtil;

public class ClassificacaoDAO {
    
    Connection con;
    
    public ClassificacaoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Classificacao classificacao) throws Exception {
        String sql = "INSERT INTO CLASSIFICACAO (SUB_CLASS_ID, CLASSIFICACAO) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classificacao.getSubClassificacao().getSubClassId());
            ps.setString(2, classificacao.getClassificacao());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Classificacao classificacao) throws Exception {
        String sql = "UPDATE CLASSIFICACAO SET SUB_CLASS_ID=?, CLASSIFICACAO=? WHERE CLASS_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classificacao.getSubClassificacao().getSubClassId());
            ps.setString(2, classificacao.getClassificacao());
            ps.setInt(3, classificacao.getClassId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Classificacao classificacao) throws Exception {
        String sql = "DELETE FROM CLASSIFICACAO WHERE CLASS_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classificacao.getClassId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public Classificacao findById(int id) throws Exception {
        Classificacao objeto = new Classificacao();
        String sql = "SELECT C.*, S.SUB_CLASSIFICACAO FROM CLASSIFICACAO C "
                + "INNER JOIN SUB_CLASSIFICACAO S ON S.SUB_CLASS_ID = C.SUB_CLASS_ID "
                + "WHERE CLASS_ID=?";
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        objeto = new Classificacao();
                        objeto.setClassId(rs.getInt("class_id"));
                        objeto.setClassificacao(rs.getString("classificacao"));
                        
                        SubClassificacao subClass = new SubClassificacao();
                        subClass.setSubClassId(rs.getInt("sub_class_id"));
                        subClass.setSubClassificacao(rs.getString("sub_classificacao"));
                        objeto.setSubClassificacao(subClass);
                    }
                    
                    rs.close();
                    ps.close();
                }
            }
        } catch (SQLException ex) {
            throw new Exception();
        }
        
        return objeto;
    }
    
    public List<Classificacao> findAll() throws Exception {
        List<Classificacao> list = new ArrayList<>();
        Classificacao objeto;
        
        String sql = "SELECT * FROM CLASSIFICACAO";
        
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    objeto = new Classificacao();
                    objeto.setClassId(rs.getInt("class_id"));
                    objeto.setClassificacao(rs.getString("classificacao"));
                    
                    SubClassificacao subClass = new SubClassificacao();
                    subClass.setSubClassId(rs.getInt("sub_class_id"));
                    subClass.setSubClassificacao(rs.getString("sub_classificacao"));
                    objeto.setSubClassificacao(subClass);
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

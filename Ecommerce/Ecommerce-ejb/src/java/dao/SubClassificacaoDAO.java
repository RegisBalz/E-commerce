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
    private Connection con;
    
    public SubClassificacaoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(SubClassificacao subClass) throws Exception {
        String SQL = "INSERT INTO SUB_CLASSIFICACAO(SUB_CLASSIFICACAO) VALUES(?)";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, subClass.getSubClassificacao());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(SubClassificacao subClass) throws Exception {
        String SQL = "UPDATE SUB_CLASSIFICACAO SET SUB_CLASSIFICACAO=? WHERE SUB_CLASS_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setString(1, subClass.getSubClassificacao());
            p.setInt(2, subClass.getSubClassId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(SubClassificacao subClass) throws Exception {
        String SQL = "DELETE FROM SUB_CLASSIFICACAO WHERE SUB_CLASS_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, subClass.getSubClassId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<SubClassificacao> findAll() throws Exception {
        List<SubClassificacao> list = new ArrayList<>();
        SubClassificacao objeto;
        
        String SQL = "SELECT * FROM SUB_CLASSIFICACAO";
        
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                objeto = new SubClassificacao();
                objeto.setSubClassId(rs.getInt("sub_class_id"));
                objeto.setSubClassificacao(rs.getString("sub_classificacao"));
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
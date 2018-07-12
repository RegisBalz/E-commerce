package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Classificacao;
import model.SubClassificacao;
import util.ConnectionUtil;

public class ClassificacaoDAO {
    private Connection con;
    
    public ClassificacaoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Classificacao classificacao) throws Exception {
        String SQL = "INSERT INTO CLASSIFICACAO(SUB_CLASS_ID, CLASSIFICACAO) VALUES(?, ?)";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, classificacao.getSubClassificacao().getSubClassId());
            p.setString(2, classificacao.getClassificacao());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Classificacao classificacao) throws Exception {
        String SQL = "UPDATE CLASSIFICACAO SET SUB_CLASS_ID=?, CLASSIFICACAO=? WHERE CLASS_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, classificacao.getSubClassificacao().getSubClassId());
            p.setString(2, classificacao.getClassificacao());
            p.setInt(3, classificacao.getClassId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Classificacao classificacao) throws Exception {
        String SQL = "DELETE FROM CLASSIFICACAO WHERE CLASS_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, classificacao.getClassId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Classificacao> findAll() throws Exception {
        List<Classificacao> list = new ArrayList<>();
        Classificacao objeto;
        
        String SQL = "SELECT * FROM CLASSIFICACAO";
        
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                objeto = new Classificacao();
                objeto.setClassId(rs.getInt("class_id"));
                
                SubClassificacao subClass = new SubClassificacao();
                subClass.setSubClassId(rs.getInt("sub_class_id"));
                subClass.setSubClassificacao(rs.getString("sub_classificacao"));
                objeto.setSubClassificacao(subClass);
                
                objeto.setClassificacao(rs.getString("classificacao"));
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
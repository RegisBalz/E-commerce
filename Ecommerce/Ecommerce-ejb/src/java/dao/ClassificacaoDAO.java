package dao;

import dto.ClassificacaoDTO;
import dto.SubclassificacaoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Classificacao;
import util.ConnectionUtil;

public class ClassificacaoDAO {
    
    Connection con;
    
    public ClassificacaoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Classificacao classificacao) throws Exception {
        String sql = "INSERT INTO CLASSIFICACAO (SUBCLASS_ID, CLASS_NOME) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classificacao.getSubclass().getSubclassId());
            ps.setString(2, classificacao.getClassNome());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Classificacao classificacao) throws Exception {
        String sql = "UPDATE CLASSIFICACAO SET SUBCLASS_ID=?, CLASS_NOME=? WHERE CLASS_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, classificacao.getSubclass().getSubclassId());
            ps.setString(2, classificacao.getClassNome());
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
    
    public ClassificacaoDTO findById(int id) throws Exception {
//        ClassificacaoDTO objeto = new ClassificacaoDTO();
//        String sql = "SELECT C.*, S.SUB_CLASSIFICACAO FROM CLASSIFICACAO C "
//                + "INNER JOIN SUB_CLASSIFICACAO S ON S.SUB_CLASS_ID = C.SUB_CLASS_ID "
//                + "WHERE CLASS_ID=?";
//        try {
//            try ( PreparedStatement ps = con.prepareStatement(sql)) {
//                ps.setInt(1, id);
//                try (ResultSet rs = ps.executeQuery()) {
//                    while(rs.next()) {
//                        objeto = new ClassificacaoDTO();
//                        objeto.setClassId(rs.getInt("class_id"));
//                        objeto.setClassificacao(rs.getString("classificacao"));
//                        
//                        SubclassificacaoDTO subClass = new SubclassificacaoDTO();
//                        subClass.setSubClassId(rs.getInt("sub_class_id"));
//                        subClass.setSubClassNome(rs.getString("sub_classificacao"));
//                        objeto.setSubClassificacao(subClass);
//                    }
//                    
//                    rs.close();
//                    ps.close();
//                }
//            }
//        } catch (SQLException ex) {
//            throw new Exception();
//        }
//        
//        return objeto;
        return null;
    }
    
    public List<ClassificacaoDTO> findAll() throws Exception {
        List<ClassificacaoDTO> list = new ArrayList<>();
        ClassificacaoDTO objeto;
        
        String sql = "SELECT C.*, S.SUBCLASS_NOME FROM CLASSIFICACAO C "
                + "INNER JOIN SUBCLASSIFICACAO S ON S.SUBCLASS_ID = C.SUBCLASS_ID "
                + "ORDER BY CLASS_ID";
        
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    objeto = new ClassificacaoDTO();
                    objeto.setClassId(rs.getInt("class_id"));
                    objeto.setClassNome(rs.getString("class_nome"));
                    
                    SubclassificacaoDTO subclassDto = new SubclassificacaoDTO();
                    subclassDto.setSubclassId(rs.getInt("subclass_id"));
                    subclassDto.setSubclassNome(rs.getString("subclass_nome"));
                    objeto.setSubclass(subclassDto);
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

package beans;

import dao.UFDAO;
import dto.CorDTO;
import exceptions.AppException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.UF;

@Stateless
public class SessaoBean implements SessaoBeanRemote, SessaoBeanLocal {
    
    @Override
    public boolean registrarUf(int uf_id, String uf_nome, String nome_estado) 
            throws AppException {
        boolean result = false;
        
        try {
            UFDAO ufDao = new UFDAO();
            UF uf = new UF();
            uf.setUfId(uf_id);
            uf.setUf(uf_nome);
            uf.setNomeEstado(nome_estado);
            ufDao.save(uf);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public void registrarCor(CorDTO cor) throws AppException {
        String sql = "INSERT INTO COR (COR) VALUES (?)";
        
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection con = DriverManager.getConnection("jdbc:firebirdsql://localhost:3050/C:\\Users\\User\\Documents\\Banco de Dados\\ecommerce.fdb" + "?encoding=WIN1252", "SYSDBA", "masterkey");
            PreparedStatement p = con.prepareStatement(sql);
            p.setString(1, cor.getCor());
            p.execute();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void pesquisarUf(int ufId) throws AppException {
        
    }
}
package beans;

import dto.CorDTO;
import exceptions.AppException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

@Stateless
public class SessaoBean implements SessaoBeanRemote, SessaoBeanLocal {
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
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UF;
import util.ConnectionUtil;

public class UFDAO {
    private Connection con;
    
    public UFDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public UF findById(int id) throws Exception {
        try {
            String SQL = "SELECT * FROM UF WHERE UF_ID=?";
            
            UF uf = new UF();
            
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, id);
            
            ResultSet rs = p.executeQuery();
            
            if (rs.next()) {
                uf.setUfId(rs.getInt("UF_ID"));
                uf.setUf(rs.getString("UF"));
                uf.setNomeEstado(rs.getString("NOME_ESTADO"));
            }
            
            con.close();
            
            return uf;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Verifique o log do aplicativo.", ex);
        }
    }
}

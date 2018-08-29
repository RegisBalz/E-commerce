package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UF;
import util.ConnectionUtil;

public class UFDAO {
    private Connection connection;
    
    public UFDAO() throws Exception {
        connection = ConnectionUtil.getConnection();
    }
    
    public void save(UF uf) throws Exception {
        String SQL = "INSERT INTO UF (UF, NOME_ESTADO) VALUES (?, ?)";
        
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, uf.getUf());
            p.setString(2, uf.getNomeEstado());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public UF findById(int id) throws Exception {
        UF uf = new UF();
        try {
            PreparedStatement p = connection.prepareStatement("SELECT * FROM UF WHERE UF_ID=?");
            p.setInt(1, id);
            
            ResultSet rs = p.executeQuery();
            
            if (rs.next()) {
                uf.setUfId(rs.getInt("UF_ID"));
                uf.setUf(rs.getString("UF"));
                uf.setNomeEstado(rs.getString("NOME_ESTADO"));
            }
            
            connection.close();
            
            return uf;
        } catch (SQLException ex) {
            throw new Exception("Erro ao processar consulta! Verifique o log do aplicativo.", ex);
        }
    }
}
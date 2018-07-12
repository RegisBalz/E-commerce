package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Login;
import model.UF;
import model.Usuario;
import util.ConnectionUtil;

public class UsuarioDAO {
    private Connection con;
    
    public UsuarioDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Usuario usuario) throws Exception {
        String SQL = "INSERT INTO USUARIO(UF_ID, LOGIN_ID, NOME_COMPLETO, CEP, CIDADE, BAIRRO, "
                + "RUA, FONE, EMAIL, CPF) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, usuario.getUf().getUfId());
            p.setInt(2, usuario.getLogin().getLoginId());
            p.setString(3, usuario.getNomeCompleto());
            p.setInt(4, usuario.getCep());
            p.setString(5, usuario.getCidade());
            p.setString(6, usuario.getBairro());
            p.setString(7, usuario.getRua());
            p.setString(8, usuario.getFone());
            p.setString(9, usuario.getEmail());
            p.setString(10, usuario.getCpf());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Usuario usuario) throws Exception {
        String SQL = "UPDATE USUARIO SET UF_ID=?, LOGIN_ID=?, NOME_COMPLETO=?, CEP=?, CIDADE=?, "
                + "BAIRRO=?, RUA=?, FONE=?, EMAIL=?, CPF=? WHERE USUARIO_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, usuario.getUf().getUfId());
            p.setInt(2, usuario.getLogin().getLoginId());
            p.setString(3, usuario.getNomeCompleto());
            p.setInt(4, usuario.getCep());
            p.setString(5, usuario.getCidade());
            p.setString(6, usuario.getBairro());
            p.setString(7, usuario.getRua());
            p.setString(8, usuario.getFone());
            p.setString(9, usuario.getEmail());
            p.setString(10, usuario.getCpf());
            p.setInt(11, usuario.getUsuarioId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Usuario usuario) throws Exception {
        String SQL = "DELETE FROM USUARIO WHERE USUARIO_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, usuario.getUsuarioId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Usuario> findAll() throws Exception {
        List<Usuario> list = new ArrayList<>();
        Usuario objeto;
        
        String SQL = "SELECT * FROM USUARIO";
        
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                objeto = new Usuario();
                objeto.setUsuarioId(rs.getInt("usuario_id"));
                
                UF uf = new UF();
                uf.setUfId(rs.getInt("uf_id"));
                uf.setNomeEstado(rs.getString("nome_estado"));
                objeto.setUf(uf);
                
                Login login = new Login();
                login.setLoginId(rs.getInt("login_id"));
                login.setUsuario(rs.getString("usuario"));
                login.setSenha(rs.getString("senha"));
                objeto.setLogin(login);
                
                objeto.setNomeCompleto(rs.getString("nome_completo"));
                objeto.setCep(rs.getInt("cep"));
                objeto.setCidade(rs.getString("cidade"));
                objeto.setBairro(rs.getString("bairro"));
                objeto.setRua(rs.getString("rua"));
                objeto.setFone(rs.getString("fone"));
                objeto.setEmail(rs.getString("email"));
                objeto.setCpf(rs.getString("cpf"));
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
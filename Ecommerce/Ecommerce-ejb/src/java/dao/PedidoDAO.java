package dao;

import dto.PedidoDTO;
import dto.UFDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Pedido;
import util.ConnectionUtil;

public class PedidoDAO {
    Connection con;
    
    public PedidoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Pedido pedido) throws Exception {
        String sql = "INSERT INTO PEDIDO (UF_ID, CLIENTE, CPF_CNPJ, CEP, "
                + "CIDADE, BAIRRO, RUA, NUMERO, COMPLEMENTO, FONE, EMAIL, "
                + "PARCELA_QTDE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pedido.getUf().getUfId());
            ps.setString(2, pedido.getCliente());
            ps.setString(3, pedido.getCpfCnpj());
            ps.setString(4, pedido.getCep());
            ps.setString(5, pedido.getCidade());
            ps.setString(6, pedido.getBairro());
            ps.setString(7, pedido.getRua());
            ps.setString(8, pedido.getNumero());
            ps.setString(9, pedido.getComplemento());
            ps.setString(10, pedido.getFone());
            ps.setString(11, pedido.getEmail());
            ps.setInt(12, pedido.getParcelaQtde());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public PedidoDTO findById(int id) throws Exception {
        PedidoDTO objeto = new PedidoDTO();
        
        String sql = "SELECT * FROM PEDIDO WHERE PEDIDO_ID=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                objeto.setPedidoId(rs.getInt("PEDIDO_ID"));
                objeto.setCliente(rs.getString("CLIENTE"));
                objeto.setCpfCnpj(rs.getString("CPF_CNPJ"));
                objeto.setCep(rs.getString("CEP"));
                objeto.setCidade(rs.getString("CIDADE"));
                objeto.setBairro(rs.getString("BAIRRO"));
                objeto.setRua(rs.getString("RUA"));
                objeto.setNumero(rs.getString("NUMERO"));
                objeto.setComplemento(rs.getString("COMPLEMENTO"));
                objeto.setFone(rs.getString("FONE"));
                objeto.setEmail(rs.getString("EMAIL"));
                objeto.setParcelaQtde(rs.getInt("PARCELA_QTDE"));
                
                UFDTO ufDto = new UFDTO();
                ufDto.setUfId(rs.getInt("UF_ID"));
                ufDto.setNomeEstado(rs.getString("NOME_ESTADO"));
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        ItensPedidoDAO itensPedidoDao = new ItensPedidoDAO();
        objeto.setItensPedido(itensPedidoDao.findById(id));
        
        return objeto;
    }
}

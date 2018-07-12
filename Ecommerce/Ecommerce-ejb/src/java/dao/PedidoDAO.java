package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Parcela;
import model.Pedido;
import model.Produto;
import model.Usuario;
import util.ConnectionUtil;

public class PedidoDAO {
    private Connection con;
    
    public PedidoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Pedido pedido) throws Exception {
        String SQL = "INSERT INTO PEDIDO(USUARIO_ID, PRODUTO_ID, PARCELA_ID, QUANTIDADE, VALOR_TOTAL) "
                + "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, pedido.getUsuario().getUsuarioId());
            p.setInt(2, pedido.getProduto().getProdutoId());
            p.setInt(3, pedido.getParcela().getParcelaId());
            p.setInt(4, pedido.getQuantidade());
            p.setDouble(5, pedido.getValorTotal());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Pedido pedido) throws Exception {
        String SQL = "UPDATE PEDIDO SET USUARIO_ID=?, PRODUTO_ID=?, PARCELA_ID=?, QUANTIDADE=?, "
                + "VALOR_TOTAL=? WHERE PEDIDO_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, pedido.getUsuario().getUsuarioId());
            p.setInt(2, pedido.getProduto().getProdutoId());
            p.setInt(3, pedido.getParcela().getParcelaId());
            p.setInt(4, pedido.getQuantidade());
            p.setDouble(5, pedido.getValorTotal());
            p.setInt(6, pedido.getPedidoId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Pedido pedido) throws Exception {
        String SQL = "DELETE FROM PEDIDO WHERE PEDIDO_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, pedido.getPedidoId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Pedido> findAll() throws Exception {
        List<Pedido> list = new ArrayList<>();
        Pedido objeto;
        
        String SQL = "SELECT * FROM PEDIDO";
        
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                objeto = new Pedido();
                
                objeto.setPedidoId(rs.getInt("pedido_id"));
                
                Usuario usuario = new Usuario();
                usuario.setUsuarioId(rs.getInt("usuario_id"));
                objeto.setUsuario(usuario);
                
                Produto produto = new Produto();
                produto.setProdutoId(rs.getInt("produto_id"));
                objeto.setProduto(produto);
                
                Parcela parcela = new Parcela();
                parcela.setParcelaId(rs.getInt("parcela_id"));
                parcela.setParcela(rs.getString("parcela"));
                objeto.setParcela(parcela);
                
                objeto.setQuantidade(rs.getInt("quantidade"));
                objeto.setValorTotal(rs.getDouble("valor_total"));
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

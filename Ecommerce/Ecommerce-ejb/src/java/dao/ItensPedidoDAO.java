package dao;

import dto.ItensPedidoDTO;
import dto.PedidoDTO;
import dto.ProdutoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ItensPedido;
import util.ConnectionUtil;

public class ItensPedidoDAO {
    Connection con;
    
    public ItensPedidoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(ItensPedido itensPedido) throws Exception {
        String sql = "INSERT INTO ITENS_PEDIDO (PRODUTO_ID, PEDIDO_ID, "
                + "QUANTIDADE, VALOR) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itensPedido.getProduto().getProdutoId());
            ps.setInt(2, itensPedido.getPedido().getPedidoId());
            ps.setInt(3, itensPedido.getQuantidade());
            ps.setDouble(4, itensPedido.getValor());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<ItensPedidoDTO> findById(int id) throws Exception {
        List<ItensPedidoDTO> list = new ArrayList<>();
        ItensPedidoDTO objeto;
        
        String sql = "SELECT IP.*, P.PRODUTO, P.VALOR, P.DESCONTO FROM ITENS_PEDIDO IP "
                + "INNER JOIN PRODUTO P ON P.PRODUTO_ID = IP.PRODUTO_ID "
                + "WHERE IP.PEDIDO_ID=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                objeto = new ItensPedidoDTO();
                objeto.setQuantidade(rs.getInt("QUANTIDADE"));
                objeto.setValor(rs.getDouble("VALOR"));
                
                ProdutoDTO produtoDto = new ProdutoDTO();
                produtoDto.setProdutoId(rs.getInt("PRODUTO_ID"));
                produtoDto.setProduto(rs.getString("PRODUTO"));
                produtoDto.setValor(rs.getDouble("VALOR"));
                produtoDto.setDesconto(rs.getInt("DESCONTO"));
                objeto.setProduto(produtoDto);
                
                PedidoDTO pedidoDto = new PedidoDTO();
                pedidoDto.setPedidoId(rs.getInt("PEDIDO_ID"));
                
                list.add(objeto);
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        return list;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Classificacao;
import model.Cor;
import model.Produto;
import util.ConnectionUtil;

public class ProdutoDAO {
    private Connection con;
    
    public ProdutoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Produto produto) throws Exception {
        String SQL = "INSERT INTO PRODUTO(CLASS_ID, COR_ID, NOME_PRODUTO, DESCRICAO, PRECO, "
                + "MARCA, PROMOCAO, FOTO, TAMANHO, PESO, ESTOQUE) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, produto.getClassificacao().getClassId());
            p.setInt(2, produto.getCor().getCorId());
            p.setString(3, produto.getNomeProduto());
            p.setString(4, produto.getDescricao());
            p.setDouble(5, produto.getPreco());
            p.setString(6, produto.getMarca());
            p.setInt(7, produto.getPromocao());
            p.setBlob(8, produto.getFoto());
            p.setString(9, produto.getTamanho());
            p.setString(10, produto.getPeso());
            p.setInt(11, produto.getEstoque());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Produto produto) throws Exception {
        String SQL = "UPDATE PRODUTO SET CLASS_ID=?, COR_ID=?, NOME_PRODUTO=?, DESCRICAO=?, PRECO=?, "
                + "MARCA=?, PROMOCAO=?, FOTO=?, TAMANHO=?, PESO=?, ESTOQUE=? "
                + "WHERE PRODUTO_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, produto.getClassificacao().getClassId());
            p.setInt(2, produto.getCor().getCorId());
            p.setString(3, produto.getNomeProduto());
            p.setString(4, produto.getDescricao());
            p.setDouble(5, produto.getPreco());
            p.setString(6, produto.getMarca());
            p.setInt(7, produto.getPromocao());
            p.setBlob(8, produto.getFoto());
            p.setString(9, produto.getTamanho());
            p.setString(10, produto.getPeso());
            p.setInt(11, produto.getEstoque());
            p.setInt(12, produto.getProdutoId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Produto produto) throws Exception {
        String SQL = "DELETE FROM PRODUTO WHERE PRODUTO_ID=?";
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            p.setInt(1, produto.getProdutoId());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<Produto> findAll() throws Exception {
        List<Produto> list = new ArrayList<>();
        Produto objeto;
        
        String SQL = "SELECT * FROM PRODUTO";
        
        try {
            PreparedStatement p = con.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                objeto = new Produto();
                
                objeto.setProdutoId(rs.getInt("produto_id"));
                
                Classificacao classificacao = new Classificacao();
                classificacao.setClassId(rs.getInt("class_id"));
                objeto.setClassificacao(classificacao);
                
                Cor cor = new Cor();
                cor.setCorId(rs.getInt("cor_id"));
                cor.setCor(rs.getString("cor"));
                objeto.setCor(cor);
                
                objeto.setNomeProduto(rs.getString("nome_produto"));
                objeto.setDescricao(rs.getString("descricao"));
                objeto.setPreco(rs.getDouble("preco"));
                objeto.setMarca(rs.getString("marca"));
                objeto.setPromocao(rs.getInt("promocao"));
                objeto.setFoto(rs.getBlob("foto"));
                objeto.setTamanho(rs.getString("tamanho"));
                objeto.setPeso(rs.getString("peso"));
                objeto.setEstoque(rs.getInt("estoque"));
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

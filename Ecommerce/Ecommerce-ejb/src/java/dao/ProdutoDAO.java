package dao;

import dto.ClassificacaoDTO;
import dto.MarcaDTO;
import dto.ProdutoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Classificacao;
import model.Marca;
import model.Produto;
import util.ConnectionUtil;

public class ProdutoDAO {
    
    Connection con;
    
    public ProdutoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Produto produto) throws Exception {
        String sql = "INSERT INTO PRODUTO (CLASS_ID, MARCA_ID, PRODUTO_NOME, "
                + "DESCRICAO, PRECO, PROMOCAO, FOTO, TAMANHO, PESO, ESTOQUE) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getClassificacao().getClassId());
            ps.setInt(2, produto.getMarca().getMarcaId());
            ps.setString(3, produto.getProdutoNome());
            ps.setString(4, produto.getDescricao());
            ps.setDouble(5, produto.getPreco());
            ps.setInt(6, produto.getPromocao());
            ps.setBlob(7, produto.getFoto());
            ps.setString(8, produto.getTamanho());
            ps.setString(9, produto.getPeso());
            ps.setInt(10, produto.getEstoque());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Produto produto) throws Exception {
        String sql = "UPDATE PRODUTO SET CLASS_ID=?, MARCA_ID=?, PRODUTO_NOME=?, "
                + "DESCRICAO=?, PRECO=?, PROMOCAO=?, FOTO=?, TAMANHO=?, PESO=?, ESTOQUE=? "
                + "WHERE PRODUTO_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getClassificacao().getClassId());
            ps.setInt(2, produto.getMarca().getMarcaId());
            ps.setString(3, produto.getProdutoNome());
            ps.setString(4, produto.getDescricao());
            ps.setDouble(5, produto.getPreco());
            ps.setInt(6, produto.getPromocao());
            ps.setBlob(7, produto.getFoto());
            ps.setString(8, produto.getTamanho());
            ps.setString(9, produto.getPeso());
            ps.setInt(10, produto.getEstoque());
            ps.setInt(11, produto.getProdutoId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Produto produto) throws Exception {
        String sql = "DELETE FROM PRODUTO WHERE PRODUTO_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getProdutoId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public List<ProdutoDTO> findAll() throws Exception {
        List<ProdutoDTO> list = new ArrayList<>();
        ProdutoDTO objeto;
        
        String sql = "SELECT P.*, C.CLASS_NOME, M.MARCA_NOME FROM PRODUTO P "
                + "INNER JOIN CLASSIFICACAO C ON C.CLASS_ID = P.CLASS_ID "
                + "INNER JOIN MARCA M ON M.MARCA_ID = P.MARCA_ID "
                + "ORDER BY PRODUTO_ID";
        
        try {
            try ( PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                
                while(rs.next()) {
                    objeto = new ProdutoDTO();
                    objeto.setProdutoId(rs.getInt("produto_id"));
                    objeto.setProdutoNome(rs.getString("produto_nome"));
                    objeto.setDescricao(rs.getString("descricao"));
                    objeto.setPreco(rs.getDouble("preco"));
                    objeto.setPromocao(rs.getInt("promocao"));
                    objeto.setFoto(rs.getBlob("foto"));
                    objeto.setTamanho(rs.getString("tamanho"));
                    objeto.setPeso(rs.getString("peso"));
                    objeto.setEstoque(rs.getInt("estoque"));
                    
                    ClassificacaoDTO classificacaoDto = new ClassificacaoDTO();
                    classificacaoDto.setClassId(rs.getInt("class_id"));
                    classificacaoDto.setClassNome(rs.getString("class_nome"));
                    objeto.setClassificacao(classificacaoDto);
                    
                    MarcaDTO marcaDto = new MarcaDTO();
                    marcaDto.setMarcaId(rs.getInt("marca_id"));
                    marcaDto.setMarcaNome(rs.getString("marca_nome"));
                    objeto.setMarca(marcaDto);
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

package dao;

import dto.CategoriaDTO;
import dto.ProdutoDTO;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import model.Produto;
import util.ConnectionUtil;

public class ProdutoDAO {
    Connection con;
    
    public ProdutoDAO() throws Exception {
        con = ConnectionUtil.getConnection();
    }
    
    public void save(Produto produto) throws Exception {
        String sql = "INSERT INTO PRODUTO (CATEGORIA_ID, PRODUTO, VALOR, FOTO, "
                + "DESCONTO, ESTOQUE, DESCRICAO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getCategoria().getCategoriaId());
            ps.setString(2, produto.getProduto());
            ps.setDouble(3, produto.getValor());
            ps.setBlob(4, new SerialBlob(produto.getFoto().getBytes()));
            ps.setInt(5, produto.getDesconto());
            ps.setInt(6, produto.getEstoque());
            ps.setString(7, produto.getDescricao());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Produto produto) throws Exception {
        String sql = "UPDATE PRODUTO SET CATEGORIA_ID=?, PRODUTO=?, VALOR=?, "
                + "FOTO=?, DESCONTO=?, ESTOQUE=?, DESCRICAO=? WHERE "
                + "PRODUTO_ID=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getCategoria().getCategoriaId());
            ps.setString(2, produto.getProduto());
            ps.setDouble(3, produto.getValor());
            ps.setBlob(4, new SerialBlob(produto.getFoto().getBytes()));
            ps.setInt(5, produto.getDesconto());
            ps.setInt(6, produto.getEstoque());
            ps.setString(7, produto.getDescricao());
            ps.setInt(8, produto.getProdutoId());
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
        
        String sql = "SELECT P.*, C.CATEGORIA FROM PRODUTO P "
                + "INNER JOIN CATEGORIA C ON C.CATEGORIA_ID = P.CATEGORIA_ID "
                + "ORDER BY P.PRODUTO_ID";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                objeto = new ProdutoDTO();
                objeto.setProdutoId(rs.getInt("PRODUTO_ID"));
                objeto.setProduto(rs.getString("PRODUTO"));
                objeto.setValor(rs.getDouble("VALOR"));
                
                Blob foto = rs.getBlob("FOTO");
                byte[] base64 = foto.getBytes(1, (int) foto.length());
                objeto.setFoto(new String(base64));
                
                objeto.setDesconto(rs.getInt("DESCONTO"));
                objeto.setEstoque(rs.getInt("ESTOQUE"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                
                CategoriaDTO categoriaDto = new CategoriaDTO();
                categoriaDto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
                categoriaDto.setCategoria(rs.getString("CATEGORIA"));
                objeto.setCategoria(categoriaDto);
                
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
    
    public ProdutoDTO findById(int id) throws Exception {
        ProdutoDTO objeto = new ProdutoDTO();
        
        String sql = "SELECT P.*, C.CATEGORIA FROM PRODUTO P "
                + "INNER JOIN CATEGORIA C ON C.CATEGORIA_ID = P.CATEGORIA_ID "
                + "WHERE P.PRODUTO_ID=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                objeto.setProdutoId(rs.getInt("PRODUTO_ID"));
                objeto.setProduto(rs.getString("PRODUTO"));
                objeto.setValor(rs.getDouble("VALOR"));
                
                Blob foto = rs.getBlob("FOTO");
                byte[] base64 = foto.getBytes(1, (int) foto.length());
                objeto.setFoto(new String(base64));
                
                objeto.setDesconto(rs.getInt("DESCONTO"));
                objeto.setEstoque(rs.getInt("ESTOQUE"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                
                CategoriaDTO categoriaDto = new CategoriaDTO();
                categoriaDto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
                categoriaDto.setCategoria(rs.getString("CATEGORIA"));
                objeto.setCategoria(categoriaDto);
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        return objeto;
    }
    
    public List<ProdutoDTO> findByCategoria(String categoria) throws Exception {
        List<ProdutoDTO> list = new ArrayList<>();
        ProdutoDTO objeto;
        
        String sql = "SELECT P.*, C.CATEGORIA FROM PRODUTO P "
                + "INNER JOIN CATEGORIA C ON C.CATEGORIA_ID = P.CATEGORIA_ID "
                + "WHERE C.CATEGORIA = UPPER (?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                objeto = new ProdutoDTO();
                objeto.setProdutoId(rs.getInt("PRODUTO_ID"));
                objeto.setProduto(rs.getString("PRODUTO"));
                objeto.setValor(rs.getDouble("VALOR"));
                
                Blob foto = rs.getBlob("FOTO");
                byte[] base64 = foto.getBytes(1, (int) foto.length());
                objeto.setFoto(new String(base64));
                
                objeto.setDesconto(rs.getInt("DESCONTO"));
                objeto.setEstoque(rs.getInt("ESTOQUE"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                
                CategoriaDTO categoriaDto = new CategoriaDTO();
                categoriaDto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
                categoriaDto.setCategoria(rs.getString("CATEGORIA"));
                objeto.setCategoria(categoriaDto);
                
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
    
    public List<ProdutoDTO> findByDescricao(String descricao) throws Exception {
        List<ProdutoDTO> list = new ArrayList<>();
        ProdutoDTO objeto;
        
        String sql = "SELECT P.*, C.CATEGORIA FROM PRODUTO P "
                + "INNER JOIN CATEGORIA C ON C.CATEGORIA_ID = P.CATEGORIA_ID "
                + "WHERE UPPER (DESCRICAO) LIKE UPPER (?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + descricao + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                objeto = new ProdutoDTO();
                objeto.setProdutoId(rs.getInt("PRODUTO_ID"));
                objeto.setProduto(rs.getString("PRODUTO"));
                objeto.setValor(rs.getDouble("VALOR"));
                
                Blob foto = rs.getBlob("FOTO");
                byte[] base64 = foto.getBytes(1, (int) foto.length());
                objeto.setFoto(new String(base64));
                
                objeto.setDesconto(rs.getInt("DESCONTO"));
                objeto.setEstoque(rs.getInt("ESTOQUE"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                
                CategoriaDTO categoriaDto = new CategoriaDTO();
                categoriaDto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
                categoriaDto.setCategoria(rs.getString("CATEGORIA"));
                objeto.setCategoria(categoriaDto);
                
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
    
    public List<ProdutoDTO> findByDesconto() throws Exception {
        List<ProdutoDTO> list = new ArrayList<>();
        ProdutoDTO objeto;
        
        String sql = "SELECT P.*, C.CATEGORIA FROM PRODUTO P "
                + "INNER JOIN CATEGORIA C ON C.CATEGORIA_ID = P.CATEGORIA_ID "
                + "WHERE P.DESCONTO > 0";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                objeto = new ProdutoDTO();
                objeto.setProdutoId(rs.getInt("PRODUTO_ID"));
                objeto.setProduto(rs.getString("PRODUTO"));
                objeto.setValor(rs.getDouble("VALOR"));
                
                Blob foto = rs.getBlob("FOTO");
                byte[] base64 = foto.getBytes(1, (int) foto.length());
                objeto.setFoto(new String(base64));
                
                objeto.setDesconto(rs.getInt("DESCONTO"));
                objeto.setEstoque(rs.getInt("ESTOQUE"));
                objeto.setDescricao(rs.getString("DESCRICAO"));
                
                CategoriaDTO categoriaDto = new CategoriaDTO();
                categoriaDto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
                categoriaDto.setCategoria(rs.getString("CATEGORIA"));
                objeto.setCategoria(categoriaDto);
                
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

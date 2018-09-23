package beans;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import dao.UFDAO;
import dto.CategoriaDTO;
import dto.ProdutoDTO;
import dto.UFDTO;
import exceptions.AppException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Categoria;
import model.Produto;
import model.UF;

@Stateless
public class SessaoBean implements SessaoBeanRemote, SessaoBeanLocal {
    
    @Override
    public boolean salvarCamposUF(String ufNome, String nomeEstado) 
            throws AppException {
        boolean result = false;
        
        try {
            UFDAO ufDao = new UFDAO();
            UF uf = new UF();
            uf.setUf(ufNome);
            uf.setNomeEstado(nomeEstado);
            ufDao.save(uf);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean atualizarCamposUF(String ufNome, String nomeEstado, int ufId) 
            throws AppException {
        boolean result = false;
        
        try {
            UFDAO ufDao = new UFDAO();
            UF uf = new UF();
            uf.setUf(ufNome);
            uf.setNomeEstado(nomeEstado);
            uf.setUfId(ufId);
            ufDao.update(uf);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean removerCamposUF(int ufId) throws AppException {
        boolean result = false;
        
        try {
            UFDAO ufDao = new UFDAO();
            UF uf = new UF();
            uf.setUfId(ufId);
            ufDao.delete(uf);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public List<UFDTO> loadTabelaUF() throws AppException {
        List<UFDTO> list = new ArrayList<>();
        
        try {
            UFDAO ufDao = new UFDAO();
            list = ufDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Houve um erro ao processar a requisição! Contate o suporte!");
        }
        
        return list;
    }
    
    @Override
    public boolean salvarCamposCategoria(String categoria) throws AppException {
        boolean result = false;
        
        try {
            CategoriaDAO catDao = new CategoriaDAO();
            Categoria cat = new Categoria();
            cat.setCategoria(categoria);
            catDao.save(cat);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean atualizarCamposCategoria(String categoria, int categoriaId) 
            throws AppException {
        boolean result = false;
        
        try {
            CategoriaDAO catDao = new CategoriaDAO();
            Categoria cat = new Categoria();
            cat.setCategoria(categoria);
            cat.setCategoriaId(categoriaId);
            catDao.update(cat);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean removerCamposCategoria(int categoriaId) throws AppException {
        boolean result = false;
        
        try {
            CategoriaDAO catDao = new CategoriaDAO();
            Categoria cat = new Categoria();
            cat.setCategoriaId(categoriaId);
            catDao.delete(cat);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public List<CategoriaDTO> loadTabelaCategoria() throws AppException {
        List<CategoriaDTO> list = new ArrayList<>();
        
        try {
            CategoriaDAO catDao = new CategoriaDAO();
            list = catDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Houve um erro ao processar a requisição! Contate o suporte!");
        }
        
        return list;
    }
    
    @Override
    public boolean salvarCamposProduto(ProdutoDTO produtoDto) 
            throws AppException {
        boolean result = false;
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            Produto produto = new Produto();
            
            Categoria cat = new Categoria();
            cat.setCategoriaId(produtoDto.getCategoria().getCategoriaId());
            produto.setCategoria(cat);
            
            produto.setProduto(produtoDto.getProduto());
            produto.setValor(produtoDto.getValor());
            produto.setFoto(produtoDto.getFoto());
            produto.setDesconto(produtoDto.getDesconto());
            produto.setEstoque(produtoDto.getEstoque());
            produto.setDescricao(produtoDto.getDescricao());
            
            produtoDao.save(produto);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean atualizarCamposProduto(ProdutoDTO produtoDto) 
            throws AppException {
        boolean result = false;
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setProdutoId(produtoDto.getProdutoId());
            
            Categoria cat = new Categoria();
            cat.setCategoriaId(produtoDto.getCategoria().getCategoriaId());
            produto.setCategoria(cat);
            
            produto.setProduto(produtoDto.getProduto());
            produto.setValor(produtoDto.getValor());
            produto.setFoto(produtoDto.getFoto());
            produto.setDesconto(produtoDto.getDesconto());
            produto.setEstoque(produtoDto.getEstoque());
            produto.setDescricao(produtoDto.getDescricao());
            
            produtoDao.update(produto);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean removerCamposProduto(int produtoId) throws AppException {
        boolean result = false;
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setProdutoId(produtoId);
            produtoDao.delete(produto);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public List<ProdutoDTO> loadTabelaProduto() throws AppException {
        List<ProdutoDTO> list = new ArrayList<>();
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            list = produtoDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Houve um erro ao processar a requisição! Contate o suporte!");
        }
        
        return list;
    }
    
    @Override
    public ProdutoDTO loadProdutoById(int produtoId) {
        ProdutoDTO produtoDto = new ProdutoDTO();
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            produtoDto = produtoDao.findById(produtoId);
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return produtoDto;
    }
    
    @Override
    public List<ProdutoDTO> loadProdutoByCateg(String categoria) {
        List<ProdutoDTO> list = new ArrayList<>();
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            list = produtoDao.findByCategoria(categoria);
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    @Override
    public List<ProdutoDTO> loadProdutoByDesconto() {
        List<ProdutoDTO> list = new ArrayList<>();
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            list = produtoDao.findByDesconto();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    @Override
    public List<ProdutoDTO> loadProdutoByDescricao(String pesquisa) {
        List<ProdutoDTO> list = new ArrayList<>();
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            list = produtoDao.findByDescricao(pesquisa);
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}

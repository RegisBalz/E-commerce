package beans;

import dto.CategoriaDTO;
import dto.ProdutoDTO;
import dto.UFDTO;
import exceptions.AppException;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface SessaoBeanRemote {
    public boolean salvarCamposUF(String ufNome, String nomeEstado) throws AppException;
    public boolean atualizarCamposUF(String ufNome, String nomeEstado, int ufId) throws AppException;
    public boolean removerCamposUF(int ufId) throws AppException;
    public List<UFDTO> loadTabelaUF() throws AppException;
    public boolean salvarCamposCategoria(String categoria) throws AppException;
    public boolean atualizarCamposCategoria(String categoria, int categoriaId) throws AppException;
    public boolean removerCamposCategoria(int categoriaId) throws AppException;
    public List<CategoriaDTO> loadTabelaCategoria() throws AppException;
    public boolean salvarCamposProduto(ProdutoDTO produtoDto) throws AppException;
    public boolean atualizarCamposProduto(ProdutoDTO produtoDto) throws AppException;
    public boolean removerCamposProduto(int produtoId) throws AppException;
    public List<ProdutoDTO> loadTabelaProduto() throws AppException;
    public ProdutoDTO loadProdutoById(int produtoId);
    public List<ProdutoDTO> loadProdutoByCateg(String categoria);
    public List<ProdutoDTO> loadProdutoByDesconto();
    public List<ProdutoDTO> loadProdutoByDescricao(String pesquisa);
}

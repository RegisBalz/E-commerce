package beans;

import dao.ClassificacaoDAO;
import dao.MarcaDAO;
import dao.SubclassificacaoDAO;
import dao.UFDAO;
import dto.ClassificacaoDTO;
import dto.MarcaDTO;
import dto.SubclassificacaoDTO;
import dto.UFDTO;
import exceptions.AppException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Classificacao;
import model.Marca;
import model.Subclassificacao;
import model.UF;

@Stateless
public class SessaoBean implements SessaoBeanRemote, SessaoBeanLocal {
    
    @Override
    public List<SubclassificacaoDTO> loadCheckboxClass() 
            throws AppException {
        List<SubclassificacaoDTO> lista = new ArrayList<>();
        
        try {
            SubclassificacaoDAO subclassDao = new SubclassificacaoDAO();
            lista = subclassDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    @Override
    public List<UFDTO> loadTabelaUF() throws AppException {
        List<UFDTO> lista = new ArrayList<>();
        
        try {
            UFDAO ufDao = new UFDAO();
            lista = ufDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Houve um erro ao processar a requisição! Contate o suporte!");
        }
        
        return lista;
    }
    
    @Override
    public List<MarcaDTO> loadTabelaMarca() throws AppException {
        List<MarcaDTO> lista = new ArrayList<>();
        
        try {
            MarcaDAO marcaDao = new MarcaDAO();
            lista = marcaDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Houve um erro ao processar a requisição! Contate o suporte!");
        }
        
        return lista;
    }
    
    @Override
    public List<SubclassificacaoDTO> loadTabelaSubclass() 
            throws AppException {
        List<SubclassificacaoDTO> lista = new ArrayList<>();
        
        try {
            SubclassificacaoDAO subclassDao = new SubclassificacaoDAO();
            lista = subclassDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Houve um erro ao processar a requisição! Contate o suporte!");
        }
        
        return lista;
    }
    
    @Override
    public List<ClassificacaoDTO> loadTabelaClass() 
            throws AppException {
        List<ClassificacaoDTO> lista = new ArrayList<>();
        
        try {
            ClassificacaoDAO classDao = new ClassificacaoDAO();
            lista = classDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
            throw new AppException("Houve um erro ao processar a requisição! Contate o suporte!");
        }
        
        return lista;
    }
    
    @Override
    public boolean salvarCamposUF(String uf_nome, String nome_estado) 
            throws AppException {
        boolean result = false;
        
        try {
            UFDAO ufDao = new UFDAO();
            UF uf = new UF();
            uf.setUfNome(uf_nome);
            uf.setNomeEstado(nome_estado);
            ufDao.save(uf);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean salvarCamposMarca(String marca_nome) 
            throws AppException {
        boolean result = false;
        
        try {
            MarcaDAO marcaDao = new MarcaDAO();
            Marca marca = new Marca();
            marca.setMarcaNome(marca_nome);
            marcaDao.save(marca);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean salvarCamposSubclass(String subclass_nome) 
            throws AppException {
        boolean result = false;
        
        try {
            SubclassificacaoDAO subclassDao = new SubclassificacaoDAO();
            Subclassificacao subclass = new Subclassificacao();
            subclass.setSubclassNome(subclass_nome);
            subclassDao.save(subclass);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean salvarCamposClass(String subclass_nome, String class_nome) 
            throws AppException {
        boolean result = false;
        
        try {
            ClassificacaoDAO classDao = new ClassificacaoDAO();
            Classificacao classificacao = new Classificacao();
            classificacao.getSubclass().setSubclassNome(subclass_nome);
            classificacao.setClassNome(class_nome);
            classDao.save(classificacao);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean atualizarCamposUF(String uf_nome, String nome_estado, int uf_id) 
            throws AppException {
        boolean result = false;
        
        try {
            UFDAO ufDao = new UFDAO();
            UF uf = new UF();
            uf.setUfNome(uf_nome);
            uf.setNomeEstado(nome_estado);
            uf.setUfId(uf_id);
            ufDao.update(uf);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean atualizarCamposMarca(String marca_nome, int marca_id) 
            throws AppException {
        boolean result = false;
        
        try {
            MarcaDAO marcaDao = new MarcaDAO();
            Marca marca = new Marca();
            marca.setMarcaNome(marca_nome);
            marca.setMarcaId(marca_id);
            marcaDao.update(marca);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean atualizarCamposSubclass(String subclass_nome, int subclass_id) 
            throws AppException {
        boolean result = false;
        
        try {
            SubclassificacaoDAO subclassDao = new SubclassificacaoDAO();
            Subclassificacao subclass = new Subclassificacao();
            subclass.setSubclassNome(subclass_nome);
            subclass.setSubclassId(subclass_id);
            subclassDao.update(subclass);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean removerCamposUF(int uf_id) throws AppException {
        boolean result = false;
        
        try {
            UFDAO ufDao = new UFDAO();
            UF uf = new UF();
            uf.setUfId(uf_id);
            ufDao.delete(uf);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean removerCamposMarca(int marca_id) throws AppException {
        boolean result = false;
        
        try {
            MarcaDAO marcaDao = new MarcaDAO();
            Marca marca = new Marca();
            marca.setMarcaId(marca_id);
            marcaDao.delete(marca);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean removerCamposSubclass(int subclass_id) throws AppException {
        boolean result = false;
        
        try {
            SubclassificacaoDAO subclassDao = new SubclassificacaoDAO();
            Subclassificacao subclass = new Subclassificacao();
            subclass.setSubclassId(subclass_id);
            subclassDao.delete(subclass);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}

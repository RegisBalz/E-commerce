package beans;

import dao.CorDAO;
import dao.UFDAO;
import dto.CorDTO;
import exceptions.AppException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import model.Cor;
import model.UF;

@Stateless
public class SessaoBean implements SessaoBeanRemote, SessaoBeanLocal {
    
    @Override
    public List<CorDTO> loadTabelaCor() throws AppException {
        List<CorDTO> lista = new ArrayList<>();
        
        try {
            CorDAO corDao = new CorDAO();
            lista = corDao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    @Override
    public boolean registrarUf(int uf_id, String uf_nome, String nome_estado) 
            throws AppException {
        boolean result = false;
        
        try {
            UFDAO ufDao = new UFDAO();
            UF uf = new UF();
            uf.setUfId(uf_id);
            uf.setUf(uf_nome);
            uf.setNomeEstado(nome_estado);
            ufDao.save(uf);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public boolean registrarCor(String corNome) throws AppException {
        boolean result = false;
        
        try {
            CorDAO corDAO = new CorDAO();
            Cor cor = new Cor();
            
            cor.setCor(corNome);
            corDAO.save(cor);
            
            result = true;
        } catch (Exception ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}

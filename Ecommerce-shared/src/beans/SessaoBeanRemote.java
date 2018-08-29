package beans;

import dto.CorDTO;
import exceptions.AppException;
import javax.ejb.Remote;

@Remote
public interface SessaoBeanRemote {
    public void registrarCor(CorDTO cor) throws AppException;
    public void pesquisarUf(int ufId) throws AppException;
    public boolean registrarUf(int uf_id, String uf, String nome_estado) throws AppException;
}
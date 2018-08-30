package beans;

import dto.CorDTO;
import exceptions.AppException;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface SessaoBeanRemote {
    public boolean registrarUf(int uf_id, String uf, String nome_estado) throws AppException;
    public boolean registrarCor(String corNome) throws AppException;
    public List<CorDTO> loadTabelaCor() throws AppException;
}

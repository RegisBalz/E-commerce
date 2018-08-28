package beans;

import dto.CorDTO;
import exceptions.AppException;
import javax.ejb.Remote;

@Remote
public interface SessaoBeanRemote {
    public void registrarCor(CorDTO cor) throws AppException;
}

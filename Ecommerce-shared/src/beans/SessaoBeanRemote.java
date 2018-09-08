package beans;

import dto.ClassificacaoDTO;
import dto.MarcaDTO;
import dto.SubclassificacaoDTO;
import dto.UFDTO;
import exceptions.AppException;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface SessaoBeanRemote {
    public List<UFDTO> loadTabelaUF() throws AppException;
    public boolean salvarCamposUF(String uf_nome, String nome_estado) throws AppException;
    public boolean removerCamposUF(int uf_id) throws AppException;
    public boolean atualizarCamposUF(String uf_nome, String nome_estado, int uf_id) throws AppException;
    public List<SubclassificacaoDTO> loadTabelaSubclass() throws AppException;
    public boolean salvarCamposSubclass(String subclass_nome) throws AppException;
    public boolean removerCamposSubclass(int subclass_id) throws AppException;
    public boolean atualizarCamposSubclass(String subclass_nome, int subclass_id) throws AppException;
    public List<ClassificacaoDTO> loadTabelaClass() throws AppException;
    public List<MarcaDTO> loadTabelaMarca() throws AppException;
    public boolean salvarCamposMarca(String marca_nome) throws AppException;
    public boolean atualizarCamposMarca(String marca_nome, int marca_id) throws AppException;
    public boolean removerCamposMarca(int marca_id) throws AppException;
    public List<SubclassificacaoDTO> loadCheckboxClass() throws AppException;
    public boolean salvarCamposClass(String subclass_nome, String class_nome) throws AppException;
}

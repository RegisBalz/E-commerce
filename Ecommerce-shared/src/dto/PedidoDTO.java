package dto;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable {
    private int pedidoId;
    private UFDTO uf;
    private String cliente;
    private String cpfCnpj;
    private String cep;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    private String fone;
    private String email;
    private int parcelaQtde;
    private List<ItensPedidoDTO> itensPedido;

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public UFDTO getUf() {
        return uf;
    }

    public void setUf(UFDTO uf) {
        this.uf = uf;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getParcelaQtde() {
        return parcelaQtde;
    }

    public void setParcelaQtde(int parcelaQtde) {
        this.parcelaQtde = parcelaQtde;
    }

    public List<ItensPedidoDTO> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItensPedidoDTO> itensPedido) {
        this.itensPedido = itensPedido;
    }
}

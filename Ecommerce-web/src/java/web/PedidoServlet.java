package web;

import beans.SessaoBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AppException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PedidoServlet extends HttpServlet {

    @EJB
    private SessaoBeanRemote bean;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        /*
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter saida = resp.getWriter();
        String content;
        
        try (BufferedReader leitor = req.getReader()) {
            content = leitor.lines().collect(Collectors.joining());
        }
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject objeto = reader.readObject();
        
        String cliente = objeto.getJsonString("cliente").getString();
        String cpf_cnpj = objeto.getJsonString("cpf_cnpj").getString();
        int estado = objeto.getJsonNumber("estado").intValue();
        int cep = objeto.getJsonNumber("cep").intValue();
        String cidade = objeto.getJsonString("cidade").getString();
        String bairro = objeto.getJsonString("bairro").getString();
        String rua = objeto.getJsonString("rua").getString();
        String numero = objeto.getJsonString("numero").getString();
        String complemento = objeto.getJsonString("complemento").getString();
        String fone = objeto.getJsonString("fone").getString();
        String email = objeto.getJsonString("email").getString();
        int parcela = objeto.getJsonNumber("parcela").intValue();
        
        try {
            bean.salvarCamposPedido(cliente, cpf_cnpj, estado, cep, cidade, bairro, rua, numero, complemento, fone, email, parcela);
        } catch (AppException ex) {
            Logger.getLogger(PedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        /*
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter saida = resp.getWriter();
        String lista = null;
        ObjectMapper mapper = new ObjectMapper();
        String condicao = req.getParameter("condicao");
        
        if ("carregarCb".equals(condicao)) {
            try {
                lista = mapper.writeValueAsString(bean.loadCbUF());
            } catch (AppException ex) {
                Logger.getLogger(PedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("itempedido".equals(condicao)) {
            
        }
        
        saida.write(lista);
        */
    }
}

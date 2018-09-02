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

public class SubclassServlet extends HttpServlet {
    
    @EJB
    private SessaoBeanRemote bean;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter saida = resp.getWriter();
        String content;
        
        try (BufferedReader leitor = req.getReader()) {
            content = leitor.lines().collect(Collectors.joining());
        }
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject objeto = reader.readObject();
        String subclass = objeto.getJsonString("sub_classificacao").getString();
        String retorno;
        
        try {
            boolean ret = bean.registrarSubclass(subclass);
            
            if (ret) {
                retorno = "O registro foi adicionado com sucesso!";
            } else {
                retorno = "O registro não foi adicionado. Houve um erro ao processar os dados!";
            }
        } catch (AppException ex) {
            retorno = ex.getMessage();
        }
        
        JsonObject json = Json.createObjectBuilder()
                .add("message", retorno)
                .build();
        
        saida.write(json.toString());
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter saida = resp.getWriter();
        String lista = null;
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            lista = mapper.writeValueAsString(bean.loadTabelaSubclass());
        } catch (AppException ex) {
            Logger.getLogger(CorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        saida.write(lista);
    }
}
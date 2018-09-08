package web;

import beans.SessaoBeanRemote;
import exceptions.AppException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveUFServlet extends HttpServlet {

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
        String uf_nome = objeto.getJsonString("uf_nome").getString();
        String nome_estado = objeto.getJsonString("nome_estado").getString();
        
        String retorno;
        boolean ret;
        
        try {
            ret = bean.salvarCamposUF(uf_nome, nome_estado);
            
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
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
        String uf_nome = objeto.getJsonString("uf_nome").getString();
        String nome_estado = objeto.getJsonString("nome_estado").getString();
        int uf_id = objeto.getJsonNumber("uf_id").intValue();
        
        String retorno;
        boolean ret;
        
        try {
            ret = bean.atualizarCamposUF(uf_nome, nome_estado, uf_id);
            
            if (ret) {
                retorno = "O registro foi atualizado com sucesso!";
            } else {
                retorno = "O registro não foi atualizado. Houve um erro ao processar os dados!";
            }
        } catch (AppException ex) {
            retorno = ex.getMessage();
        }
        
        JsonObject json = Json.createObjectBuilder()
                .add("message", retorno)
                .build();
        
        saida.write(json.toString());
    }
}

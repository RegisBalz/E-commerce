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

public class ClassificacaoServlet extends HttpServlet {

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
        String mode = objeto.getJsonString("mode").getString();
        
        String lista = null;
        ObjectMapper mapper = new ObjectMapper();
//        
//        if (mode.equals("loadSelect")) {
//            try {
//                lista = mapper.writeValueAsString(bean.loadSelectSubclass());
//            } catch (AppException ex) {
//                Logger.getLogger(ClassificacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else if (mode.equals("loadTabela")) {
//            try {
//                lista = mapper.writeValueAsString(bean.loadTabelaClassificacao());
//            } catch (AppException ex) {
//                Logger.getLogger(ClassificacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//            
        saida.write(lista);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
    }
}

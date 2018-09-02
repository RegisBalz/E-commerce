package web;

import beans.SessaoBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.AppException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
            lista = mapper.writeValueAsString(bean.loadSelect());
        } catch (AppException ex) {
            Logger.getLogger(ClassificacaoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        saida.write(lista);
    }
}

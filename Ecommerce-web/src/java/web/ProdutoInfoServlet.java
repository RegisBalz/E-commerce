package web;

import beans.SessaoBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProdutoInfoServlet extends HttpServlet {

    @EJB
    private SessaoBeanRemote bean;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter out = resp.getWriter();
        String lista;
        ObjectMapper mapper = new ObjectMapper();
        
        int id = Integer.parseInt(req.getParameter("id"));
        
        lista = mapper.writeValueAsString(bean.loadProdutoById(id));
        
        out.write(lista);
    }
}

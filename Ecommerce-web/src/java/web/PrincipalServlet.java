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

public class PrincipalServlet extends HttpServlet {
    
    @EJB
    private SessaoBeanRemote bean;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        ObjectMapper mapper = new ObjectMapper();
        String lista = null;
        
        switch (action) {
            case "load-categoria": {
                try {
                    lista = mapper.writeValueAsString(bean.loadTabelaCategoria());
                } catch (AppException ex) {
                    Logger.getLogger(PrincipalServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "load-grid-produto": {
                try {
                    lista = mapper.writeValueAsString(bean.loadTabelaProduto());
                } catch (AppException ex) {
                    Logger.getLogger(PrincipalServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "load-grid-categoria": {
                String categoria = req.getParameter("categoria");
                lista = mapper.writeValueAsString(bean.loadProdutoByCateg(categoria));
                break;
            }
            case "load-grid-desconto": {
                lista = mapper.writeValueAsString(bean.loadProdutoByDesconto());
                break;
            }
            case "load-grid-pesquisa": {
                String search = req.getParameter("search");
                lista = mapper.writeValueAsString(bean.loadProdutoByDescricao(search));
                break;
            }
        }
        
        out.write(lista);
    }
}

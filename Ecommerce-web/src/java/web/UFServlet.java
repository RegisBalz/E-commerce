package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UFServlet extends HttpServlet {
    
    Connection con;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter saida = resp.getWriter();
        
        String cor = req.getParameter("cor");
        
        try {
            PreparedStatement p = con.prepareStatement("insert into cor values (?)");
            p.setString(1, cor);
            p.execute();
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
        
        saida.write("Concluído!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        
        
    }
    
}

package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorServlet extends HttpServlet {

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
        
        PrintWriter saida = resp.getWriter();
        
        try {
            PreparedStatement p = con.prepareStatement("select * from curso");
            p.execute();
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                saida.println("<ul>");
                    saida.println("<li>" + (rs.getInt("cor_id")) + "</li>");
                    saida.println("<li>" + (rs.getString("cor")) + "</li>");
                saida.println("</ul>");
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}

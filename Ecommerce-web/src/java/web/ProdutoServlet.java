package web;

import beans.SessaoBeanRemote;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CategoriaDTO;
import dto.ProdutoDTO;
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

public class ProdutoServlet extends HttpServlet {
    
    @EJB
    private SessaoBeanRemote bean;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter out = resp.getWriter();
        String content;
        
        try (BufferedReader leitor = req.getReader()) {
            content = leitor.lines().collect(Collectors.joining());
        }
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject objeto = reader.readObject();
        String action = objeto.getJsonString("action").getString();
        
        String retorno = null;
        boolean ret;
        
        switch (action) {
            case "save": {
                ProdutoDTO produtoDto = new ProdutoDTO();
                CategoriaDTO catDto = new CategoriaDTO();
                catDto.setCategoriaId(objeto.getJsonNumber("categoria_id").intValue());
                produtoDto.setCategoria(catDto);
                
                produtoDto.setProduto(objeto.getJsonString("produto").getString());
                produtoDto.setValor(objeto.getJsonNumber("valor").intValue());
                produtoDto.setFoto(objeto.getJsonString("foto").getString());
                produtoDto.setDesconto(objeto.getJsonNumber("desconto").intValue());
                produtoDto.setEstoque(objeto.getJsonNumber("estoque").intValue());
                produtoDto.setDescricao(objeto.getJsonString("descricao").getString());
                
                try {
                    ret = bean.salvarCamposProduto(produtoDto);
                    
                    if (ret) {
                        retorno = "O registro foi adicionado com sucesso!";
                    } else {
                        retorno = "O registro não foi adicionado. Houve um erro ao processar os dados!";
                    }
                } catch (AppException ex) {
                    retorno = ex.getMessage();
                }
                
                break;
            }
            case "update": {
                ProdutoDTO produtoDto = new ProdutoDTO();
                produtoDto.setProdutoId(objeto.getJsonNumber("produto_id").intValue());
                
                CategoriaDTO catDto = new CategoriaDTO();
                catDto.setCategoriaId(objeto.getJsonNumber("categoria_id").intValue());
                produtoDto.setCategoria(catDto);
                
                produtoDto.setProduto(objeto.getJsonString("produto").getString());
                produtoDto.setValor(objeto.getJsonNumber("valor").intValue());
                produtoDto.setFoto(objeto.getJsonString("foto").getString());
                produtoDto.setDesconto(objeto.getJsonNumber("desconto").intValue());
                produtoDto.setEstoque(objeto.getJsonNumber("estoque").intValue());
                produtoDto.setDescricao(objeto.getJsonString("descricao").getString());
                
                try {
                    ret = bean.atualizarCamposProduto(produtoDto);
                    
                    if (ret) {
                        retorno = "O registro foi atualizado com sucesso!";
                    } else {
                        retorno = "O registro não foi atualizado. Houve um erro ao processar os dados!";
                    }
                } catch (AppException ex) {
                    retorno = ex.getMessage();
                }
                
                break;
            }
            case "delete": {
                int produtoId = objeto.getJsonNumber("produto_id").intValue();
                
                try {
                    ret = bean.removerCamposProduto(produtoId);
                    
                    if (ret) {
                        retorno = "O registro foi removido com sucesso!";
                    } else {
                        retorno = "O registro não foi removido. Houve um erro ao processar os dados!";
                    }
                } catch (AppException ex) {
                    retorno = ex.getMessage();
                }
                
                break;
            }
        }
        
        JsonObject json = Json.createObjectBuilder()
                .add("message", retorno)
                .build();
        
        out.write(json.toString());
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");
        
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String action = req.getParameter("action");
        String lista = null;
        
        if ("load-opt-categoria".equals(action)) {
            try {
                lista = mapper.writeValueAsString(bean.loadTabelaCategoria());
            } catch (AppException ex) {
                Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("load-produto".equals(action)) {
            try {
                lista = mapper.writeValueAsString(bean.loadTabelaProduto());
            } catch (AppException ex) {
                Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        out.write(lista);
    }
}

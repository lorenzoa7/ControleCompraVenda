package controller;

import aplicacao.Categoria;
import aplicacao.Produto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CategoriaDAO;
import model.ProdutoDAO;

@WebServlet(name = "AreaClienteController", urlPatterns = {"/AreaClienteController"})
public class AreaClienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProdutoDAO produtodao = new ProdutoDAO();
        ArrayList<Produto> meusProdutos;

        meusProdutos = produtodao.getListaDisponiveis();
        request.setAttribute("meusProdutos", meusProdutos);
        RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/area_cliente.jsp");
        mostrar.forward(request, response);
    }
}
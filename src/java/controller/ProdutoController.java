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
import model.CategoriaDAO;
import model.ProdutoDAO;

@WebServlet(name = "ProdutoController", urlPatterns = {"/ProdutoController"})
public class ProdutoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProdutoDAO produtodao = new ProdutoDAO();
        CategoriaDAO categoriadao = new CategoriaDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Produto> meusProdutos;
        ArrayList<Categoria> minhasCategorias;

        Produto produto = new Produto();
        switch (acao) {
            case "mostrar":
                meusProdutos = produtodao.getLista();
                request.setAttribute("meusProdutos", meusProdutos);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/lista_produtos_view.jsp");
                mostrar.forward(request, response);
                break;

            case "definir_categoria":
                id = Integer.parseInt(request.getParameter("id"));
                produto = produtodao.getProdutoPorID(id);
                minhasCategorias = categoriadao.getLista();

                request.setAttribute("produto", produto);
                request.setAttribute("minhasCategorias", minhasCategorias);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/definir_categoria.jsp");
                incluir.forward(request, response);
                break;

            case "liberar_venda":

                id = Integer.parseInt(request.getParameter("id"));
                produto = produtodao.getProdutoPorID(id);
                
                produto.setLiberadoVenda("S");
                produtodao.gravar(produto);

                response.sendRedirect("ProdutoController?acao=mostrar");
                
                break;

            case "restringir_venda":

                id = Integer.parseInt(request.getParameter("id"));
                produto = produtodao.getProdutoPorID(id);
                
                produto.setLiberadoVenda("N");
                produtodao.gravar(produto);

                response.sendRedirect("ProdutoController?acao=mostrar");
                
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem;
        try {
            Produto produto = new Produto();
            ProdutoDAO produtodao = new ProdutoDAO();
            
            produto = produtodao.getProdutoPorID(Integer.parseInt(request.getParameter("id")));
            produto.setId_categoria(Integer.parseInt(request.getParameter("categoria")));


            if (produtodao.gravar(produto)) {
                mensagem = "Categoria registrada com sucesso!";
            } else {
                mensagem = "Erro ao registrar categoria!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao registrar categoria!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
            rd.forward(request, response);
        }

    }
}
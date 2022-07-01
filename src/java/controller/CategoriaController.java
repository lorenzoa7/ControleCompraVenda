package controller;

import aplicacao.Categoria;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;

@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriaDAO categoriadao = new CategoriaDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Categoria> minhasCategorias;

        Categoria categoria = new Categoria();
        switch (acao) {
            case "mostrar":
                minhasCategorias = categoriadao.getLista();
                request.setAttribute("minhasCategorias", minhasCategorias);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/lista_categorias_view.jsp");
                mostrar.forward(request, response);
                break;

            case "cadastrar_categoria":
                categoria.setId(0);
                categoria.setNome("");

                request.setAttribute("categoria", categoria);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/cadastrar_categoria.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                categoria = categoriadao.getCategoriaPorID(id);

                if (categoria.getId() > 0) {
                    request.setAttribute("categoria", categoria);
                    RequestDispatcher rs = request.getRequestDispatcher("cadastrar_categoria.jsp");
                    rs.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar categoria!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                categoriadao.excluir(id);
                

                minhasCategorias = categoriadao.getLista();
                request.setAttribute("minhasCategorias", minhasCategorias);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/lista_categorias_view.jsp");
                aposexcluir.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem;
        try {
            Categoria categoria = new Categoria();

            categoria.setId(Integer.parseInt(request.getParameter("id")));
            categoria.setNome(request.getParameter("nome_categoria"));

            CategoriaDAO dao = new CategoriaDAO();

            if (dao.gravar(categoria)) {
                mensagem = "Categoria cadastrada com sucesso!";
            } else {
                mensagem = "Erro ao cadastrar categoria!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao cadastrar categoria!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
            rd.forward(request, response);
        }

    }
}
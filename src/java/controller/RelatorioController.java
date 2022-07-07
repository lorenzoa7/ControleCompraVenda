package controller;

import aplicacao.Categoria;
import aplicacao.Produto;
import aplicacao.Venda;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CategoriaDAO;
import model.ProdutoDAO;
import model.VendaDAO;

@WebServlet(name = "RelatorioController", urlPatterns = {"/RelatorioController"})
public class RelatorioController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        String papel = (String) sessao.getAttribute("logado");
        
        if (papel != null && "administrador".equals(papel)) {

            ProdutoDAO produtodao = new ProdutoDAO();
            VendaDAO vendadao = new VendaDAO();
            String acao = (String) request.getParameter("acao");
            ArrayList<Produto> meusProdutos;
            ArrayList<Venda> minhasVendas;
            ArrayList<Date> minhasDatas;
            Date primeiraData;
            int qtdProdutosVendidos;
            float qtdTotalVendas;

            switch (acao) {
                case "estoque":
                    meusProdutos = produtodao.getLista();
                    request.setAttribute("meusProdutos", meusProdutos);
                    RequestDispatcher mostrar_estoque = getServletContext().getRequestDispatcher("/relatorio_estoque.jsp");
                    mostrar_estoque.forward(request, response);
                    break;

                case "vendas":
                    minhasDatas = vendadao.getListaDeData();
                    request.setAttribute("minhasDatas", minhasDatas);
                    RequestDispatcher mostrar_vendas = getServletContext().getRequestDispatcher("/relatorio_vendas.jsp");
                    mostrar_vendas.forward(request, response);
                    break;
            }
        } else {
            String mensagem;
            if ("invalido".equals(papel)) {
                mensagem = "O usuário ou a senha estão errados!";
            } else {
                mensagem = "Você não tem permissão para acessar a área de administrador!";
            }
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_funcionario.jsp");
            rd.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        VendaDAO vendadao = new VendaDAO();
        ArrayList<Venda> minhasVendas;
        int qtdProdutosVendidos;
        float qtdTotalVendas;
        
        Date dataEscolhida = Date.valueOf(request.getParameter("data"));
        minhasVendas = vendadao.getListaPorData(dataEscolhida);
        qtdProdutosVendidos = vendadao.getQtdProdutoPorData(dataEscolhida);
        qtdTotalVendas = vendadao.getQtdValorPorData(dataEscolhida);
        
        request.setAttribute("dataEscolhida", dataEscolhida);
        request.setAttribute("minhasVendas", minhasVendas);
        request.setAttribute("qtdProdutosVendidos", qtdProdutosVendidos);
        request.setAttribute("qtdTotalVendas", qtdTotalVendas);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/relatorio_vendas_view.jsp");
        rd.forward(request, response);
    }
}
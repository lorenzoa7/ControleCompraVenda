package controller;

import aplicacao.Fornecedor;
import aplicacao.Funcionario;
import aplicacao.Produto;
import aplicacao.Compra;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FornecedorDAO;
import model.FuncionarioDAO;
import model.ProdutoDAO;
import model.CompraDAO;

@WebServlet(name = "CompraController", urlPatterns = {"/CompraController"})
public class CompraController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        String papel = (String) sessao.getAttribute("logado");
        
        if (papel != null && "comprador".equals(papel)) {
            CompraDAO compradao = new CompraDAO();
            ProdutoDAO produtodao = new ProdutoDAO();
            FornecedorDAO fornecedordao = new FornecedorDAO();
            FuncionarioDAO funcionariodao = new FuncionarioDAO();
            String acao = (String) request.getParameter("acao");
            int id;
            ArrayList<Compra> minhasCompras;
            ArrayList<Produto> meusProdutos;
            ArrayList<Fornecedor> meusFornecedores;
            ArrayList<Funcionario> meusFuncionarios;

            Compra compra = new Compra();
            switch (acao) {
                case "mostrar":
                    minhasCompras = compradao.getLista();
                    request.setAttribute("minhasCompras", minhasCompras);
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/lista_compras_view.jsp");
                    mostrar.forward(request, response);
                    break;

                case "realizar_compra":
                    compra.setId(0);
                    compra.setId(0);
                    compra.setQuantidade_compra(null);
                    compra.setData_compra(null);
                    compra.setValor_compra(null);
                    compra.setId_fornecedor(0);
                    compra.setId_produto(0);
                    compra.setId_funcionario(0);
                    compra.setFornecedor("");
                    compra.setProduto("");
                    compra.setFuncionario("");
                    meusProdutos = produtodao.getListaDisponiveis();
                    meusFornecedores = fornecedordao.getLista();
                    meusFuncionarios = funcionariodao.getLista();

                    request.setAttribute("compra", compra);
                    request.setAttribute("meusProdutos", meusProdutos);
                    request.setAttribute("meusFornecedores", meusFornecedores);
                    request.setAttribute("meusFuncionarios", meusFuncionarios);
                    RequestDispatcher incluir = getServletContext().getRequestDispatcher("/realizar_compra.jsp");
                    incluir.forward(request, response);
                    break;

                case "editar":

                    id = Integer.parseInt(request.getParameter("id"));
                    compra = compradao.getCompraPorID(id);
                    meusProdutos = produtodao.getListaDisponiveis();
                    meusFornecedores = fornecedordao.getLista();
                    meusFuncionarios = funcionariodao.getLista();

                    if (compra.getId() > 0) {
                        request.setAttribute("compra", compra);
                        request.setAttribute("meusProdutos", meusProdutos);
                        request.setAttribute("meusFornecedores", meusFornecedores);
                        request.setAttribute("meusFuncionarios", meusFuncionarios);
                        RequestDispatcher rs = request.getRequestDispatcher("realizar_compra.jsp");
                        rs.forward(request, response);
                    } else {
                        String mensagem = "Erro ao gravar compra!";
                        request.setAttribute("mensagem", mensagem);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
                        rd.forward(request, response);
                    }
                    break;

                case "excluir":

                    id = Integer.parseInt(request.getParameter("id"));
                    compradao.excluir(id);


                    minhasCompras = compradao.getLista();
                    request.setAttribute("minhasCompras", minhasCompras);
                    RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/lista_compras_view.jsp");
                    aposexcluir.forward(request, response);
                    break;
            }
        } else {
            String mensagem;
            if (papel == null) {
                mensagem = "O usuário ou a senha estão errados!";
            } else {
                mensagem = "Você não tem permissão para acessar a área de comprador!";
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
        String mensagem;
        try {
            Compra compra = new Compra();
        
            compra.setId(Integer.parseInt(request.getParameter("id")));
            System.out.println("ID OK");
            compra.setQuantidade_compra(Integer.parseInt(request.getParameter("quantidade_compra")));
            System.out.println("QTD COMPRA OK");
            compra.setData_compra(Date.valueOf(request.getParameter("data_compra")));
            System.out.println("DATA OK");
            compra.setValor_compra(Integer.parseInt(request.getParameter("valor_compra")));
            System.out.println("VALOR OK");
            compra.setId_fornecedor(Integer.parseInt(request.getParameter("fornecedor")));
            System.out.println("FORNECEDOR OK");
            compra.setId_produto(Integer.parseInt(request.getParameter("produto")));
            System.out.println("PRODUTO OK");
            compra.setId_funcionario(Integer.parseInt(request.getParameter("funcionario")));
            System.out.println("FUNCIONARIO OK");
            
            Produto produto = new ProdutoDAO().getProdutoPorID(compra.getId_produto());
            System.out.println("BUSCA POR PRODUTO OK");
            
            produto.setPrecoCompra(Double.valueOf(compra.getValor_compra()));
            System.out.println("PRECO COMPRA OK");
            produto.setQtdDisponivel(produto.getQtdDisponivel() + compra.getQuantidade_compra());
            System.out.println("QTD COMPRA OK");

            CompraDAO dao = new CompraDAO();
            System.out.println("COMPRADAO OK");
            ProdutoDAO produtodao = new ProdutoDAO();
            System.out.println("PRODUTODAO OK");

            if (dao.gravar(compra) && produtodao.gravar(produto)) {
                mensagem = "Compra realizada com sucesso!";
            } else {
                mensagem = "Erro ao realizar compra!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao realizar compra!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
            rd.forward(request, response);
        }

    }
}
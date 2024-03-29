package controller;

import aplicacao.Cliente;
import aplicacao.Funcionario;
import aplicacao.Produto;
import aplicacao.Venda;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ClienteDAO;
import model.CompraDAO;
import model.FuncionarioDAO;
import model.ProdutoDAO;
import model.VendaDAO;

@WebServlet(name = "VendaController", urlPatterns = {"/VendaController"})
public class VendaController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        String papel = (String) sessao.getAttribute("logado");
        
        if (papel != null && "vendedor".equals(papel)) {

            VendaDAO vendadao = new VendaDAO();
            ProdutoDAO produtodao = new ProdutoDAO();
            ClienteDAO clientedao = new ClienteDAO();
            FuncionarioDAO funcionariodao = new FuncionarioDAO();
            String acao = (String) request.getParameter("acao");
            int id;
            ArrayList<Venda> minhasVendas;
            ArrayList<Produto> meusProdutos;
            ArrayList<Cliente> meusClientes;
            ArrayList<Funcionario> meusFuncionarios;

            Venda venda = new Venda();
            switch (acao) {
                case "mostrar":
                    minhasVendas = vendadao.getLista();
                    request.setAttribute("minhasVendas", minhasVendas);
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/lista_vendas_view.jsp");
                    mostrar.forward(request, response);
                    break;

                case "realizar_venda":
                    venda.setId(0);
                    venda.setQuantidade_venda(null);
                    venda.setData_venda(null);
                    venda.setValor_venda(null);
                    venda.setId_cliente(0);
                    venda.setId_produto(0);
                    venda.setId_funcionario(0);
                    venda.setCliente("");
                    venda.setProduto("");
                    venda.setFuncionario("");
                    meusProdutos = produtodao.getListaDisponiveis();
                    meusClientes = clientedao.getLista();
                    meusFuncionarios = funcionariodao.getLista();

                    request.setAttribute("venda", venda);
                    request.setAttribute("meusProdutos", meusProdutos);
                    request.setAttribute("meusClientes", meusClientes);
                    request.setAttribute("meusFuncionarios", meusFuncionarios);
                    RequestDispatcher incluir = getServletContext().getRequestDispatcher("/realizar_venda.jsp");
                    incluir.forward(request, response);
                    break;

                case "editar":

                    id = Integer.parseInt(request.getParameter("id"));
                    venda = vendadao.getVendaPorID(id);
                    meusProdutos = produtodao.getListaDisponiveis();
                    meusClientes = clientedao.getLista();
                    meusFuncionarios = funcionariodao.getLista();

                    if (venda.getId() > 0) {
                        request.setAttribute("venda", venda);
                        request.setAttribute("meusProdutos", meusProdutos);
                        request.setAttribute("meusClientes", meusClientes);
                        request.setAttribute("meusFuncionarios", meusFuncionarios);
                        RequestDispatcher rs = request.getRequestDispatcher("realizar_venda.jsp");
                        rs.forward(request, response);
                    } else {
                        String mensagem = "Erro ao gravar venda!";
                        request.setAttribute("mensagem", mensagem);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_vendedor.jsp");
                        rd.forward(request, response);
                    }
                    break;

                case "excluir":

                    id = Integer.parseInt(request.getParameter("id"));
                    vendadao.excluir(id);


                    minhasVendas = vendadao.getLista();
                    request.setAttribute("minhasVendas", minhasVendas);
                    RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/lista_vendas_view.jsp");
                    aposexcluir.forward(request, response);
                    break;
            }
        } else {
            String mensagem;
            if ("invalido".equals(papel)) {
                mensagem = "O usuário ou a senha estão errados!";
            } else {
                mensagem = "Você não tem permissão para acessar a área de vendedor!";
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
            Venda venda = new Venda();
            RequestDispatcher rd;

            venda.setId(Integer.parseInt(request.getParameter("id")));
            venda.setQuantidade_venda(Integer.parseInt(request.getParameter("quantidade_venda")));
            venda.setData_venda(Date.valueOf(request.getParameter("data_venda")));
            venda.setValor_venda(Float.parseFloat(request.getParameter("valor_venda")));
            venda.setId_cliente(Integer.parseInt(request.getParameter("cliente")));
            venda.setId_produto(Integer.parseInt(request.getParameter("produto")));
            venda.setId_funcionario(Integer.parseInt(request.getParameter("funcionario")));

            VendaDAO dao = new VendaDAO();
            ProdutoDAO produtodao = new ProdutoDAO();
            
            Produto produto = produtodao.getProdutoPorID(venda.getId_produto());
            int nova_qtd_disponivel = produto.getQtdDisponivel() - venda.getQuantidade_venda();
            System.out.println("Nova qtd disponivel é " + nova_qtd_disponivel);
            if (nova_qtd_disponivel < 0) {
                mensagem = "Não existem produtos disponíveis suficientes no estoque!";
                request.setAttribute("mensagem", mensagem);
                rd = getServletContext().getRequestDispatcher("/mensagem_vendedor.jsp");
                rd.forward(request, response);
            } else {
                 produto.setQtdDisponivel(nova_qtd_disponivel);
                 produtodao.gravar(produto);
            }
           

            if (dao.gravar(venda)) {
                mensagem = "Venda realizada com sucesso!";
            } else {
                mensagem = "Erro ao realizar venda!";
            }

            request.setAttribute("mensagem", mensagem);
            rd = getServletContext().getRequestDispatcher("/mensagem_vendedor.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao realizar venda!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_vendedor.jsp");
            rd.forward(request, response);
        }

    }
}
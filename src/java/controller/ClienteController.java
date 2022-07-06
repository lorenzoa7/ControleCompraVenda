package controller;

import aplicacao.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ClienteDAO;

@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        String papel = (String) sessao.getAttribute("logado");
        
        if (papel != null && "vendedor".equals(papel)) {

            ClienteDAO clientedao = new ClienteDAO();
            String acao = (String) request.getParameter("acao");
            int id;
            ArrayList<Cliente> meusClientes;

            Cliente cliente = new Cliente();
            switch (acao) {
                case "mostrar":
                    meusClientes = clientedao.getLista();
                    request.setAttribute("meusClientes", meusClientes);
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/lista_clientes_view.jsp");
                    mostrar.forward(request, response);
                    break;

                case "cadastrar":
                    cliente.setId(0);
                    cliente.setNome("");
                    cliente.setCPF("");
                    cliente.setEndereco("");
                    cliente.setBairro("");
                    cliente.setCidade("");
                    cliente.setUF("");
                    cliente.setCEP("");
                    cliente.setTelefone("");
                    cliente.setEmail("");

                    request.setAttribute("cliente", cliente);
                    RequestDispatcher incluir = getServletContext().getRequestDispatcher("/cadastrar_cliente.jsp");
                    incluir.forward(request, response);
                    break;

                case "editar":

                    id = Integer.parseInt(request.getParameter("id"));
                    cliente = clientedao.getClientePorID(id);

                    if (cliente.getId() > 0) {
                        request.setAttribute("cliente", cliente);
                        RequestDispatcher rs = request.getRequestDispatcher("cadastrar_cliente.jsp");
                        rs.forward(request, response);
                    } else {
                        String mensagem = "Erro ao gravar cliente!";
                        request.setAttribute("mensagem", mensagem);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_vendedor.jsp");
                        rd.forward(request, response);
                    }
                    break;

                case "excluir":

                    id = Integer.parseInt(request.getParameter("id"));
                    clientedao.excluir(id);

                    meusClientes = clientedao.getLista();
                    request.setAttribute("meusClientes", meusClientes);
                    RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/lista_clientes_view.jsp");
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
            Cliente cliente = new Cliente();

            cliente.setId(Integer.parseInt(request.getParameter("id")));
            cliente.setNome(request.getParameter("nome"));
            cliente.setCPF(request.getParameter("cpf"));
            cliente.setEndereco(request.getParameter("endereco"));
            cliente.setBairro(request.getParameter("bairro"));
            cliente.setCidade(request.getParameter("cidade"));
            cliente.setUF(request.getParameter("uf"));
            cliente.setCEP(request.getParameter("cep"));
            cliente.setTelefone(request.getParameter("telefone"));
            cliente.setEmail(request.getParameter("email"));

            ClienteDAO dao = new ClienteDAO();

            if (dao.gravar(cliente)) {
                mensagem = "Cliente gravado com sucesso!";
            } else {
                mensagem = "Erro ao gravar cliente!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_vendedor.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar cliente!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_vendedor.jsp");
            rd.forward(request, response);
        }

    }
}
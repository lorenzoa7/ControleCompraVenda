package controller;

import aplicacao.Funcionario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import model.FuncionarioDAO;

@WebServlet(name = "FuncionarioController", urlPatterns = {"/FuncionarioController"})
public class FuncionarioController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String acao = (String) request.getParameter("acao");
            HttpSession sessao = request.getSession();
            String papel = (String) sessao.getAttribute("logado");
        
            if (papel != null && "administrador".equals(papel)) {
                FuncionarioDAO funcionariodao = new FuncionarioDAO();
                int id;
                ArrayList<Funcionario> meusAdmin;
                ArrayList<Funcionario> meusVendedores;
                ArrayList<Funcionario> meusCompradores;
                Funcionario funcionario = new Funcionario();
                
                switch (acao) {
                    case "sair":
                        sessao.invalidate();
                        response.sendRedirect("index.jsp");
                        break;
                    case "mostrar_admin":
                        meusAdmin = funcionariodao.getListaAdmin();
                        request.setAttribute("meusAdmin", meusAdmin);
                        RequestDispatcher mostrar_admin = getServletContext().getRequestDispatcher("/lista_admin_view.jsp");
                        mostrar_admin.forward(request, response);
                        break;
                    case "mostrar_vendedores":
                        meusVendedores = funcionariodao.getListaVendedores();
                        request.setAttribute("meusVendedores", meusVendedores);
                        RequestDispatcher mostrar_vendedores = getServletContext().getRequestDispatcher("/lista_vendedores_view.jsp");
                        mostrar_vendedores.forward(request, response);
                        break;
                    case "mostrar_compradores":
                        meusCompradores = funcionariodao.getListaCompradores();
                        request.setAttribute("meusCompradores", meusCompradores);
                        RequestDispatcher mostrar_compradores = getServletContext().getRequestDispatcher("/lista_compradores_view.jsp");
                        mostrar_compradores.forward(request, response);
                        break;           
                    case "cadastrar_admin":
                        funcionario.setId(0);
                        funcionario.setNome("");
                        funcionario.setCpf("");
                        funcionario.setSenha("");
                        funcionario.setPapel("0");

                        request.setAttribute("funcionario", funcionario);
                        RequestDispatcher incluir_admin = getServletContext().getRequestDispatcher("/cadastrar_funcionario.jsp");
                        incluir_admin.forward(request, response);
                        break;
                    case "cadastrar_vendedor":
                        funcionario.setId(0);
                        funcionario.setNome("");
                        funcionario.setCpf("");
                        funcionario.setSenha("");
                        funcionario.setPapel("1");

                        request.setAttribute("funcionario", funcionario);
                        RequestDispatcher incluir_vendedor = getServletContext().getRequestDispatcher("/cadastrar_funcionario.jsp");
                        incluir_vendedor.forward(request, response);
                        break;
                    case "cadastrar_comprador":
                        funcionario.setId(0);
                        funcionario.setNome("");
                        funcionario.setCpf("");
                        funcionario.setSenha("");
                        funcionario.setPapel("2");

                        request.setAttribute("funcionario", funcionario);
                        RequestDispatcher incluir_comprador = getServletContext().getRequestDispatcher("/cadastrar_funcionario.jsp");
                        incluir_comprador.forward(request, response);
                        break;
                    case "editar":
                        id = Integer.parseInt(request.getParameter("id"));
                        funcionario = funcionariodao.getFuncionarioPorID(id);

                        if (funcionario.getId() > 0) {
                            request.setAttribute("funcionario", funcionario);
                            RequestDispatcher rs = request.getRequestDispatcher("cadastrar_funcionario.jsp");
                            rs.forward(request, response);
                        } else {
                            String mensagem = "Erro ao gravar " + toLowerCase(funcionario.getPapelString()) + "!";
                            request.setAttribute("mensagem", mensagem);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_admin.jsp");
                            rd.forward(request, response);
                        }
                        break;
                    case "excluir_admin":
                        id = Integer.parseInt(request.getParameter("id"));
                        funcionariodao.excluir(id);

                        meusAdmin = funcionariodao.getListaAdmin();
                        request.setAttribute("meusAdmin", meusAdmin);
                        RequestDispatcher aposexcluiradmin = getServletContext().getRequestDispatcher("/lista_admin_view.jsp");
                        aposexcluiradmin.forward(request, response);
                        break;
                    case "excluir_vendedor":
                        id = Integer.parseInt(request.getParameter("id"));
                        funcionariodao.excluir(id);

                        meusVendedores = funcionariodao.getListaVendedores();
                        request.setAttribute("meusVendedores", meusVendedores);
                        RequestDispatcher aposexcluirvendedor = getServletContext().getRequestDispatcher("/lista_vendedores_view.jsp");
                        aposexcluirvendedor.forward(request, response);
                        break;
                    case "excluir_comprador":
                        id = Integer.parseInt(request.getParameter("id"));
                        funcionariodao.excluir(id);

                        meusCompradores = funcionariodao.getListaVendedores();
                        request.setAttribute("meusCompradores", meusCompradores);
                        RequestDispatcher aposexcluircomprador = getServletContext().getRequestDispatcher("/lista_compradores_view.jsp");
                        aposexcluircomprador.forward(request, response);
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
        HttpSession sessao = request.getSession();
        
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        
        FuncionarioDAO funcionariodao = new FuncionarioDAO();
        Funcionario funcionario = funcionariodao.getFuncionarioPorLogin(cpf, senha);
        
        if (funcionario.getId() != -1) {
            String papel = funcionario.getPapel();
            if ("0".equals(papel)) {
                sessao.setAttribute("logado", "administrador");
            } else if ("1".equals(papel)) {
                sessao.setAttribute("logado", "vendedor");
            } else if ("2".equals(papel)) {
                sessao.setAttribute("logado", "comprador");
            }
            
            switch (papel) {
                case "0":
                    response.sendRedirect("FuncionarioController?acao=mostrar_admin");
                    break;
                case "1":
                    response.sendRedirect("ClienteController?acao=mostrar");
                    break;
                case "2":
                    response.sendRedirect("FornecedorController?acao=mostrar");
                    break;
                }
            
        } else {
            sessao.setAttribute("logado", "invalido");
            String mensagem = "O usuário ou a senha estão errados!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_funcionario.jsp");
            rd.forward(request, response);
        }
        
    }
}

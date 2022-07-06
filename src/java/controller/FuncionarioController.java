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
import model.FuncionarioDAO;

@WebServlet(name = "FuncionarioController", urlPatterns = {"/FuncionarioController"})
public class FuncionarioController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String acao = (String) request.getParameter("acao");
            HttpSession sessao = request.getSession();
        
            switch (acao) {
                case "sair":
                    sessao.invalidate();
                    response.sendRedirect("index.jsp");
                    break;
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
                response.sendRedirect("AdminController?acao=mostrar");
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

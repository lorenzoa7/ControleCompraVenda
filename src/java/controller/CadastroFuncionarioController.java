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

@WebServlet(name = "CadastroFuncionarioController", urlPatterns = {"/CadastroFuncionarioController"})
public class CadastroFuncionarioController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem;
        try {
            Funcionario funcionario = new Funcionario();

            funcionario.setId(Integer.parseInt(request.getParameter("id")));
            funcionario.setNome(request.getParameter("nome"));
            funcionario.setCpf(request.getParameter("cpf"));
            funcionario.setSenha(request.getParameter("senha"));
            funcionario.setPapel(request.getParameter("papel"));

            FuncionarioDAO dao = new FuncionarioDAO();

            if (dao.gravar(funcionario)) {
                mensagem = funcionario.getPapelString() + " gravado com sucesso!";
            } else {
                mensagem = "Erro ao gravar " + toLowerCase(funcionario.getPapelString()) + "!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_admin.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar funcionário!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_admin.jsp");
            rd.forward(request, response);
        }

    }
}

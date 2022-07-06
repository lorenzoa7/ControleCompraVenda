package controller;

import aplicacao.Fornecedor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FornecedorDAO;

@WebServlet(name = "FornecedorController", urlPatterns = {"/FornecedorController"})
public class FornecedorController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        String papel = (String) sessao.getAttribute("logado");
        
        
        if (papel != null && "comprador".equals(papel)) {
            
            FornecedorDAO fornecedordao = new FornecedorDAO();
            String acao = (String) request.getParameter("acao");
            int id;
            ArrayList<Fornecedor> meusFornecedores;

            Fornecedor fornecedor = new Fornecedor();
            switch (acao) {
                case "mostrar":
                    meusFornecedores = fornecedordao.getLista();
                    request.setAttribute("meusFornecedores", meusFornecedores);
                    RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/lista_fornecedores_view.jsp");
                    mostrar.forward(request, response);
                    break;

                case "cadastrar":
                    fornecedor.setId(0);
                    fornecedor.setRazao_social("");
                    fornecedor.setCnpj("");
                    fornecedor.setEndereco("");
                    fornecedor.setBairro("");
                    fornecedor.setCidade("");
                    fornecedor.setUf("");
                    fornecedor.setCep("");
                    fornecedor.setTelefone("");
                    fornecedor.setEmail("");

                    request.setAttribute("fornecedor", fornecedor);
                    RequestDispatcher incluir = getServletContext().getRequestDispatcher("/cadastrar_fornecedor.jsp");
                    incluir.forward(request, response);
                    break;

                case "editar":

                    id = Integer.parseInt(request.getParameter("id"));
                    fornecedor = fornecedordao.getFornecedorPorID(id);

                    if (fornecedor.getId() > 0) {
                        request.setAttribute("fornecedor", fornecedor);
                        RequestDispatcher rs = request.getRequestDispatcher("cadastrar_fornecedor.jsp");
                        rs.forward(request, response);
                    } else {
                        String mensagem = "Erro ao gravar fornecedor!";
                        request.setAttribute("mensagem", mensagem);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
                        rd.forward(request, response);
                    }
                    break;

                case "excluir":

                    id = Integer.parseInt(request.getParameter("id"));
                    fornecedordao.excluir(id);

                    meusFornecedores = fornecedordao.getLista();
                    request.setAttribute("meusFornecedores", meusFornecedores);
                    RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/lista_fornecedores_view.jsp");
                    aposexcluir.forward(request, response);
                    break;
            }
        } else {
            String mensagem;
            if ("invalido".equals(papel)) {
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
            Fornecedor fornecedor = new Fornecedor();

            fornecedor.setId(Integer.parseInt(request.getParameter("id")));
            fornecedor.setRazao_social(request.getParameter("razao_social"));
            fornecedor.setCnpj(request.getParameter("cnpj"));
            fornecedor.setEndereco(request.getParameter("endereco"));
            fornecedor.setBairro(request.getParameter("bairro"));
            fornecedor.setCidade(request.getParameter("cidade"));
            fornecedor.setUf(request.getParameter("uf"));
            fornecedor.setCep(request.getParameter("cep"));
            fornecedor.setTelefone(request.getParameter("telefone"));
            fornecedor.setEmail(request.getParameter("email"));

            FornecedorDAO dao = new FornecedorDAO();

            if (dao.gravar(fornecedor)) {
                mensagem = "Fornecedor gravado com sucesso!";
            } else {
                mensagem = "Erro ao gravar fornecedor!";
            }

            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            mensagem = "Erro ao gravar fornecedor!";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/mensagem_comprador.jsp");
            rd.forward(request, response);
        }

    }
}
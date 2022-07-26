<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Funcionario" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_administradores.jsp" />
    
    <% Funcionario aux = (Funcionario)request.getAttribute("funcionario"); %>

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col-6">
                <h4>Cadastrar <%= aux.getPapelString() %></h4>
                  <form method="POST" action="CadastroFuncionarioController" >
                      <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                      <input type="hidden" class="form-control" name="papel" value="<%= aux.getPapel() %>">
                      
                    <div class="form-group">
                        <input type="text" class="form-control" name="nome" value="<%= aux.getNome() %>" size="30" maxlength="50" placeholder="Informe o nome" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="cpf" value="<%= aux.getCpf() %>" onkeypress="$(this).mask('000.000.000-00');" placeholder="Informe o CPF"  required />
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="senha" value="<%= aux.getSenha() %>" size="5" maxlength="10" placeholder="Informe a senha" required />
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <a href="FuncionarioController?acao=mostrar_admin" class="btn btn-outline-danger">Retornar</a>
                </form>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
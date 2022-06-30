<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Cliente" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_vendedores.jsp" />
    
    <% Cliente aux = (Cliente)request.getAttribute("cliente"); %>

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col-6">
                <h4>Cadastrar Cliente</h4>
                  <form method="POST" action="ClienteController" >
                      <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                      
                    <div class="form-group">
                        <input type="text" class="form-control" name="nome" value="<%= aux.getNome() %>" size="30" maxlength="100" placeholder="Informe o nome" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="cpf" value="<%= aux.getCPF() %>" onkeypress="$(this).mask('000.000.000-00');" placeholder="Informe o CPF"  required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="endereco" value="<%= aux.getEndereco() %>" size="100" maxlength="150" placeholder="Informe o endereço" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="bairro" value="<%= aux.getBairro() %>" size="15" maxlength="50" placeholder="Informe o bairro" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="cidade" value="<%= aux.getCidade() %>" size="15" maxlength="50" placeholder="Informe a cidade" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="uf" value="<%= aux.getUF() %>" size="2" maxlength="2" placeholder="Informe o UF" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="cep" value="<%= aux.getCEP() %>" onkeypress="$(this).mask('00000-000');" placeholder="Informe o CEP" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="telefone" value="<%= aux.getTelefone() %>" onkeypress="$(this).mask('(00) 00000-0000');" placeholder="Informe o telefone" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" value="<%= aux.getEmail() %>" size="30" maxlength="100" placeholder="Informe o e-mail" required />
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <a href="ClienteController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
                </form>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
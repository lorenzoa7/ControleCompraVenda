<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Fornecedor" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_compradores.jsp" />
    
    <% Fornecedor aux = (Fornecedor)request.getAttribute("fornecedor"); %>

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col-6">
                <h4>Cadastrar Fornecedor</h4>
                  <form method="POST" action="FornecedorController" >
                      <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                      
                    <div class="form-group">
                        <input type="text" class="form-control" name="razao_social" value="<%= aux.getRazao_social() %>" size="30" maxlength="100" placeholder="Informe a razão social" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="cnpj" value="<%= aux.getCnpj() %>" onkeypress="$(this).mask('00.000.000/0000-00');" placeholder="Informe o CNPJ"  required />
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
                        <input type="text" class="form-control" name="uf" value="<%= aux.getUf() %>" size="2" maxlength="2" placeholder="Informe o UF" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="cep" value="<%= aux.getCep() %>" size="4" maxlength="8" placeholder="Informe o CEP" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="telefone" value="<%= aux.getTelefone() %>" onkeypress="$(this).mask('(00) 00000-0000');" placeholder="Informe o telefone" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" value="<%= aux.getEmail() %>" size="30" maxlength="100" placeholder="Informe o e-mail" required />
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <a href="FornecedorController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
                </form>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
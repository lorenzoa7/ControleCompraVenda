<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Categoria" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_compradores.jsp" />
    
    <% Categoria aux = (Categoria)request.getAttribute("categoria"); %>

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col-6">
                <h4>Cadastrar Categoria</h4>
                  <form method="POST" action="CategoriaController" >
                      <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                      
                    <div class="form-group">
                        <input type="text" class="form-control" name="nome_categoria" value="<%= aux.getNome() %>" size="30" maxlength="100" placeholder="Informe o nome da categoria" required>
                    </div>
                    
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <a href="CategoriaController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
                </form>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
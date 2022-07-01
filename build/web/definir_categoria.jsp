<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Produto,aplicacao.Categoria" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_compradores.jsp" />
    
    <% Produto aux = (Produto)request.getAttribute("produto"); %>

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col-6">
                <h4>Definir Categoria para o Produto <%= aux.getNome() %> </h4>
                  <form method="POST" action="ProdutoController" >
                      <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                      
                    <select class="form-select" aria-label="Selecione a categoria" name="categoria">
                        <option selected>Selecione a categoria</option>
                        <%
                                ArrayList<Categoria> ListaCategoria = (ArrayList<Categoria>) request.getAttribute("minhasCategorias");
                                for (int i = 0; i < ListaCategoria.size(); i++) {
                                    Categoria categoriaaux = ListaCategoria.get(i);
                        %>
                        <option value="<%=categoriaaux.getId()%>"><%=categoriaaux.getNome()%></option>
                        <%
                            }
                        %>
                    </select>
                    
                    <button type="submit" class="btn btn-primary">Definir</button>
                    <a href="ProdutoController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
                </form>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
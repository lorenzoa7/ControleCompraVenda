<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Categoria" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_compradores.jsp" />

    <div class="container">
        <% if (request.getAttribute("mensagem") != null) { %>
        <div class="alert alert-success" role="alert">
            
                <%= request.getAttribute("mensagem") %>
            
        </div>
        <% } %>
        <div class="justify-content-center align-items-center row">
            <div class="col">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome da Categoria</th>
                                <th scope="col"><div class="float-right">Ações</div><br></th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Categoria> ListaCategoria = (ArrayList<Categoria>) request.getAttribute("minhasCategorias");
                                for (int i = 0; i < ListaCategoria.size(); i++) {
                                    Categoria aux = ListaCategoria.get(i);
                                    String link_editar = "CategoriaController?acao=editar&id="+aux.getId();
                                    String link_excluir = "CategoriaController?acao=excluir&id="+aux.getId();
                            %>
                            <tr>
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getNome()%></td> 
                                <td><a href="<%=link_excluir%>" class="btn btn-outline-danger float-right">Excluir</a> <a href="<%=link_editar%>" class="btn btn-outline-secondary float-right">Editar</a> 
                                </td> 

                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="justify-content-center align-items-center row">
            <a href="CategoriaController?acao=cadastrar_categoria" class="btn w-25 btn-warning">Cadastrar nova categoria</a>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Produto" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>

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
                                <th scope="col">Nome do Produto</th>
                                <th scope="col">Descrição</th>
                                <th scope="col">Categoria</th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Produto> ListaProduto = (ArrayList<Produto>) request.getAttribute("meusProdutos");
                                for (int i = 0; i < ListaProduto.size(); i++) {
                                    Produto aux = ListaProduto.get(i);
                                    
                            %>
                            <tr>
                                <td><%=aux.getNome()%></td> 
                                <td><%=aux.getDescricao()%></td> 
                                <td><%=aux.getCategoria()%></td>

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
            <a href="index.jsp" class="btn w-25 btn-warning">Retornar</a>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
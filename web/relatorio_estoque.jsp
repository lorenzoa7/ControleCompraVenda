<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Produto" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_administradores.jsp" />

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
                                <th scope="col">Nome do Produto</th>
                                <th scope="col">Descrição</th>
                                <th scope="col">Preço de Compra</th>
                                <th scope="col">Preço de Venda</th>
                                <th scope="col">Quantidade Disponível</th>
                                <th scope="col">Liberado pra Venda</th>
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
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getNome()%></td> 
                                <td><%=aux.getDescricao()%></td> 
                                <td><%=aux.getPrecoCompra()%></td>
                                <td><%=aux.getPrecoVenda()%></td>
                                <td><%=aux.getQtdDisponivel()%></td>
                                <td><%=aux.getLiberadoVenda()%></td>
                                <td><%=aux.getCategoria()%></td>
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
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
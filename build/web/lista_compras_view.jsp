<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Compra" %>
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
                                <th scope="col">Quantidade de Produtos Comprados</th>
                                <th scope="col">Data da Compra</th>
                                <th scope="col">Valor da Compra</th>
                                <th scope="col">Fornecedor</th>
                                <th scope="col">Produto</th>
                                <th scope="col">Funcionário</th>
                                <th scope="col"><div class="float-right">Ações</div><br></th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Compra> ListaCompra = (ArrayList<Compra>) request.getAttribute("minhasCompras");
                                for (int i = 0; i < ListaCompra.size(); i++) {
                                    Compra aux = ListaCompra.get(i);
                                    String link_editar = "CompraController?acao=editar&id="+aux.getId();
                                    String link_excluir = "CompraController?acao=excluir&id="+aux.getId();
                            %>
                            <tr>
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getQuantidade_compra()%></td> 
                                <td><%=aux.getData_compra()%></td> 
                                <td><%=aux.getValor_compra()%></td>
                                <td><%=aux.getFornecedor()%></td>
                                <td><%=aux.getProduto()%></td>
                                <td><%=aux.getFuncionario()%></td>
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
            <a href="CompraController?acao=realizar_compra" class="btn w-25 btn-warning">Realizar nova compra</a>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
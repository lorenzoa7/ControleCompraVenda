<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Venda" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_vendedores.jsp" />

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
                                <th scope="col">Quantidade de Produtos Vendidos</th>
                                <th scope="col">Data da Venda</th>
                                <th scope="col">Valor da Venda</th>
                                <th scope="col">Cliente</th>
                                <th scope="col">Produto</th>
                                <th scope="col">Funcionário</th>
                                <th scope="col"><div class="float-right">Ações</div><br></th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Venda> ListaVenda = (ArrayList<Venda>) request.getAttribute("minhasVendas");
                                for (int i = 0; i < ListaVenda.size(); i++) {
                                    Venda aux = ListaVenda.get(i);
                                    String link_editar = "VendaController?acao=editar&id="+aux.getId();
                                    String link_excluir = "VendaController?acao=excluir&id="+aux.getId();
                            %>
                            <tr>
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getQuantidade_venda()%></td> 
                                <td><%=aux.getData_venda()%></td> 
                                <td><%=aux.getValor_venda()%></td>
                                <td><%=aux.getCliente()%></td>
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
            <a href="VendaController?acao=realizar_venda" class="btn w-25 btn-warning">Realizar nova venda</a>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
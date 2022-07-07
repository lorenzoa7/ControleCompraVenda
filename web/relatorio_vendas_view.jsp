<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Venda" %>
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
                                <th scope="col">Quantidade da Venda</th>
                                <th scope="col">Data da Venda</th>
                                <th scope="col">Valor da Venda</th>
                                <th scope="col">Cliente</th>
                                <th scope="col">Produto</th>
                                <th scope="col">Funcionario</th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Venda> ListaVenda = (ArrayList<Venda>) request.getAttribute("minhasVendas");
                                for (int i = 0; i < ListaVenda.size(); i++) {
                                    Venda aux = ListaVenda.get(i);
                            %>
                            <tr>
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getQuantidade_venda()%></td> 
                                <td><%=aux.getData_venda()%></td> 
                                <td><%=aux.getValor_venda()%></td>
                                <td><%=aux.getCliente()%></td>
                                <td><%=aux.getProduto()%></td>
                                <td><%=aux.getFuncionario()%></td>

                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="justify-content-center align-items-center row text-center">
            <div class="col"><span>Quantidade de Produtos Vendidos: <%= request.getAttribute("qtdProdutosVendidos") %></span></div>
            <div class="col"><span>Valor Total das Vendas: <%= request.getAttribute("qtdTotalVendas") %></span></div>
        </div>
        <div class="text-center"><a href="RelatorioController?acao=vendas" class="retorna">Retorna</a></div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
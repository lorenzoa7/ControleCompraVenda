<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Produto" %>
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
                                <th scope="col">Nome do Produto</th>
                                <th scope="col">Descrição</th>
                                <th scope="col">Preço de Compra</th>
                                <th scope="col">Preço de Venda</th>
                                <th scope="col">Quantidade Disponível</th>
                                <th scope="col">Liberado pra Venda</th>
                                <th scope="col">Categoria</th>
                                <th scope="col"><div class="float-right">Ações</div><br></th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Produto> ListaProduto = (ArrayList<Produto>) request.getAttribute("meusProdutos");
                                for (int i = 0; i < ListaProduto.size(); i++) {
                                    Produto aux = ListaProduto.get(i);
                                    String link_definir = "ProdutoController?acao=definir_categoria&id="+aux.getId();
                                    String link_liberadovenda;
                                    String mensagem_liberadovenda;
                                    if (aux.getLiberadoVenda().equals("S")) {
                                        link_liberadovenda = "ProdutoController?acao=restringir_venda&id="+aux.getId();
                                        mensagem_liberadovenda = "Restringir Venda";
                                    } else {
                                        link_liberadovenda = "ProdutoController?acao=liberar_venda&id="+aux.getId();
                                        mensagem_liberadovenda = "Liberar Venda";
                                    }
                                    
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
                                <td><a href="<%=link_liberadovenda%>" class="btn btn-outline-primary float-right"><%=mensagem_liberadovenda%></a> <a href="<%=link_definir%>" class="btn btn-outline-secondary float-right">Definir Categoria</a> 
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
            <a href="CategoriaController?acao=mostrar" class="btn w-25 btn-warning">Visualizar Categorias</a>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
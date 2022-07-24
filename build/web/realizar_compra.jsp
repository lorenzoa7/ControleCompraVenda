<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Compra,aplicacao.Produto,aplicacao.Fornecedor,aplicacao.Funcionario" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_vendedores.jsp" />
    
    <% Compra aux = (Compra)request.getAttribute("compra"); %>

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col-6">
                <h4>Realizar uma Compra</h4>
                  <form method="POST" action="CompraController" >
                      <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                      
                    <select class="form-select" aria-label="Selecione um produto" name="produto">
                        <% if (aux.getId_produto() == 0) { %>
                            <option selected>Selecione um produto</option>
                        <% } %>
                        <%
                                ArrayList<Produto> ListaProduto = (ArrayList<Produto>) request.getAttribute("meusProdutos");
                                for (int i = 0; i < ListaProduto.size(); i++) {
                                    Produto produtoaux = ListaProduto.get(i);
                        %>
                        <option value="<%=produtoaux.getId()%>"><%=produtoaux.getNome()%></option>
                        <%
                            if (produtoaux.getId() == aux.getId_produto()) {
                            %>
                            <option selected value="<%=produtoaux.getId()%>"><%=produtoaux.getNome()%></option>
                            <% }
                            }
                        %>
                    </select>
                    <div class="form-group">
                        <input type="number" class="form-control" name="quantidade_compra" placeholder="Informe a quantidade de produtos" value="<%= aux.getQuantidade_compra() %>" required />
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" name="valor_compra" placeholder="Informe o valor da compra" value="<%= aux.getValor_compra() %>" required />
                    </div>
                    <div class="form-group">
                        <% if(aux.getData_compra() == null) { %>
                            <input type="text" class="form-control" name="data_venda" placeholder="Informe a data da compra" onfocus="(this.type='date')" required />
                        <% } else { %>
                        <input type="text" class="form-control" name="data_venda" placeholder="Informe a data da compra" onfocus="(this.type='date')" value="<%= aux.getData_compra() %>" required />
                        <% } %>
                    </div>
                    <select class="form-select" aria-label="Selecione o fornecedor" name="fornecedor">
                        <% if (aux.getId_fornecedor() == 0) { %>
                            <option selected>Selecione um fornecedor</option>
                        <% } %>
                        <%
                                ArrayList<Fornecedor> ListaFornecedor = (ArrayList<Fornecedor>) request.getAttribute("meusFornecedores");
                                for (int i = 0; i < ListaFornecedor.size(); i++) {
                                    Fornecedor fornecedoraux = ListaFornecedor.get(i);
                        %>
                        <option value="<%=fornecedoraux.getId()%>"><%=fornecedoraux.getRazao_social()%></option>
                        <%
                            if (fornecedoraux.getId() == aux.getId_produto()) {
                            %>
                            <option selected value="<%=fornecedoraux.getId()%>"><%=fornecedoraux.getRazao_social()%></option>
                            <% }
                            }
                        %>
                    </select>
                    <select class="form-select" aria-label="Selecione o funcionário" name="funcionario">
                        <% if (aux.getId_funcionario() == 0) { %>
                            <option selected>Selecione um funcionário</option>
                        <% } %>
                        <%
                                ArrayList<Funcionario> ListaFuncionario = (ArrayList<Funcionario>) request.getAttribute("meusFuncionarios");
                                for (int i = 0; i < ListaFuncionario.size(); i++) {
                                    Funcionario funcionarioaux = ListaFuncionario.get(i);
                        %>
                        <option value="<%=funcionarioaux.getId()%>"><%=funcionarioaux.getNome()%></option>
                        <%
                            if (funcionarioaux.getId() == aux.getId_produto()) {
                            %>
                            <option selected value="<%=funcionarioaux.getId()%>"><%=funcionarioaux.getNome()%></option>
                            <% }
                            }
                        %>
                    </select>

                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <a href="CompraController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
                </form>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
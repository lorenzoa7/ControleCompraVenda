<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Venda,aplicacao.Produto,aplicacao.Cliente,aplicacao.Funcionario" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_vendedores.jsp" />
    
    <% Venda aux = (Venda)request.getAttribute("venda"); %>

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col-6">
                <h4>Realizar uma Venda</h4>
                  <form method="POST" action="VendaController" >
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
                        <input type="number" class="form-control" name="quantidade_venda" placeholder="Informe a quantidade de produtos" value="<%= aux.getQuantidade_venda() %>"  required />
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" name="valor_venda" placeholder="Informe o valor da venda" value="<%= aux.getValor_venda() %>" required />
                    </div>
                    <div class="form-group">
                        <% if(aux.getData_venda() == null) { %>
                            <input type="text" class="form-control" name="data_venda" placeholder="Informe a data da venda" onfocus="(this.type='date')" required />
                        <% } else { %>
                        <input type="text" class="form-control" name="data_venda" placeholder="Informe a data da venda" onfocus="(this.type='date')" value="<%= aux.getData_venda() %>" required />
                        <% } %>
                    </div>
                    <select class="form-select" aria-label="Selecione o cliente" name="cliente">
                        <% if (aux.getId_cliente() == 0) { %>
                            <option selected>Selecione um cliente</option>
                        <% } %>
                        <%
                                ArrayList<Cliente> ListaCliente = (ArrayList<Cliente>) request.getAttribute("meusClientes");
                                for (int i = 0; i < ListaCliente.size(); i++) {
                                    Cliente clienteaux = ListaCliente.get(i);
                        %>
                        <option value="<%=clienteaux.getId()%>"><%=clienteaux.getNome()%></option>
                        <%
                            if (clienteaux.getId() == aux.getId_produto()) {
                            %>
                            <option selected value="<%=clienteaux.getId()%>"><%=clienteaux.getNome()%></option>
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
                    <a href="VendaController?acao=mostrar" class="btn btn-outline-danger">Retornar</a>
                </form>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
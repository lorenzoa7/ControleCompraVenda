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
                        <option selected>Selecione um produto</option>
                        <%
                                ArrayList<Produto> ListaProduto = (ArrayList<Produto>) request.getAttribute("meusProdutos");
                                for (int i = 0; i < ListaProduto.size(); i++) {
                                    Produto produtoaux = ListaProduto.get(i);
                        %>
                        <option value="<%=produtoaux.getId()%>"><%=produtoaux.getNome()%></option>
                        <%
                            }
                        %>
                    </select>
                    <div class="form-group">
                        <input type="number" class="form-control" name="quantidade_venda" placeholder="Informe a quantidade de produtos"  required />
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" name="valor_venda" placeholder="Informe o valor da venda" required />
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="data_venda" placeholder="Informe a data da venda" onfocus="(this.type='date')" required />
                    </div>
                    <select class="form-select" aria-label="Selecione o cliente" name="cliente">
                        <option selected>Selecione o cliente</option>
                        <%
                                ArrayList<Cliente> ListaCliente = (ArrayList<Cliente>) request.getAttribute("meusClientes");
                                for (int i = 0; i < ListaCliente.size(); i++) {
                                    Cliente clienteaux = ListaCliente.get(i);
                        %>
                        <option value="<%=clienteaux.getId()%>"><%=clienteaux.getNome()%></option>
                        <%
                            }
                        %>
                    </select>
                    <select class="form-select" aria-label="Selecione o funcionário" name="funcionario">
                        <option selected>Selecione o funcionário</option>
                        <%
                                ArrayList<Funcionario> ListaFuncionario = (ArrayList<Funcionario>) request.getAttribute("meusFuncionarios");
                                for (int i = 0; i < ListaFuncionario.size(); i++) {
                                    Funcionario funcionarioaux = ListaFuncionario.get(i);
                        %>
                        <option value="<%=funcionarioaux.getId()%>"><%=funcionarioaux.getNome()%></option>
                        <%
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
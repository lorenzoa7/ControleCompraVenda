<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Cliente" %>
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
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Endereço</th>
                                <th scope="col">Bairro</th>
                                <th scope="col">Cidade</th>
                                <th scope="col">UF</th>
                                <th scope="col">CEP</th>
                                <th scope="col">Telefone</th>
                                <th scope="col">Email</th>
                                <th scope="col"><div class="float-right">Ações</div><br></th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Cliente> ListaCliente = (ArrayList<Cliente>) request.getAttribute("meusClientes");
                                for (int i = 0; i < ListaCliente.size(); i++) {
                                    Cliente aux = ListaCliente.get(i);
                                    String link_editar = "ClienteController?acao=editar&id="+aux.getId();
                                    String link_excluir = "ClienteController?acao=excluir&id="+aux.getId();
                            %>
                            <tr>
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getNome()%></td> 
                                <td><%=aux.getCPF()%></td> 
                                <td><%=aux.getEndereco()%></td>
                                <td><%=aux.getBairro()%></td>
                                <td><%=aux.getCidade()%></td>
                                <td><%=aux.getUF()%></td>
                                <td><%=aux.getCEP()%></td>
                                <td><%=aux.getTelefone()%></td>
                                <td><%=aux.getEmail()%></td> 
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
            <a href="ClienteController?acao=cadastrar" class="btn w-25 btn-warning">Cadastrar novo cliente</a>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
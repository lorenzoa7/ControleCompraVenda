<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Fornecedor" %>
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
                                <th scope="col">Razão Social</th>
                                <th scope="col">CNPJ</th>
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
                                ArrayList<Fornecedor> ListaFornecedor = (ArrayList<Fornecedor>) request.getAttribute("meusFornecedores");
                                for (int i = 0; i < ListaFornecedor.size(); i++) {
                                    Fornecedor aux = ListaFornecedor.get(i);
                                    String link_editar = "FornecedorController?acao=editar&id="+aux.getId();
                                    String link_excluir = "FornecedorController?acao=excluir&id="+aux.getId();
                            %>
                            <tr>
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getRazao_social()%></td> 
                                <td><%=aux.getCnpj()%></td> 
                                <td><%=aux.getEndereco()%></td>
                                <td><%=aux.getBairro()%></td>
                                <td><%=aux.getCidade()%></td>
                                <td><%=aux.getUf()%></td>
                                <td><%=aux.getCep()%></td>
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
            <a href="FornecedorController?acao=cadastrar" class="btn w-25 btn-warning">Cadastrar novo fornecedor</a>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
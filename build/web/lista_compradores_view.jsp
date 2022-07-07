<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Funcionario" %>
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
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Senha</th>
                                <th scope="col">Papel</th>
                                <th scope="col"><div class="float-right">Ações</div><br></th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Funcionario> ListaCompradores = (ArrayList<Funcionario>) request.getAttribute("meusCompradores");
                                for (int i = 0; i < ListaCompradores.size(); i++) {
                                    Funcionario aux = ListaCompradores.get(i);
                                    String link_editar = "FuncionarioController?acao=editar&id="+aux.getId()+"&papel="+aux.getPapel();
                                    String link_excluir = "FuncionarioController?acao=excluir_comprador&id="+aux.getId();
                            %>
                            <tr>
                                <td><%=aux.getId()%></td>
                                <td><%=aux.getNome()%></td> 
                                <td><%=aux.getCpf()%></td> 
                                <td><%=aux.getSenha()%></td>
                                <td><%=aux.getPapelString()%></td>
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
            <a href="FuncionarioController?acao=cadastrar_comprador" class="btn w-25 btn-warning">Cadastrar novo comprador</a>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
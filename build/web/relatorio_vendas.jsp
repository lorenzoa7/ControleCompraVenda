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
        <div class="justify-content-center align-items-center row text-center">
            <div class="col">
                    <form method="POST" action="RelatorioController">
                        <select class="form-select" aria-label="Selecione uma data" name="data">
                            <% 
                                ArrayList<Date> ListaData = (ArrayList<Date>) request.getAttribute("minhasDatas"); 
                                for (int i = 0; i < ListaData.size(); i++) {
                                    Date data = ListaData.get(i);

                            %>
                                <option value="<%=data%>" class="text-center"><%=data%></option>
                            <%
                                }
                            %>
                        </select>
                        <button class="btn btn-warning btn-block btn-lg mt-3" type="submit">Gerar Relatório</button>
                    </form>
            </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
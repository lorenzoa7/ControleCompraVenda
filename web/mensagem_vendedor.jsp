<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.Cliente" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <jsp:include page="menu_vendedores.jsp" />

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col">
                <div class="alert alert-success" role="alert">
                    <h5>
                        <%= request.getAttribute("mensagem") %>
                    </h5>
                </div>
                <div><a href="ClienteController?acao=mostrar">Retorna</a></div>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
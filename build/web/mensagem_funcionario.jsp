<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>

    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col">
                <div class="alert alert-success" role="alert">
                    <h5>
                        <%= request.getAttribute("mensagem") %>
                    </h5>
                </div>
                <div><a href="FuncionarioController?acao=sair" class="retorna">Sair e voltar</a></div>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>

</body>
</html>
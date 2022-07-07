<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<head>
    <%@include file="cabecalho.html" %>
</head>
<body>
    <div class="container">
        <div class="justify-content-center align-items-center row">
            <div class="col-4">
                <div class="text-center mb-4">
                    <a href="AreaClienteController" class="btn btn-warning btn-block btn-lg">Área do Cliente</a>
                </div>
                <div class="form-login">
                    <div class="text-center mb-4">
                        <h1>Faça login no sistema</h2>
                    </div>

                    <form method="POST" action="FuncionarioController">
                        <input type="hidden" class="form-control" name="id" value="0">
                        
                        <div class="form-group">
                            <input class="form-control" name="cpf" type="text" placeholder="Informe o CPF" onkeypress="$(this).mask('000.000.000-00');" required autofocus />
                        </div>
                        
                        <div class="form-group">
                            <input class="form-control" type="password" name="senha" placeholder="Informe a senha do funcionário" required autofocus />
                        </div>
                                       
                        <div class="d-grid gap-2">
                            <button class="btn btn-warning btn-block btn-lg" type="submit">Entrar</button>
                        </div>
                    </form>
                </div>
                <p class="mt-5 mb-3 text-muted text-center">&copy; Todos os direitos reservados</p>
            </div>
        </div>
    </div>

    <%@include file="scripts_basicos.html" %>  

</body>
</html>
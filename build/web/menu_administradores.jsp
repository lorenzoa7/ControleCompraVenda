<header>
    <div class="container" id="nav-container">
        <nav class="navbar navbar-expand-lg fixed-top">
            <a href="FuncionarioController?acao=mostrar_admin" class="navbar-brand">
                <img id="logo" src="assets/images/logo.png" alt="CompraVenda"> CompraVenda
            </a>
            <span class="status">Área de Administradores</span>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-links" aria-controls="navbar-links" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbar-links">
                <div class="navbar-nav">
                    <a href="FuncionarioController?acao=mostrar_admin" class="nav-item nav-link">Mostrar Administradores</a>
                    <a href="FuncionarioController?acao=mostrar_vendedores" class="nav-item nav-link">Mostrar Vendedores</a>
                    <a href="FuncionarioController?acao=mostrar_compradores" class="nav-item nav-link">Mostrar Compradores</a>
                    <a href="RelatorioController?acao=estoque" class="nav-item nav-link">Relatório de Estoque</a>
                    <a href="RelatorioController?acao=vendas" class="nav-item nav-link">Relatório de Vendas</a>
                    <a href="FuncionarioController?acao=sair" class="nav-item nav-link" id="sair">Sair</a>
                </div>
            </div>
        </nav>
    </div>
</header>
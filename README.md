# Controle de Compra e Venda

Este projeto é um aplicativo web que permite o total controle de uma loja acerca do cadastro e monitoramento de seus produtos, clientes, fornecedores e funcionários.

## Tecnologias

- [Java (Servlet + JSP)](https://www.oracle.com/java/technologies/downloads/)
- [Bootstrap](https://getbootstrap.com/)
- [JQuery](https://jquery.com/)
- [MySQL (CRUD)](https://www.mysql.com/)

## Funcionalidades

Na aplicação, existem alguns papéis envolvidos que exercem diferentes funções ao interagirem com o sistema.

- **Clientes**: acessa a aplicação e visualiza os produtos colocados para venda;
- **Vendedores**: Funcionários responsáveis pelas vendas de produtos para os clientes. São funcionários previamente cadastrados pelo administrador e que acessam a área privada da aplicação. Cadastram os clientes que estão comprando e cadastram as vendas dos produtos para os clientes;
- **Compradores**: Funcionários responsáveis pelas compras de produtos juntos aos fornecedores. São funcionários previamente cadastrados pelo administrador e que acessam a área privada da aplicação. Cadastram os fornecedores que estão vendendo os produtos e cadastram as compras dos produtos dos fornecedores;
- **Administradores**: Administradores: Funcionários previamente autorizados a acessar a área responsável pelo cadastro de compradores, vendedores e outros administradores.

## Diagrama do Banco de Dados

![Picture11](/readme_media/picture11.jpg)

## Áreas de Usuário

![Picture8](/readme_media/picture8.jpg)
![Picture9](/readme_media/picture9.jpg)
![Picture10](/readme_media/picture10.jpg)

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas: [Git](https://git-scm.com), [Java JDK](https://www.oracle.com/java/technologies/downloads/), [MySQL](https://www.mysql.com/downloads/), [WampServer](https://www.wampserver.com/en/), [PHPMyAdmin](https://www.phpmyadmin.net/) e, também recomendado, uma IDE, como o [Netbeans](https://netbeans.apache.org/download/index.html).

Lembrando que alguns downloads acima já incluem, em seus respectivos instaladores, opções para baixar outras ferramentas listadas, como o *WampServer* que, em sua instalação, dá a opção para instalar o *PHPMyAdmin* junto.

## Rodando a aplicação

#### Clonando o repositório
Em uma linha de comando, execute os seguintes comandos:

```bash
# Acesse a pasta que deseje instalar a aplicação
$ cd C:\PastaDesejada
# Clone este repositório
$ git clone https://github.com/lorenzoa7/ControleCompraVenda.git
```
#### Abrindo o projeto

Primeiramente, você deve abrir o projeto no NetBeans. Para isso, no canto superior esquerdo, clique no menu **Arquivo** e clique na opção **Abrir Projeto**.

![Picture1](/readme_media/picture1.jpg)

Depois, selecione o projeto que você clonou na pasta escolhida no passo anterior.


#### Conectando o Banco de Dados

Agora é hora de configurar o banco de dados para sua aplicação. Dentro do NetBeans, no menu lateral do projeto, abra a pasta indicada como **Pacotes de Código Fonte**, depois **model** e, então, abra o arquivo `Conexao.java`, que estará localizado ali.

Abrindo o arquivo, vá até  a linha *24* e altere as variáveis *NomeDoBanco*, *Usuario* e *Senha* para o que desejar. Não se esqueça dessas informações!
```bash
conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/NomeDoBanco", "Usuario", "Senha");
```

#### Configurando o Banco de Dados

Então, é hora de configurar e carregar o banco de dados! Primeiramente, abra o **WampServer**, que você instalou em sua máquina.

![Picture2](/readme_media/picture2.jpg)

Ele inicializará na bandeja de aplicativos do seu sistema operacional (no Windows, canto inferior direito). Espere alguns segundos até que ele inicialize completamente, com seu ícone ficando totalmente verde.

Estando totalmente carregado, clique com o botão esquerdo em cima do ícone do *WampServer*, vá até *PhpMyAdmin* e selecione a versão desejada.

![Picture3](/readme_media/picture3.jpg)

Então, você verá uma tela de login do *PhpMyAdmin*. Em *Utilizador* e *Palavra-passe*, você deve inserir o usuário e a senha definidas no passo anterior, dentro do código. Na *Escolha de servidor*, deixe em *MySQL*.

![Picture4](/readme_media/picture4.jpg)

Acessando o *PhpMyAdmin*, você deve clicar no botão *Novo*, no menu lateral esquerdo. Em *Nome da base de dados*, você deve inserir o nome do banco, definido no passo anterior e, ao lado, escolher a codificação `utf8_general_ci`.

Depois de criado, só falta importar o banco de dados disponibilizado neste repositório. Para isto, vá até *Importar* e clique em *Escolher Arquivo*. Então, vá até a pasta de instalação do projeto e selecione o arquivo `estoque.sql`.

![Picture5](/readme_media/picture5.jpg)

Uma vez importado, se você for em *Estrutura*, deve aparecer algo como:

![Picture6](/readme_media/picture6.jpg)


#### Acessando a aplicação

Pronto! Agora é só rodar a aplicação no seu servidor local. Para isso, basta ir no menu superior do NetBeans, em *Executar* e clicar em *Executar Projeto*, ou simplesmente apertar *F6* no teclado.

Para começar, sugiro acessar a *Área de Admin* para cadastrar os funcionários. O administrador previamente cadastrado tem o CPF `111.111.111-11` e a senha `111`.

![Picture7](/readme_media/picture7.jpg)

---
Made by Lorenzo Aceti 👋 [Check my LinkedIn](https://www.linkedin.com/in/lorenzoaceti/) 
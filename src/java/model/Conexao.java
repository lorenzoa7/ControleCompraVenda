package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Conexao", urlPatterns = {"/Conexao"})
public class Conexao extends HttpServlet {

     //Atributo que irá armazenar a conexão com o banco de dados
    private static Connection conexao = null;
    
    //Método que realiza a conexão com o banco de dados
    public static Connection criaConexao() throws SQLException {
        //Verifica se já exite uma conexão com o banco de dados
        if ( conexao == null ) {
            try {
                //Carrega o Driver JDBC na memória
                Class.forName("com.mysql.jdbc.Driver"); //load driver                       
                System.out.println("Driver foi carregado!");
                //Abre a conexão com o banco de dados via JDBC
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
                System.out.println("Conexão realizada com sucesso!");
            }
            catch( ClassNotFoundException e ) {
                System.out.println("Driver não foi localizado!");
            }
        }
        // Retorna um objeto Connection, contendo a conexão aberta com o BD
        return conexao;
    }
}

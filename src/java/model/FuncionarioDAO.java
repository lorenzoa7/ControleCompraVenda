package model;

import aplicacao.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "FuncionarioDAO", urlPatterns = {"/FuncionarioDAO"})
public class FuncionarioDAO extends HttpServlet {

  private Connection conexao;
    public FuncionarioDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    public ArrayList<Funcionario> getLista() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Funcionario> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from funcionarios");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Funcionario para armazenar os dados
                //que vieram do BD
                Funcionario funcionario = new Funcionario();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                funcionario.setId(rs.getInt("id") );
                //Pega o conteúdo da coluna "nome" do ResultSet (rs)
                funcionario.setNome( rs.getString("nome") );
                //Pega o conteúdo da coluna "cpf" do ResultSet (rs)
                funcionario.setCpf( rs.getString("cpf") );
                //Pega o conteúdo da coluna "senha" do ResultSet (rs)
                funcionario.setSenha( rs.getString("senha") );
                //Pega o conteúdo da coluna "papel" do ResultSet (rs)
                funcionario.setPapel( rs.getString("papel") );
                
                resultado.add(funcionario);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Funcionarios encontrados no banco de dados.
        return resultado;
    }
    
    public Funcionario getFuncionarioPorID( int codigo ) {
        Funcionario Funcionario = new Funcionario();
        try {
            String sql = "SELECT * FROM funcionarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Funcionario.setId(rs.getInt("id") );
                Funcionario.setNome( rs.getString("nome") );
                Funcionario.setCpf( rs.getString("cpf") );
                Funcionario.setSenha( rs.getString("senha") );
                Funcionario.setPapel( rs.getString("papel") );
               
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Funcionario;
    }
    
    public String getFuncionarioPorString( int codigo ) {
        Funcionario Funcionario = new Funcionario();
        try {
            String sql = "SELECT * FROM funcionarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Funcionario.setId(rs.getInt("id") );
                Funcionario.setNome( rs.getString("nome") );
                Funcionario.setCpf( rs.getString("cpf") );
                Funcionario.setSenha( rs.getString("senha") );
                Funcionario.setPapel( rs.getString("papel") );
               
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Funcionario.getNome();
    }
    
    public Funcionario getFuncionarioPorLogin( String cpf, String senha ) {
        Funcionario Funcionario = new Funcionario();
        try {
            String sql = "SELECT * FROM funcionarios WHERE cpf = ? AND senha = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, senha);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                System.out.println("TA LOGADO SIM HEIN");
                Funcionario.setId(rs.getInt("id") );
                Funcionario.setNome( rs.getString("nome") );
                Funcionario.setCpf( rs.getString("cpf") );
                Funcionario.setSenha( rs.getString("senha") );
                Funcionario.setPapel( rs.getString("papel") );
               
            } else {
                System.out.println("ENTROU AQUI PQ NAO TA LOGADO");
                Funcionario.setId(-1);
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Funcionario;
    }
    
    public boolean gravar( Funcionario Funcionario ) {
        try {
            String sql;
            if ( Funcionario.getId() == 0 ) {
                // Realizar uma inclusão
                sql = "INSERT INTO funcionarios (nome, cpf, senha, papel) VALUES (?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE funcionarios SET nome=?, cpf=?, senha=?, papel=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, Funcionario.getNome());
            ps.setString(2, Funcionario.getCpf());
            ps.setString(3, Funcionario.getSenha());
            ps.setString(4, Funcionario.getPapel());
            
            
            if ( Funcionario.getId()> 0 )
                ps.setInt(5, Funcionario.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir( int id ) {
        try {
            String sql = "DELETE FROM funcionarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
}

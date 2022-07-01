package model;

import aplicacao.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "FornecedorDAO", urlPatterns = {"/FornecedorDAO"})
public class FornecedorDAO extends HttpServlet {

  private Connection conexao;
    public FornecedorDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    public ArrayList<Fornecedor> getLista() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Fornecedor> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from fornecedores");
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Fornecedor para armazenar os dados
                //que vieram do BD
                Fornecedor fornecedor = new Fornecedor();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                fornecedor.setId(rs.getInt("id") );
                //Pega o conteúdo da coluna "razao_social" do ResultSet (rs)
                fornecedor.setRazao_social( rs.getString("razao_social") );
                //Pega o conteúdo da coluna "cnpj" do ResultSet (rs)
                fornecedor.setCnpj( rs.getString("cnpj") );
                //Pega o conteúdo da coluna "endereco" do ResultSet (rs)
                fornecedor.setEndereco(rs.getString("endereco") );
                //Pega o conteúdo da coluna "bairro" do ResultSet (rs)
                fornecedor.setBairro(rs.getString("bairro") );
                //Pega o conteúdo da coluna "cidade" do ResultSet (rs)
                fornecedor.setCidade(rs.getString("cidade") );
                //Pega o conteúdo da coluna "uf" do ResultSet (rs)
                fornecedor.setUf(rs.getString("uf") );
                //Pega o conteúdo da coluna "cep" do ResultSet (rs)
                fornecedor.setCep(rs.getString("cep") );
                //Pega o conteúdo da coluna "telefone" do ResultSet (rs)
                fornecedor.setTelefone(rs.getString("telefone") );
                //Pega o conteúdo da coluna "email" do ResultSet (rs)
                fornecedor.setEmail(rs.getString("email") );
                //Adiciona o objeto criado na ArrayList resultado
                resultado.add(fornecedor);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Fornecedores encontrados no banco de dados.
        return resultado;
    }
    
    public Fornecedor getFornecedorPorID( int codigo ) {
        Fornecedor Fornecedor = new Fornecedor();
        try {
            String sql = "SELECT * FROM fornecedores WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Fornecedor.setId(rs.getInt("id") );
                Fornecedor.setRazao_social( rs.getString("razao_social") );
                Fornecedor.setCnpj( rs.getString("cnpj") );
                Fornecedor.setEndereco(rs.getString("endereco") );
                Fornecedor.setBairro(rs.getString("bairro") );
                Fornecedor.setCidade(rs.getString("cidade") );
                Fornecedor.setUf(rs.getString("uf") );
                Fornecedor.setCep(rs.getString("cep") );
                Fornecedor.setTelefone(rs.getString("telefone") );
                Fornecedor.setEmail(rs.getString("email") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Fornecedor;
    }
    
    public String getFornecedorPorString( int codigo ) {
        Fornecedor Fornecedor = new Fornecedor();
        try {
            String sql = "SELECT * FROM fornecedores WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Fornecedor.setId(rs.getInt("id") );
                Fornecedor.setRazao_social( rs.getString("razao_social") );
                Fornecedor.setCnpj( rs.getString("cnpj") );
                Fornecedor.setEndereco(rs.getString("endereco") );
                Fornecedor.setBairro(rs.getString("bairro") );
                Fornecedor.setCidade(rs.getString("cidade") );
                Fornecedor.setUf(rs.getString("uf") );
                Fornecedor.setCep(rs.getString("cep") );
                Fornecedor.setTelefone(rs.getString("telefone") );
                Fornecedor.setEmail(rs.getString("email") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Fornecedor.getRazao_social();
    }
    
    public boolean gravar( Fornecedor Fornecedor ) {
        try {
            String sql;
            if ( Fornecedor.getId() == 0 ) {
                // Realizar uma inclusão
                sql = "INSERT INTO fornecedores (razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?,?,?,?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE fornecedores SET razao_social=?, cnpj=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, Fornecedor.getRazao_social());
            ps.setString(2, Fornecedor.getCnpj());
            ps.setString(3, Fornecedor.getEndereco());
            ps.setString(4, Fornecedor.getBairro());
            ps.setString(5, Fornecedor.getCidade());
            ps.setString(6, Fornecedor.getUf());
            ps.setString(7, Fornecedor.getCep());
            ps.setString(8, Fornecedor.getTelefone());
            ps.setString(9, Fornecedor.getEmail());
            
            
            if ( Fornecedor.getId()> 0 )
                ps.setInt(10, Fornecedor.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir( int id ) {
        try {
            String sql = "DELETE FROM fornecedores WHERE id = ?";
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

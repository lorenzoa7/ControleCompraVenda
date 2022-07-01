package model;

import aplicacao.Fornecedor;
import aplicacao.Produto;
import aplicacao.Funcionario;
import aplicacao.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "CompraDAO", urlPatterns = {"/CompraDAO"})
public class CompraDAO extends HttpServlet {

  private Connection conexao;
    public CompraDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    public ArrayList<Compra> getLista() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Compra> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from compras");
            
            FornecedorDAO fornecedordao = new FornecedorDAO();
            ProdutoDAO produtodao = new ProdutoDAO();
            FuncionarioDAO funcionariodao = new FuncionarioDAO();
            
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Compra para armazenar os dados
                //que vieram do BD
                Compra compra = new Compra();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                compra.setId(rs.getInt("id") );
                //Pega o conteúdo da coluna "quantidade_compra" do ResultSet (rs)
                compra.setQuantidade_compra( rs.getInt("quantidade_compra") );
                //Pega o conteúdo da coluna "data_compra" do ResultSet (rs)
                compra.setData_compra( rs.getDate("data_compra") );
                //Pega o conteúdo da coluna "valor_compra" do ResultSet (rs)
                compra.setValor_compra(rs.getInt("valor_compra") );
                
                //Pega o conteúdo da coluna "fornecedor" do ResultSet (rs)
                compra.setId_fornecedor(rs.getInt("id_fornecedor"));
                String fornecedor = fornecedordao.getFornecedorPorString(compra.getId_fornecedor()) ;
                compra.setFornecedor(fornecedor);
                
                //Pega o conteúdo da coluna "produto" do ResultSet (rs)
                compra.setId_produto(rs.getInt("id_produto"));
                String produto = produtodao.getProdutoPorString(compra.getId_produto()) ;
                compra.setProduto(produto);
                
                //Pega o conteúdo da coluna "funcionario" do ResultSet (rs)
                compra.setId_funcionario(rs.getInt("id_funcionario"));
                String funcionario = funcionariodao.getFuncionarioPorString(compra.getId_funcionario()) ;
                compra.setFuncionario(funcionario);
                
                resultado.add(compra);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Compras encontrados no banco de dados.
        return resultado;
    }
    
    public Compra getCompraPorID( int codigo ) {
        Compra Compra = new Compra();
        try {
            String sql = "SELECT * FROM compras WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            FornecedorDAO fornecedordao = new FornecedorDAO();
            ProdutoDAO produtodao = new ProdutoDAO();
            FuncionarioDAO funcionariodao = new FuncionarioDAO();
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Compra.setId(rs.getInt("id") );
                Compra.setQuantidade_compra( rs.getInt("quantidade_compra") );
                Compra.setData_compra( rs.getDate("data_compra") );
                Compra.setValor_compra(rs.getInt("valor_compra") );
                
                Compra.setId_fornecedor(rs.getInt("id_fornecedor"));
                String fornecedor = fornecedordao.getFornecedorPorString(Compra.getId_fornecedor()) ;
                Compra.setFornecedor(fornecedor);
                
                Compra.setId_produto(rs.getInt("id_produto"));
                String produto = produtodao.getProdutoPorString(Compra.getId_produto()) ;
                Compra.setProduto(produto);
                
                Compra.setId_funcionario(rs.getInt("id_funcionario"));
                String funcionario = funcionariodao.getFuncionarioPorString(Compra.getId_funcionario()) ;
                Compra.setFuncionario(funcionario);
                
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Compra;
    }
    
    public boolean gravar( Compra Compra ) {
        try {
            String sql;
            if ( Compra.getId() == 0 ) {
                // Realizar uma inclusão
                sql = "INSERT INTO compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_funcionario) VALUES (?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE compras SET quantidade_compra=?, data_compra=?, valor_compra=?, id_fornecedor=?, id_produto=?, id_funcionario=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, Compra.getQuantidade_compra());
            ps.setDate(2, Compra.getData_compra());
            ps.setInt(3, Compra.getValor_compra());
            ps.setInt(4, Compra.getId_fornecedor());
            ps.setInt(5, Compra.getId_produto());
            ps.setInt(6, Compra.getId_funcionario());
            
            if ( Compra.getId()> 0 )
                ps.setInt(7, Compra.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir( int id ) {
        try {
            String sql = "DELETE FROM compras WHERE id = ?";
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

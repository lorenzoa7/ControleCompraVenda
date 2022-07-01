package model;

import aplicacao.Categoria;
import aplicacao.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "ProdutoDAO", urlPatterns = {"/ProdutoDAO"})
public class ProdutoDAO extends HttpServlet {

  private Connection conexao;
    public ProdutoDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    public ArrayList<Produto> getLista() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Produto> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from produtos");
            
            CategoriaDAO categoriadao = new CategoriaDAO();
            
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Produto para armazenar os dados
                //que vieram do BD
                Produto produto = new Produto();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                produto.setId(rs.getInt("id") );
                //Pega o conteúdo da coluna "nome_produto" do ResultSet (rs)
                produto.setNome( rs.getString("nome_produto") );
                //Pega o conteúdo da coluna "descricao" do ResultSet (rs)
                produto.setDescricao( rs.getString("descricao") );
                //Pega o conteúdo da coluna "preco_compra" do ResultSet (rs)
                produto.setPrecoCompra(rs.getDouble("preco_compra") );
                //Pega o conteúdo da coluna "preco_venda" do ResultSet (rs)
                produto.setPrecoVenda(rs.getDouble("preco_venda") );
                //Pega o conteúdo da coluna "quantidade_disponível" do ResultSet (rs)
                produto.setQtdDisponivel(rs.getInt("quantidade_disponível") );
                //Pega o conteúdo da coluna "uf" do ResultSet (rs)
                produto.setLiberadoVenda(rs.getString("liberado_venda") );
                //Pega o conteúdo da coluna "categoria" do ResultSet (rs)
                
                produto.setId_categoria(rs.getInt("id_categoria"));
                
                String categoria = categoriadao.getCategoriaPorString(produto.getId_categoria()) ;
                produto.setCategoria(categoria);
                
                
                resultado.add(produto);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Produtos encontrados no banco de dados.
        return resultado;
    }
    
    public ArrayList<Produto> getListaDisponiveis() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Produto> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from produtos");
            
            CategoriaDAO categoriadao = new CategoriaDAO();
            
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Produto para armazenar os dados
                //que vieram do BD
                Produto produto = new Produto();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                produto.setId(rs.getInt("id") );
                //Pega o conteúdo da coluna "nome_produto" do ResultSet (rs)
                produto.setNome( rs.getString("nome_produto") );
                //Pega o conteúdo da coluna "descricao" do ResultSet (rs)
                produto.setDescricao( rs.getString("descricao") );
                //Pega o conteúdo da coluna "preco_compra" do ResultSet (rs)
                produto.setPrecoCompra(rs.getDouble("preco_compra") );
                //Pega o conteúdo da coluna "preco_venda" do ResultSet (rs)
                produto.setPrecoVenda(rs.getDouble("preco_venda") );
                //Pega o conteúdo da coluna "quantidade_disponível" do ResultSet (rs)
                produto.setQtdDisponivel(rs.getInt("quantidade_disponível") );
                //Pega o conteúdo da coluna "uf" do ResultSet (rs)
                produto.setLiberadoVenda(rs.getString("liberado_venda") );
                //Pega o conteúdo da coluna "categoria" do ResultSet (rs)
                
                produto.setId_categoria(rs.getInt("id_categoria"));
                
                String categoria = categoriadao.getCategoriaPorString(produto.getId_categoria()) ;
                produto.setCategoria(categoria);
                
                if (produto.getQtdDisponivel() > 0 && "S".equals(produto.getLiberadoVenda())) {
                    resultado.add(produto);
                }
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Produtos encontrados no banco de dados.
        return resultado;
    }
    
    public Produto getProdutoPorID( int codigo ) {
        Produto Produto = new Produto();
        try {
            String sql = "SELECT * FROM produtos WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            CategoriaDAO categoriadao = new CategoriaDAO();
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Produto.setId(rs.getInt("id") );
                Produto.setNome( rs.getString("nome_produto") );
                Produto.setDescricao( rs.getString("descricao") );
                Produto.setPrecoCompra(rs.getDouble("preco_compra") );
                Produto.setPrecoVenda(rs.getDouble("preco_venda") );
                Produto.setQtdDisponivel(rs.getInt("quantidade_disponível") );
                Produto.setLiberadoVenda(rs.getString("liberado_venda") );
                
                Produto.setId_categoria(rs.getInt("id_categoria"));
                String categoria = categoriadao.getCategoriaPorString(Produto.getId_categoria());
                Produto.setCategoria(categoria);
                
                
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Produto;
    }
    
    public String getProdutoPorString( int codigo ) {
        Produto Produto = new Produto();
        try {
            String sql = "SELECT * FROM produtos WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            CategoriaDAO categoriadao = new CategoriaDAO();
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Produto.setId(rs.getInt("id") );
                Produto.setNome( rs.getString("nome_produto") );
                Produto.setDescricao( rs.getString("descricao") );
                Produto.setPrecoCompra(rs.getDouble("preco_compra") );
                Produto.setPrecoVenda(rs.getDouble("preco_venda") );
                Produto.setQtdDisponivel(rs.getInt("quantidade_disponível") );
                Produto.setLiberadoVenda(rs.getString("liberado_venda") );
                
                Produto.setId_categoria(rs.getInt("id_categoria"));
                String categoria = categoriadao.getCategoriaPorString(Produto.getId_categoria());
                Produto.setCategoria(categoria);
                
                
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Produto.getNome();
    }
    
    public boolean gravar( Produto Produto ) {
        try {
            String sql;
            if ( Produto.getId() == 0 ) {
                // Realizar uma inclusão
                sql = "INSERT INTO produtos (nome_produto, descricao, preco_compra, preco_venda, quantidade_disponível, liberado_venda, id_categoria) VALUES (?,?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE produtos SET nome_produto=?, descricao=?, preco_compra=?, preco_venda=?, quantidade_disponível=?, liberado_venda=?, id_categoria=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, Produto.getNome());
            ps.setString(2, Produto.getDescricao());
            ps.setDouble(3, Produto.getPrecoCompra());
            ps.setDouble(4, Produto.getPrecoVenda());
            ps.setInt(5, Produto.getQtdDisponivel());
            ps.setString(6, Produto.getLiberadoVenda());
            ps.setInt(7, Produto.getId_categoria());
            
            if ( Produto.getId()> 0 )
                ps.setInt(8, Produto.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir( int id ) {
        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
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

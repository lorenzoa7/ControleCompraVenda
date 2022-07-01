package model;

import aplicacao.Cliente;
import aplicacao.Produto;
import aplicacao.Funcionario;
import aplicacao.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "VendaDAO", urlPatterns = {"/VendaDAO"})
public class VendaDAO extends HttpServlet {

  private Connection conexao;
    public VendaDAO() {
        try {
            // Cria a conexão com o banco de dados
            conexao = Conexao.criaConexao();
        }
        catch( Exception e ) {
            System.out.println("Erro criação de conexao DAO");
            System.out.println(e);
        }
    }
    public ArrayList<Venda> getLista() {
        //Cria o objeto resultado que irá armazenar os registros retornados do BD
        ArrayList<Venda> resultado = new ArrayList<>();
        try {            
            // Cria o objeto para quer será utilizado para enviar comandos SQL
            // para o BD
            Statement stmt = conexao.createStatement();
            // Armazena o resultado do comando enviado para o banco de dados
            ResultSet rs = stmt.executeQuery("select * from vendas");
            
            ClienteDAO clientedao = new ClienteDAO();
            ProdutoDAO produtodao = new ProdutoDAO();
            FuncionarioDAO funcionariodao = new FuncionarioDAO();
            
            // rs.next() Aponta para o próximo registro do BD, se houver um 
            while( rs.next() ) {
                //Cria o objeto da classe Venda para armazenar os dados
                //que vieram do BD
                Venda venda = new Venda();
                
                //Pega o conteúdo da coluna "id" do ResultSet (rs)
                venda.setId(rs.getInt("id") );
                //Pega o conteúdo da coluna "quantidade_venda" do ResultSet (rs)
                venda.setQuantidade_venda( rs.getInt("quantidade_venda") );
                //Pega o conteúdo da coluna "data_venda" do ResultSet (rs)
                venda.setData_venda( rs.getDate("data_venda") );
                //Pega o conteúdo da coluna "valor_venda" do ResultSet (rs)
                venda.setValor_venda(rs.getFloat("valor_venda") );
                
                //Pega o conteúdo da coluna "cliente" do ResultSet (rs)
                venda.setId_cliente(rs.getInt("id_cliente"));
                String cliente = clientedao.getClientePorString(venda.getId_cliente()) ;
                venda.setCliente(cliente);
                
                //Pega o conteúdo da coluna "produto" do ResultSet (rs)
                venda.setId_produto(rs.getInt("id_produto"));
                String produto = produtodao.getProdutoPorString(venda.getId_produto());
                venda.setProduto(produto);
                
                //Pega o conteúdo da coluna "funcionario" do ResultSet (rs)
                venda.setId_funcionario(rs.getInt("id_funcionario"));
                String funcionario = funcionariodao.getFuncionarioPorString(venda.getId_funcionario()) ;
                venda.setFuncionario(funcionario);
                
                resultado.add(venda);
            }
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        
        // Retorna a lista de Vendas encontrados no banco de dados.
        return resultado;
    }
    
    public Venda getVendaPorID( int codigo ) {
        Venda Venda = new Venda();
        try {
            String sql = "SELECT * FROM vendas WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ClienteDAO clientedao = new ClienteDAO();
            ProdutoDAO produtodao = new ProdutoDAO();
            FuncionarioDAO funcionariodao = new FuncionarioDAO();
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                Venda.setId(rs.getInt("id") );
                Venda.setQuantidade_venda( rs.getInt("quantidade_venda") );
                Venda.setData_venda( rs.getDate("data_venda") );
                Venda.setValor_venda(rs.getFloat("valor_venda") );
                
                Venda.setId_cliente(rs.getInt("id_cliente"));
                String cliente = clientedao.getClientePorString(Venda.getId_cliente()) ;
                Venda.setCliente(cliente);
                
                Venda.setId_produto(rs.getInt("id_produto"));
                String produto = produtodao.getProdutoPorString(Venda.getId_produto()) ;
                Venda.setProduto(produto);
                
                Venda.setId_funcionario(rs.getInt("id_funcionario"));
                String funcionario = funcionariodao.getFuncionarioPorString(Venda.getId_funcionario()) ;
                Venda.setFuncionario(funcionario);
                
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return Venda;
    }
    
    public boolean gravar( Venda Venda ) {
        try {
            String sql;
            if ( Venda.getId() == 0 ) {
                // Realizar uma inclusão
                sql = "INSERT INTO vendas (quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_funcionario) VALUES (?,?,?,?,?,?)";
            } else {
                // Realizar uma alteração
                sql = "UPDATE vendas SET quantidade_venda=?, data_venda=?, valor_venda=?, id_cliente=?, id_produto=?, id_funcionario=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, Venda.getQuantidade_venda());
            ps.setDate(2, Venda.getData_venda());
            ps.setFloat(3, Venda.getValor_venda());
            ps.setInt(4, Venda.getId_cliente());
            ps.setInt(5, Venda.getId_produto());
            ps.setInt(6, Venda.getId_funcionario());
            
            if ( Venda.getId()> 0 )
                ps.setInt(7, Venda.getId());
            
            ps.execute();
            
            return true;
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir( int id ) {
        try {
            String sql = "DELETE FROM vendas WHERE id = ?";
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

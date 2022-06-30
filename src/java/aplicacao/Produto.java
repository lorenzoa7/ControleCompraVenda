package aplicacao;


public class Produto {
    private Integer id;
    private String nome_produto;
    private String descricao;
    private Integer preco_compra;
    private Integer preco_venda;
    private Integer quantidade_disponivel;
    private String liberado_venda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome_produto;
    }

    public void setNome(String nome) {
        this.nome_produto = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Integer getPrecoCompra() {
        return preco_compra;
    }

    public void setPrecoCompra(Integer preco_compra) {
        this.preco_compra = preco_compra;
    }
    
    public Integer getPrecoVenda() {
        return preco_venda;
    }

    public void setPrecoVenda(Integer preco_venda) {
        this.preco_venda = preco_venda;
    }
    
    public Integer getQtdDisponivel() {
        return quantidade_disponivel;
    }

    public void setQtdDisponivel(Integer quantidade_disponivel) {
        this.quantidade_disponivel = quantidade_disponivel;
    }
    
    public String getLiberadoVenda() {
        return liberado_venda;
    }

    public void setLiberadoVenda(String liberado_venda) {
        this.liberado_venda = liberado_venda;
    }
    
}

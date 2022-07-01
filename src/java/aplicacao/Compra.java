package aplicacao;

import java.sql.Date;


public class Compra {
    private Integer id;
    private Integer quantidade_compra;
    private Date data_compra;
    private Integer valor_compra;
    private Integer id_fornecedor;
    private Integer id_produto;
    private Integer id_funcionario;
    private String fornecedor;
    private String produto;
    private String funcionario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade_compra() {
        return quantidade_compra;
    }

    public void setQuantidade_compra(Integer quantidade_compra) {
        this.quantidade_compra = quantidade_compra;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public Integer getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(Integer valor_compra) {
        this.valor_compra = valor_compra;
    }

    public Integer getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Integer id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
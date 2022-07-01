package aplicacao;

import java.sql.Date;


public class Venda {
    private Integer id;
    private Integer quantidade_venda;
    private Date data_venda;
    private Float valor_venda;
    private Integer id_cliente;
    private Integer id_produto;
    private Integer id_funcionario;
    private String cliente;
    private String produto;
    private String funcionario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade_venda() {
        return quantidade_venda;
    }

    public void setQuantidade_venda(Integer quantidade_venda) {
        this.quantidade_venda = quantidade_venda;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public Float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(Float valor_venda) {
        this.valor_venda = valor_venda;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
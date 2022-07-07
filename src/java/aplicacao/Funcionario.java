package aplicacao;


public class Funcionario {
    private Integer id;
    private String nome;
    private String cpf;
    private String senha;
    private String papel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
    
    public String getPapelString() {
        if ("0".equals(papel)) {
            return "Administrador";
        } else if ("1".equals(papel)) {
            return "Vendedor";
        } else if ("2".equals(papel)) {
            return "Comprador";
        } else {
            return "Nenhum";
        }
    }
}
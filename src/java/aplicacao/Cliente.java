package aplicacao;


public class Cliente {
    private Integer id;
    private String nome;
    private String cpf;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;

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
    
    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getUF() {
        return uf;
    }

    public void setUF(String uf) {
        this.uf = uf;
    }
    
    public String getCEP() {
        return cep;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

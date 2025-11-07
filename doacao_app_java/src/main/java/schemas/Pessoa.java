package schemas;

public abstract class Pessoa {

    private int id;
    private String nome;
    private String email;
    private String documento;
    private String telefone;
    private String senha;
    private String endereco;

    public abstract void enviarMensagem(String texto);

    public Pessoa(Integer id, String nome, String email, String documento, String telefone, String senha, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.telefone = telefone;
        this.senha = senha;
        this.endereco = endereco;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

package schemas;

public class Protetor extends Pessoa {
    private String tipo;

    public Protetor(Integer id,String nome, String senha, String endereco, String email, String documento, String telefone, String tipo) {
        super(id,nome, email, documento, telefone, senha, endereco);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public void enviarMensagem(String texto) {
        System.out.println("schemas.Mensagem para o adotante " + getNome() + ": " + texto);
    }
}

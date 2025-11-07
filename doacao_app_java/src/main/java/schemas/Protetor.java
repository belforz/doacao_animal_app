package schemas;

public class Protetor extends Pessoa {
    private String preferenciaAdocao;

    public Protetor(Integer id,String nome, String senha, String endereco, String email, String documento, String telefone, String preferenciaAdocao) {
        super(id,nome, email, documento, telefone, senha, endereco);
        this.preferenciaAdocao = preferenciaAdocao;
    }

    public String getPreferenciaAdocao() {
        return preferenciaAdocao;
    }

    public void setPreferenciaAdocao(String preferenciaAdocao) {
        this.preferenciaAdocao = preferenciaAdocao;
    }
    @Override
    public void enviarMensagem(String texto) {
        System.out.println("schemas.Mensagem para o adotante " + getNome() + ": " + texto);
    }
}

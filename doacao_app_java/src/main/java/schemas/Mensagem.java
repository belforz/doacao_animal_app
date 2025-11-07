package schemas;

import java.util.Date;

public class Mensagem {
    private Integer idMensagem;
    private Date dataMensagem;
    private String conteudo;
    private Integer idRemetente;
    private Integer idDestinario;

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Date getDataMensagem() {
        return dataMensagem;
    }

    public void setDataMensagem(Date dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(Integer idRemetente) {
        this.idRemetente = idRemetente;
    }

    public Integer getIdDestinario() {
        return idDestinario;
    }

    public void setIdDestinario(Integer idDestinario) {
        this.idDestinario = idDestinario;
    }
}


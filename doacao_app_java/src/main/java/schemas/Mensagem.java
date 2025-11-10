package schemas;

import java.util.Date;

public class Mensagem {
    private Integer idMensagem;
    private Date dataMensagem;
    private String conteudo;
    private Integer idRemetente;
    private Integer idDestinatario;
    private String tipoRemetente;
    private String tipoDestinatario;
    private ProcessoAdocao processoAdocao;
    private Integer id_processo;

    public Mensagem(java.sql.Date dataMensagem, String conteudo, int idRemetente, int idDestinatario, String tipoRemetente, String tipoDestinatario, int idProcesso) {
        this.dataMensagem = dataMensagem;
        this.conteudo = conteudo;
        this.idRemetente = idRemetente;
        this.idDestinatario = idDestinatario;
        this.tipoRemetente = tipoRemetente;
        this.tipoDestinatario = tipoDestinatario;
        this.processoAdocao = new ProcessoAdocao();
        this.processoAdocao.setIdPAdocao(idProcesso);
    }

    public ProcessoAdocao getProcessoAdocao() {
        return processoAdocao;
    }

    public void setProcessoAdocao(ProcessoAdocao processoAdocao) {
        this.processoAdocao = processoAdocao;
    }

    public String getTipoRemetente() {
        return tipoRemetente;
    }

    public void setTipoRemetente(String tipoRemetente) {
        this.tipoRemetente = tipoRemetente;
    }

    public String getTipoDestinatario() {
        return tipoDestinatario;
    }

    public void setTipoDestinatario(String tipoDestinatario) {
        this.tipoDestinatario = tipoDestinatario;
    }

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

    public Integer getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(Integer idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public Integer getId_processo() {
        return id_processo;
    }

    public void setId_processo(Integer id_processo) {
        this.id_processo = id_processo;
    }
}

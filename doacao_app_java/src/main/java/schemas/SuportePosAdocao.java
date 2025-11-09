package schemas;

import java.time.LocalDate;

public class SuportePosAdocao {
    private Integer idSuporte;
    private LocalDate dataRegistro;
    private String tipoSolicitacao;
    private String descricao;
    private Integer idAdocao;

    public SuportePosAdocao() {}

    public SuportePosAdocao(Integer idSuporte, LocalDate dataRegistro, String tipoSolicitacao, String descricao, Integer idAdocao) {
        this.idSuporte = idSuporte;
        this.dataRegistro = dataRegistro;
        this.tipoSolicitacao = tipoSolicitacao;
        this.descricao = descricao;
        this.idAdocao = idAdocao;
    }

    public Integer getIdSuporte() {
        return idSuporte;
    }

    public void setIdSuporte(Integer idSuporte) {
        this.idSuporte = idSuporte;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getTipoSolicitacao() {
        return tipoSolicitacao;
    }

    public void setTipoSolicitacao(String tipoSolicitacao) {
        this.tipoSolicitacao = tipoSolicitacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdAdocao() {
        return idAdocao;
    }

    public void setIdAdocao(Integer idAdocao) {
        this.idAdocao = idAdocao;
    }

    public void registrar() {
        System.out.println("Registrando suporte pós-adoção: " + tipoSolicitacao);
    }
}

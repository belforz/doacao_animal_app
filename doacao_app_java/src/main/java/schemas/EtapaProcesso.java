package schemas;

import java.time.LocalDate;

public class EtapaProcesso {
    private Integer id;
    private LocalDate data;
    private String observacoes;
    private String statusEtapa;
    private String tipoEtapa;
    private Integer id_processo;

    public EtapaProcesso() {}

    public EtapaProcesso(Integer id, LocalDate data, String observacoes, String statusEtapa, String tipoEtapa, Integer id_processo) {
        this.id = id;
        this.data = data;
        this.observacoes = observacoes;
        this.statusEtapa = statusEtapa;
        this.tipoEtapa = tipoEtapa;
        this.id_processo = id_processo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatusEtapa() {
        return statusEtapa;
    }

    public void setStatusEtapa(String statusEtapa) {
        this.statusEtapa = statusEtapa;
    }

    public String getTipoEtapa() {
        return tipoEtapa;
    }

    public void setTipoEtapa(String tipoEtapa) {
        this.tipoEtapa = tipoEtapa;
    }

    public Integer getId_processo() {
        return id_processo;
    }

    public void setId_processo(Integer id_processo) {
        this.id_processo = id_processo;
    }

    public void executar(){

    };
}

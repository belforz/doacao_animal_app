package schemas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Adocao {
    private Integer idAdocao;
    private LocalDate dataAdocao;
    private String descricao;
    private String termos;
    private ProcessoAdocao processo;
    private List<SuportePosAdocao> suportes = new ArrayList<>();
    private Integer id_processo;

    public Adocao() {}

    public Adocao(Integer idAdocao, LocalDate dataAdocao, String descricao, String termos, Integer id_processo) {
        this.idAdocao = idAdocao;
        this.dataAdocao = dataAdocao;
        this.descricao = descricao;
        this.termos = termos;
        this.id_processo = id_processo;
    }

    public Integer getIdAdocao() {
        return idAdocao;
    }

    public void setIdAdocao(Integer idAdocao) {
        this.idAdocao = idAdocao;
    }

    public LocalDate getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(LocalDate dataAdocao) {
        this.dataAdocao = dataAdocao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTermos() {
        return termos;
    }

    public void setTermos(String termos) {
        this.termos = termos;
    }

    public ProcessoAdocao getProcesso() {
        return processo;
    }

    public void setProcesso(ProcessoAdocao processo) {
        this.processo = processo;
    }

    public List<SuportePosAdocao> getSuportes() {
        return suportes;
    }

    public void setSuportes(List<SuportePosAdocao> suportes) {
        this.suportes = suportes;
    }

    public Integer getId_processo() {
        return id_processo;
    }

    public void setId_processo(Integer id_processo) {
        this.id_processo = id_processo;
    }
}

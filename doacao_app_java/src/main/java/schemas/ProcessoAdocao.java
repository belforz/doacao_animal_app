package schemas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessoAdocao {
    private Integer idPAdocao;
    private Animal animal;
    private Adotante adotante;
    private StatusProcesso status;
    private LocalDate dataIncio;
    private List<EtapaProcesso> etapas = new ArrayList<>();
    private List<Mensagem> mensagems = new ArrayList<>();
    private Integer id_animal;
    private Integer id_adotante;

    public ProcessoAdocao(Integer idPAdocao, Animal animal, Adotante adotante, StatusProcesso status, LocalDate dataIncio, Integer id_animal, Integer id_adotante) {
        this.idPAdocao = idPAdocao;
        this.animal = animal;
        this.adotante = adotante;
        this.status = status;
        this.dataIncio = dataIncio;
        this.id_animal = id_animal;
        this.id_adotante = id_adotante;
    }

    public ProcessoAdocao() {

    }

    public Integer getIdPAdocao() {
        return idPAdocao;
    }

    public void setIdPAdocao(Integer idPAdocao) {
        this.idPAdocao = idPAdocao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Adotante getAdotante() {
        return adotante;
    }

    public void setAdotante(Adotante adotante) {
        this.adotante = adotante;
    }

    public StatusProcesso getStatus() {
        return status;
    }

    public void setStatus(StatusProcesso status) {
        this.status = status;
    }

    public LocalDate getDataIncio() {
        return dataIncio;
    }

    public void setDataIncio(LocalDate dataIncio) {
        this.dataIncio = dataIncio;
    }

    public List<EtapaProcesso> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<EtapaProcesso> etapas) {
        this.etapas = etapas;
    }

    public List<Mensagem> getMensagems() {
        return mensagems;
    }

    public void setMensagems(List<Mensagem> mensagems) {
        this.mensagems = mensagems;
    }

    public void adicionarEtapa(EtapaProcesso etapa) {
        this.etapas.add(etapa);
    }

    public void mudarStatus(StatusProcesso novoStatus) {
        this.status = novoStatus;
        System.out.println("Procecsso" + idPAdocao + "agora está em" + novoStatus);
    }

    public Integer getId_animal() {
        return id_animal;
    }

    public void setId_animal(Integer id_animal) {
        this.id_animal = id_animal;
    }

    public Integer getId_adotante() {
        return id_adotante;
    }

    public void setId_adotante(Integer id_adotante) {
        this.id_adotante = id_adotante;
    }
}

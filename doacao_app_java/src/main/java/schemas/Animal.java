package schemas;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private Integer id;
    private String especie;
    private String raca;
    private String temperamento;
    private String historicoSaude;
    private String nome;
    private String descricao;
    private Boolean esEspecial;
    private Integer idade;
    private Character sexo;
    private String status;
    private Protetor protetor;
    private List<FotoAnimal> fotoAnimal = new ArrayList<>();

    public Animal(Integer idAnimal, String especie, String raca, String temperamento, String historicoSaude, String nome, String descricao, boolean esEspecial, int idade, char sexo, String status, int idProtetor) {
        this.id = idAnimal;
        this.especie = especie;
        this.raca = raca;
        this.temperamento = temperamento;
        this.historicoSaude = historicoSaude;
        this.nome = nome;
        this.descricao = descricao;
        this.esEspecial = esEspecial;
        this.idade = idade;
        this.sexo = sexo;
        this.status = status;
        this.protetor = new Protetor(idProtetor, "", "", "", "", "", "", "");
    }

    public Animal() {

    }

    public Protetor getProtetor() {
        return protetor;
    }

    public void setProtetor(Protetor protetor) {
        this.protetor = protetor;
    }

    public List<FotoAnimal> getFotoAnimal() {
        return fotoAnimal;
    }

    public void setFotoAnimal(List<FotoAnimal> fotoAnimal) {
        this.fotoAnimal = fotoAnimal;
    }

    public void addFotoAnimal(FotoAnimal foto){
        this.fotoAnimal.add(foto);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getHistoricoSaude() {
        return historicoSaude;
    }

    public void setHistoricoSaude(String historicoSaude) {
        this.historicoSaude = historicoSaude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getEsEspecial() {
        return esEspecial;
    }

    public void setEsEspecial(Boolean esEspecial) {
        this.esEspecial = esEspecial;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

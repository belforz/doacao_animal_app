package schemas;

public class FotoAnimal {
    private String urlAnimal;
    private String descricaoFoto;
    private Integer idAnimal;
    private Integer idFotoAnimal;

    public FotoAnimal(String urlAnimal, String descricaoFoto, Integer idAnimal, Integer idFotoAnimal) {
        this.urlAnimal = urlAnimal;
        this.descricaoFoto = descricaoFoto;
        this.idAnimal = idAnimal;
        this.idFotoAnimal = idFotoAnimal;

    }

    public String getUrlAnimal() {
        return urlAnimal;
    }

    public void setUrlAnimal(String urlAnimal) {
        this.urlAnimal = urlAnimal;
    }

    public String getDescricaoFoto() {
        return descricaoFoto;
    }

    public void setDescricaoFoto(String descricaoFoto) {
        this.descricaoFoto = descricaoFoto;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Integer getIdFotoAnimal() {
        return idFotoAnimal;
    }

    public void setIdFotoAnimal(Integer idFotoAnimal) {
        this.idFotoAnimal = idFotoAnimal;
    }
}

package schemas;

import java.time.LocalDate;

public class SuportePosAdocao {
    private Integer idSuporte;
    private LocalDate dataRegistro;
    private String tipoSolicitacao;
    private String descricao;
    private Integer idAdocao;

    public void registrar() {
        System.out.println("Registrando suporte pós-adoção: " + tipoSolicitacao);
    }
}

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
    private List<Mensagem> mensgens = new ArrayList<>();

    public void adicionarEtapa(EtapaProcesso etapa) {
        this.etapas.add(etapa);
    }

    public void mudarStatus(StatusProcesso novoStatus) {
        this.status = novoStatus;
        System.out.println("Procecsso" + idPAdocao + "agora está em" + novoStatus);
    }
}

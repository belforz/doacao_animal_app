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
}

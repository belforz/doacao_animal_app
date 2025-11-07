package schemas;

public class VisitaDomiciliar extends EtapaProcesso {
    private String enderecoVisita;

    @Override
    public void executar () {
        System.out.println("Processando visita domiciliar" + enderecoVisita);
    }
    
}

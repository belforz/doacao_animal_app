package schemas;

public class Entrevista extends EtapaProcesso {
    private String avaliador;
    
    @Override
    public void executar (){
        System.out.println("Executando entrevista com o avaliador: " + avaliador);
    }
}

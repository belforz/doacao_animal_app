import DAO.*;
import exceptions.CustomException;
import schemas.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            // Teste de criação do DAO
            AdotanteDAO adotantedao = new AdotanteDAO();
            ProtetorDAO protetordao = new ProtetorDAO();
            AnimalDAO animaldao = new AnimalDAO();
            FotoAnimalDAO fotoanimaldao = new FotoAnimalDAO();
            MensagemDAO mensagemdao = new MensagemDAO();
            ProcessoAdocaoDAO processoadocaodao = new ProcessoAdocaoDAO();
            SuportePosAdocaoDAO suporteposadocaodao = new SuportePosAdocaoDAO();
            EtapaProcessoDAO etapaprocessodao = new EtapaProcessoDAO();
            AdocaoDAO adocaodao = new AdocaoDAO();

            System.out.println("DAO Adotante criado com sucesso!" + adotantedao);
            System.out.println("DAO Protetor criado com sucesso!" + protetordao);
            System.out.println("DAO Animal criado com sucesso!" + animaldao);
            System.out.println("DAO FotoAnimal criado com sucesso!" + fotoanimaldao);
            System.out.println("DAO Mensagem criado com sucesso!" + mensagemdao);
            System.out.println("DAO ProcessoAdocao criado com sucesso!" + processoadocaodao);
            System.out.println("DAO SuportePosAdocao criado com sucesso!" + suporteposadocaodao);
            System.out.println("DAO EtapaProcesso criado com sucesso!" + etapaprocessodao);
            System.out.println("DAO Adocao criado com sucesso!" + adocaodao);


            LocalDate localdate = LocalDate.now();
            Date data = Date.valueOf(localdate);
            // Teste de criar
            // Adotante
            Adotante adotante = new Adotante(null, "Leandro", "1234568", "Rua B, 488", "macedobeiramar@hotmail.com", "041657201", "11111111", "Adotante");
            adotantedao.create(adotante);
            System.out.println("Adotante criado com ID: " + adotante.getId());
            // Protetor
            Protetor protetor = new Protetor(null, "Leandro Protetor", "2345678", "Rua C, 123", "macedobeiramaru@hotmail.com", "456678765", "22222222", "Protetor");
            protetordao.create(protetor);
            System.out.println("Protetor criado com ID: " + protetor.getId());
            // Animal
            Animal animal = new Animal(null, "Rex", "Cachorro", "Labrador", "Bom", "Masculino", "Grande", false, 2, 'M', "BEM", protetor.getId());
            animaldao.create(animal);
            System.out.println("Animal criado com ID: " + animal.getId());
            // FotoAnimal
            FotoAnimal fotoAnimal = new FotoAnimal("http://example.com/foto1.jpg", "Foto frontal", animal.getId(), protetor.getId());
            fotoanimaldao.create(fotoAnimal);
            System.out.println("FotoAnimal criado com ID: " + fotoAnimal.getIdFotoAnimal());

            // ProcessoAdocao
            ProcessoAdocao processoAdocao = new ProcessoAdocao(null, animal, adotante, StatusProcesso.ENTREVISTA, localdate, animal.getId(), adotante.getId());
            processoadocaodao.create(processoAdocao);
            // Mensagem
            Mensagem mensagem = new Mensagem(data, "Olá, estou interessado em adotar o Rex.", adotante.getId(), protetor.getId(), "Adotante", "Protetor", processoAdocao.getIdPAdocao());
            mensagemdao.create(mensagem);
            System.out.println("Mensagem criada com ID: " + mensagem.getIdMensagem());

            // EtapaProcesso
            EtapaProcesso etapa = new EtapaProcesso(null, localdate, "Entrevista inicial", "PENDENTE", "ENTREVISTA", processoAdocao.getIdPAdocao());
            etapaprocessodao.create(etapa);
            System.out.println("EtapaProcesso criada com ID: " + etapa.getId());

            //Adocao
            Adocao adocao = new Adocao(null, localdate, "Adoção concluída", "Termos aceitos", processoAdocao.getIdPAdocao());
            adocaodao.create(adocao);
            System.out.println("Adocao criada com ID: " + adocao.getIdAdocao());

            // SuportePosAdocao
            SuportePosAdocao suporte = new SuportePosAdocao(null, localdate, "Consulta veterinária", "Ajuda com vacinas", adocao.getIdAdocao());
            suporteposadocaodao.create(suporte);
            System.out.println("SuportePosAdocao criado com ID: " + suporte.getIdSuporte());

            // Teste de leitura
            Adotante adt = adotantedao.read(adotante.getId());
            if (adt != null) {
                System.out.println("Adotante lido: " + adt.getNome());
            }
            Protetor prt = protetordao.read(protetor.getId());
            if (prt != null) {
                System.out.println("Protetor lido: " + prt.getNome());
            }
            // Teste de leitura para Animal
            Animal anm = animaldao.read(animal.getId());
            if (anm != null) {
                System.out.println("Animal lido: " + anm.getNome());
            }
            // Teste de leitura para ProcessoAdocao
            ProcessoAdocao proc = processoadocaodao.read(processoAdocao.getIdPAdocao());
            if (proc != null) {
                System.out.println("ProcessoAdocao lido: " + proc.getStatus());
            }
            // Teste de leitura para Adocao
            Adocao ado = adocaodao.read(adocao.getIdAdocao());
            if (ado != null) {
                System.out.println("Adocao lida: " + ado.getDescricao());
            }

            // Teste de atualizar
            adotante.setNome("Leandro Atualizado");
            adotantedao.update(adotante);
            System.out.println("Adotante atualizado para: " + adotante.getNome());
            // Teste de atualizar Protetor
            protetor.setNome("Protetor Atualizado");
            protetordao.update(protetor);
            System.out.println("Protetor atualizado para: " + protetor.getNome());
            // Teste de atualizar Animal
            animal.setNome("Rex Atualizado");
            animaldao.update(animal);
            System.out.println("Animal atualizado para: " + animal.getNome());

            // Teste de deletar
            mensagemdao.delete(mensagem.getIdMensagem());
            System.out.println("Mensagem deletada com ID: " + mensagem.getIdMensagem());
            // Teste de deletar EtapaProcesso
            etapaprocessodao.delete(etapa.getId());
            System.out.println("EtapaProcesso deletada com ID: " + etapa.getId());
            // Teste de deletar SuportePosAdocao
            suporteposadocaodao.delete(suporte.getIdSuporte());
            System.out.println("SuportePosAdocao deletado com ID: " + suporte.getIdSuporte());

        } catch (CustomException ce) {
            System.err.println("Erro personalizado: " + ce.getMessage());
        } catch (Exception ex) {
            System.err.println("Erro inesperado: " + ex.getMessage());
        }
    }};


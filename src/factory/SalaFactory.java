package factory;

import model.Sala;
import model.SalaIndividual;
import model.SalaGrupo;
import model.Laboratorio;

public class SalaFactory {

    public static Sala criarSala(TipoSala tipo, String nome) {
        return switch (tipo) {
            case INDIVIDUAL -> new SalaIndividual(nome);
            case GRUPO -> new SalaGrupo(nome);
            case LABORATORIO -> new Laboratorio(nome);
        };
    }
} 
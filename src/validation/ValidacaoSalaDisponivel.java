package validation;

import model.Reserva;
import java.util.List;

//Handler 3: valida se a sala está disponível no horário solicitado.
//detecta conflitos de horário com reservas já existentes, retornando uma mensagem com o erro.

// complementa as strategies existentes, atuando como uma camada de validação prévia.

public class ValidacaoSalaDisponivel extends ValidacaoBase {

    @Override
    public ResultadoValidacao validar(Reserva novaReserva, List<Reserva> reservasExistentes) {
        if (novaReserva.getSala() == null) {
            return ResultadoValidacao.falha("Reserva recusada: sala não informada.");
        }

        for (Reserva existente : reservasExistentes) {
            boolean mesmaSala = existente.getSala().equals(novaReserva.getSala());
            boolean horariosColidem =
                    novaReserva.getInicio().isBefore(existente.getFim()) &&
                    novaReserva.getFim().isAfter(existente.getInicio());

            if (mesmaSala && horariosColidem) {
                return ResultadoValidacao.falha(
                        "Reserva recusada: a sala '" + novaReserva.getSala().getNome()
                        + "' já está ocupada das " + existente.getInicio()
                        + " às " + existente.getFim()
                        + " pelo usuário " + existente.getUsuario().getNome() + ".");
            }
        }

        // Validação passou — segue para o próximo handler
        return validarProximo(novaReserva, reservasExistentes);
    }
}
package validation;

import model.Reserva;
import java.util.List;

//Handler 2: valida se o intervalo de horário da reserva é coerente.
//Regra: O horário de início deve ser anterior ao horário de fim.

public class ValidacaoHorario extends ValidacaoBase {

    @Override
    public ResultadoValidacao validar(Reserva novaReserva, List<Reserva> reservasExistentes) {
        if (novaReserva.getInicio() == null || novaReserva.getFim() == null) {
            return ResultadoValidacao.falha("Reserva recusada: horário de início ou fim não informado.");
        }

        if (!novaReserva.getInicio().isBefore(novaReserva.getFim())) {
            return ResultadoValidacao.falha(
                    "Reserva recusada: o horário de início deve ser anterior ao horário de fim.");
        }

        // Validação passou — segue para o próximo handler
        return validarProximo(novaReserva, reservasExistentes);
    }
}
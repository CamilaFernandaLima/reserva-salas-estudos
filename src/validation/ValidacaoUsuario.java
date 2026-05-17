package validation;

import model.Reserva;
import java.util.List;

//Handler 1: valida se o usuário associado à reserva é válido (não nulo e com nome)
public class ValidacaoUsuario extends ValidacaoBase {

    @Override
    public ResultadoValidacao validar(Reserva novaReserva, List<Reserva> reservasExistentes) {
        if (novaReserva.getUsuario() == null) {
            return ResultadoValidacao.falha("Reserva recusada: usuário não informado.");
        }

        if (novaReserva.getUsuario().getNome() == null
                || novaReserva.getUsuario().getNome().isBlank()) {
            return ResultadoValidacao.falha("Reserva recusada: nome do usuário não pode ser vazio.");
        }

        // Validação passou — segue para o próximo handler
        return validarProximo(novaReserva, reservasExistentes);
    }
}
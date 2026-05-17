package validation;

import model.Reserva;
import model.SalaIndividual;
import model.TipoUsuario;
import java.util.List;

//Handler 4: valida a compatibilidade entre o tipo de sala e o tipo de usuário.
 /* Regras:
 *   - Professores podem reservar qualquer tipo de sala.
 *   - Estudantes não podem reservar laboratórios.
 *   - SalaIndividual tem capacidade 1.
 */
public class ValidacaoCapacidade extends ValidacaoBase {

    @Override
    public ResultadoValidacao validar(Reserva novaReserva, List<Reserva> reservasExistentes) {
        if (novaReserva.getSala() == null || novaReserva.getUsuario() == null) {
            // Já tratado pelos handlers anteriores; passa adiante se chegou aqui
            return validarProximo(novaReserva, reservasExistentes);
        }

        boolean ehEstudante = novaReserva.getUsuario().getTipo() == TipoUsuario.ESTUDANTE;
        boolean ehLaboratorio = novaReserva.getSala().getClass().getSimpleName()
                                           .equals("Laboratorio");
        boolean ehSalaIndividual = novaReserva.getSala() instanceof SalaIndividual;

        if (ehEstudante && ehLaboratorio) {
            return ResultadoValidacao.falha(
                    "Reserva recusada: estudantes não podem reservar laboratórios. "
                    + "Apenas professores têm acesso a este tipo de sala.");
        }

        if (ehSalaIndividual && novaReserva.getSala().getCapacidade() < 1) {
            return ResultadoValidacao.falha(
                    "Reserva recusada: a sala individual não possui capacidade suficiente.");
        }

        // Validação passou
        return validarProximo(novaReserva, reservasExistentes);
    }
}
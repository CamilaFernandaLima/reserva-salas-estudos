package strategy;

import model.Reserva;
import model.TipoUsuario;

import java.util.List;

public class PoliticaPrioridadeDocente implements PoliticaDeReserva {

    @Override
    public boolean podeReservar(Reserva novaReserva, List<Reserva> reservasExistentes) {
        for (Reserva reserva : reservasExistentes) {
            boolean mesmaSala = reserva.getSala().equals(novaReserva.getSala());

            boolean horariosColidem =
                    novaReserva.getInicio().isBefore(reserva.getFim()) &&
                            novaReserva.getFim().isAfter(reserva.getInicio());

            if (mesmaSala && horariosColidem) {
                return novaReserva.getUsuario().getTipo() == TipoUsuario.PROFESSOR;
            }
        }
        return true;
    }
}
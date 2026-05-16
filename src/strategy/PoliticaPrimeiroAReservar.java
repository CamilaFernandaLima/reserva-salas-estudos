package strategy;

import model.Reserva;

import java.util.List;

public class PoliticaPrimeiroAReservar implements PoliticaDeReserva {

    @Override
    public boolean podeReservar(Reserva novaReserva, List<Reserva> reservasExistentes) {
        for (Reserva reserva : reservasExistentes) {
            boolean mesmaSala = reserva.getSala().equals(novaReserva.getSala());

            boolean horariosColidem =
                    novaReserva.getInicio().isBefore(reserva.getFim()) &&
                            novaReserva.getFim().isAfter(reserva.getInicio());

            if (mesmaSala && horariosColidem) {
                return false;
            }
        }
        return true;
    }
}
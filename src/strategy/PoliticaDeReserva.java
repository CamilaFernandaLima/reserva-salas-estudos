package strategy;

import model.Reserva;

import java.util.List;

public interface PoliticaDeReserva {

    boolean podeReservar(Reserva novaReserva, List<Reserva> reservasExistentes);
}
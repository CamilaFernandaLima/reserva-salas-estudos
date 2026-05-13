package observer;

import model.Reserva;

public interface ObservadorReserva {

    void atualizar(String mensagem, Reserva reserva);
} 
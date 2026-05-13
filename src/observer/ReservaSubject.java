package observer;

import model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaSubject {

    private List<ObservadorReserva> observadores = new ArrayList<>();

    public void adicionarObservador(ObservadorReserva observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorReserva observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String mensagem, Reserva reserva) {
        for (ObservadorReserva observador : observadores) {
            observador.atualizar(mensagem, reserva);
        }
    }
} 
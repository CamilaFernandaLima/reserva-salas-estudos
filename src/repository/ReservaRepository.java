package repository;

import model.Reserva;
import model.Sala;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {

    private static volatile ReservaRepository instance;

    private final List<Sala> salas;
    private final List<Reserva> reservas;

    private ReservaRepository() {
        this.salas = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public static ReservaRepository getInstance() {
        if (instance == null) {
            synchronized (ReservaRepository.class) {
                if (instance == null) {
                    instance = new ReservaRepository();
                }
            }
        }

        return instance;
    }

    public void adicionarSala(Sala sala) {
        salas.add(sala);
    }

    public List<Sala> listarSalas() {
        return salas;
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservas;
    }

    public Reserva buscarReservaPorId(int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        return null;
    }

    public void removerReserva(Reserva reserva) {
        reservas.remove(reserva);
    }
} 
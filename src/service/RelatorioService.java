package service;

import model.Reserva;
import repository.ReservaRepository;

import java.time.LocalDate;
import java.util.List;

public class RelatorioService {

    private ReservaRepository repository;

    public RelatorioService() {
        this.repository = ReservaRepository.getInstance();
    }

    public void gerarRelatorioDiario(LocalDate data) {
        List<Reserva> reservas = repository.listarReservas();

        System.out.println("Relatório diário de reservas - " + data);
        System.out.println("--------------------------------------");

        boolean encontrouReserva = false;

        for (Reserva reserva : reservas) {
            boolean mesmaData = reserva.getInicio().toLocalDate().equals(data);

            if (mesmaData) {
                encontrouReserva = true;

                System.out.println("Sala: " + reserva.getSala().getNome());
                System.out.println("Usuário: " + reserva.getUsuario().getNome());
                System.out.println("Início: " + reserva.getInicio());
                System.out.println("Fim: " + reserva.getFim());
                System.out.println("--------------------------------------");
            }
        }

        if (!encontrouReserva) {
            System.out.println("Nenhuma reserva encontrada para esta data.");
        }
    }
} 
package observer;

import model.Reserva;

public class ServicoRelatorioNotificacao implements ObservadorReserva {

    @Override
    public void atualizar(String mensagem, Reserva reserva) {
        System.out.println("Serviço de relatório recebeu atualização: "
                + mensagem + " | Sala: " + reserva.getSala().getNome());
    }
} 
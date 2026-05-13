package observer;

import model.Reserva;

public class UsuarioNotificacao implements ObservadorReserva {

    private String nome;

    public UsuarioNotificacao(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String mensagem, Reserva reserva) {
        System.out.println("Notificação para " + nome + ": " + mensagem
                + " | Reserva ID: " + reserva.getId());
    }
} 
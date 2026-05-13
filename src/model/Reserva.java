package model;

import java.time.LocalDateTime;

public class Reserva {

    private static int contador = 1;

    private int id;
    private Usuario usuario;
    private Sala sala;
    private LocalDateTime inicio;
    private LocalDateTime fim; 

    public Reserva(Usuario usuario, Sala sala,
                   LocalDateTime inicio,
                   LocalDateTime fim) {

        this.id = contador++;
        this.usuario = usuario;
        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void alterarHorario(LocalDateTime novoInicio, LocalDateTime novoFim) {
        this.inicio = novoInicio;
        this.fim = novoFim;
    }
}
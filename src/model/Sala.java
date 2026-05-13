package model;

public abstract class Sala {

    protected String nome;
    protected int capacidade;

    public Sala(String nome, int capacidade) {
        this.nome = nome;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    } 

    public int getCapacidade() {
        return capacidade;
    }
}
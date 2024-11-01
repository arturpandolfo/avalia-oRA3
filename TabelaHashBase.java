package org.example;

public abstract class TabelaHashBase {
    protected int tamanho;
    protected int colisoes;

    public TabelaHashBase(int tamanho) {
        this.tamanho = tamanho;
        this.colisoes = 0;
    }

    public abstract void inserir(Registro registro, String tipoHash);
    public abstract boolean buscar(int codigo, String tipoHash);
    public abstract int getColisoes();
}

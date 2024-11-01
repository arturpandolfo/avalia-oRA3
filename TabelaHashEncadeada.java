package org.example;

import java.util.LinkedList;

public class TabelaHashEncadeada extends TabelaHashBase {
    private LinkedList<Registro>[] tabela;

    @SuppressWarnings("unchecked")
    public TabelaHashEncadeada(int tamanho) {
        super(tamanho);
        tabela = new LinkedList[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int funcaoHash(int codigo, String tipo) {
        switch (tipo) {
            case "divisao":
                return codigo % tamanho;
            case "multiplicacao":
                double A = 0.6180339887;
                return (int) (tamanho * (codigo * A % 1));
            case "dobramento":
                return Math.abs((codigo / 1000) + (codigo % 1000)) % tamanho;
            default:
                throw new IllegalArgumentException("Função hash inválida");
        }
    }

    public void inserir(Registro registro, String tipoHash) {
        int indice = funcaoHash(registro.getCodigo(), tipoHash);
        if (!tabela[indice].isEmpty()) colisoes++;
        tabela[indice].add(registro);
    }

    public boolean buscar(int codigo, String tipoHash) {
        int indice = funcaoHash(codigo, tipoHash);
        for (Registro registro : tabela[indice]) {
            if (registro.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getColisoes() {
        return colisoes;
    }
}

package org.example;

public class TabelaHashRehashing extends TabelaHashBase {
    private Registro[] tabela;

    public TabelaHashRehashing(int tamanho) {
        super(tamanho);
        tabela = new Registro[tamanho];
    }

    // define a função
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
        int tentativas = 0;

        while (tabela[indice] != null && tentativas < tamanho) {
            colisoes++;
            tentativas++;
            indice = (indice + tentativas * tentativas) % tamanho;
        }

        tabela[indice] = registro;
    }

    public boolean buscar(int codigo, String tipoHash) {
        int indice = funcaoHash(codigo, tipoHash);
        int tentativas = 0;

        while (tabela[indice] != null && tentativas < tamanho) {
            if (tabela[indice].getCodigo() == codigo) {
                return true;
            }
            tentativas++;
            indice = (indice + tentativas * tentativas) % tamanho;
        }
        return false;
    }

    @Override
    public int getColisoes() {
        return colisoes;
    }
}

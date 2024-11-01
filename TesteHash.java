package org.example;

import java.util.Random;

public class TesteHash {

    public static void main(String[] args) {
        int[] tamanhos = {10, 100, 1000};
        String[] tiposHash = {"divisao", "multiplicacao", "dobramento"};
        int[] tamanhosDados = {1000000, 5000000, 20000000};

        Random random = new Random(42);
        StringBuilder resultados = new StringBuilder();

        for (int tamanhoTabela : tamanhos) {
            for (String tipoHash : tiposHash) {
                TabelaHashEncadeada tabelaEncadeada = new TabelaHashEncadeada(tamanhoTabela);
                TabelaHashRehashing tabelaRehashing = new TabelaHashRehashing(tamanhoTabela);

                for (int tamanhoDados : tamanhosDados) {
                    resultados.append("Tabela Encadeada - Tamanho: ")
                            .append(tamanhoTabela)
                            .append(", TipoHash: ")
                            .append(tipoHash)
                            .append(", Dados: ")
                            .append(tamanhoDados)
                            .append("\n");

                    // aqui vai inserir os dados
                    long inicio = System.nanoTime();
                    for (int i = 0; i < tamanhoDados; i++) {
                        int codigo = 100000000 + random.nextInt(900000000);
                        tabelaEncadeada.inserir(new Registro(codigo), tipoHash);
                    }
                    long fim = System.nanoTime();
                    resultados.append("Tempo de Inserção (Encadeada): ")
                            .append((fim - inicio) / 1_000_000)
                            .append(" ms\n");
                    resultados.append("Colisões (Encadeada): ")
                            .append(tabelaEncadeada.getColisoes())
                            .append("\n");

                    resultados.append("Tabela Rehashing - Tamanho: ")
                            .append(tamanhoTabela)
                            .append(", TipoHash: ")
                            .append(tipoHash)
                            .append(", Dados: ")
                            .append(tamanhoDados)
                            .append("\n");

                    // rehashing e tempo
                    inicio = System.nanoTime();
                    for (int i = 0; i < tamanhoDados; i++) {
                        int codigo = 100000000 + random.nextInt(900000000);
                        tabelaRehashing.inserir(new Registro(codigo), tipoHash);
                    }
                    fim = System.nanoTime();
                    resultados.append("Tempo de Inserção (Rehashing): ")
                            .append((fim - inicio) / 1_000_000)
                            .append(" ms\n");
                    resultados.append("Colisões (Rehashing): ")
                            .append(tabelaRehashing.getColisoes())
                            .append("\n\n");
                }
            }
        }

        // resultado
        System.out.println(resultados.toString());
    }
}

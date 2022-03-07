import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Resolução do desafio do Tabuleiro do Sr. Mili
 * utilizando um tabuleiro N X N
 * @author Cosmo Lustosa
 */
public class TabuleiroMili {
    Map<Integer, Integer> frequencia = new HashMap<>();
    static int[][] matriz;

    public static void main(String[] args) throws IOException {
        int dimensao, qtdOperacoes, tipoOperacao;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] recebeEntradas = br.readLine().split(" ");
        dimensao = Integer.parseInt(recebeEntradas[0]);
        qtdOperacoes = Integer.parseInt(recebeEntradas[1]);
        matriz = new int[dimensao][dimensao];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = 0;
            }
        }
        for (; qtdOperacoes > 0; qtdOperacoes--) {
            recebeEntradas = br.readLine().split(" ");
            tipoOperacao = Integer.parseInt(recebeEntradas[0]);
            switch (tipoOperacao) {
                case 1:
                    int linha = Integer.parseInt(recebeEntradas[1]);
                    int valor = Integer.parseInt(recebeEntradas[2]);
                    atribuiValorLinha(linha, valor, matriz);
                    break;
                case 2:
                    int coluna = Integer.parseInt(recebeEntradas[1]);
                    valor = Integer.parseInt(recebeEntradas[2]);
                    atribuiValorColuna(coluna, valor, matriz);
                    break;
                case 3:
                case 4:
                    int op = Integer.parseInt(recebeEntradas[0]);
                    int eixo = Integer.parseInt(recebeEntradas[1]);
                    imprimeMaiorValor(op, eixo, matriz);
                    break;
            }
        }

    }

    public static void atribuiValorLinha(int linha, int valor, int[][] matriz) {
        for (int j = 0; j < matriz.length; j++) {
            matriz[linha - 1][j] = valor;
        }
    }

    public static void atribuiValorColuna(int coluna, int valor, int[][] matriz) {
        for (int j = 0; j < matriz.length; j++) {
            matriz[j][coluna - 1] = valor;
        }
    }

    public static void imprimeMaiorValor(int op, int eixo, int[][] matriz) {
        int maiorFrequencia = 0;
        Map<Integer, Integer> duplicacao = new HashMap<>();
        List<Integer> numeros = new ArrayList<>();

        if (op == 3) {
            for (int i = 0; i < matriz.length; i++) {
                numeros.add(matriz[eixo - 1][i]);
            }
        }
        if (op == 4) {
            for (int i = 0; i < matriz.length; i++) {
                numeros.add(matriz[i][eixo - 1]);
            }
        }
        for (Integer numero : numeros) {
            int frequencia = Collections.frequency(numeros, numero);
            duplicacao.put(numero, frequencia);
        }

        List<Integer> maioresRepeticoes = new ArrayList<>(); //add as maiores repeticoes
        maiorFrequencia = Collections.max(duplicacao.values()); //recebe a maior frequencia de repetições
        List<Integer> chaves = new ArrayList<>(duplicacao.keySet());

        for (Integer chave : chaves) {
            Integer qtRepeticoes = duplicacao.get(chave);
            if (qtRepeticoes >= maiorFrequencia) {
                maioresRepeticoes.add(chave);
            }
        }
        Integer maiorNumero;
        if (maioresRepeticoes.size() == 1) {
            maiorNumero = Collections.max(chaves);

        } else {
            maiorNumero = Collections.max(maioresRepeticoes); //maior valor da linha
        }
        System.out.println(maiorNumero);
    }
}
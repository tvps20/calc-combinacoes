import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static int countTotal = 0; // Contator de combinações
    private static final boolean writeFile = true; // Escrever um arquivo de texto
    private static final int qtdCombinations = 8; // Combinações de 8 elementos
    private static final int[] elementos = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25}; // Elementos para combincação
    private static BufferedWriter bufferedWriter;

    public static void main(String[] args) throws IOException {
        generateCombinations(elementos, qtdCombinations);
        System.out.println("total: " + countTotal);

        if (bufferedWriter != null) {
            bufferedWriter.close();
        }
    }

    public static void generateCombinations(int[] elementos, int tamanhoCombinacao) throws IOException {
        if (tamanhoCombinacao > elementos.length) {
            System.out.println("A quantidade de elementos para a combinação é maior que a quantidade de elementos da amostra.");
            return;
        }

        int[] combinacao = new int[tamanhoCombinacao];
        generateRecursiveCombinations(elementos, combinacao, 0, 0);
    }

    private static void generateRecursiveCombinations(int[] elementos, int[] combinacao, int indiceCombinacao, int indiceElementos) throws IOException {
        try {
            if (indiceCombinacao == combinacao.length) {
                if (writeFile) {
                    writerCombinations(combinacao, ";");
                } else {
                    printCombinations(combinacao, ";");
                }

                countTotal++;
                return;
            }

            for (int i = indiceElementos; i < elementos.length; i++) {
                combinacao[indiceCombinacao] = elementos[i];
                generateRecursiveCombinations(elementos, combinacao, indiceCombinacao + 1, i + 1);
            }
        } catch (IOException ex) {
            bufferedWriter.close();
            throw new RuntimeException(ex);
        }
    }

    private static void printCombinations(int[] combinacao, String separator) {
        var line = new StringBuilder();

        for (int j = 0; j < combinacao.length; j++) {
            if (j == (combinacao.length - 1)) {
                line.append(combinacao[j]);
            } else {
                line.append(combinacao[j]).append(separator);
            }
        }

        line.append(System.lineSeparator());
        System.out.printf(line.toString());
    }

    private static void writerCombinations(int[] combinacao, String separator) throws IOException {
        if (bufferedWriter == null) {
            bufferedWriter = new BufferedWriter(new FileWriter("combinacoes.txt"));
        }

        var line = new StringBuilder();

        for (int j = 0; j < combinacao.length; j++) {
            if (j == (combinacao.length - 1)) {
                line.append(combinacao[j]);
            } else {
                line.append(combinacao[j]).append(separator);
            }
        }

        bufferedWriter.write(line + System.lineSeparator());
    }
}

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        Scanner keyboard = new Scanner(System.in);
//        int quantidadeVertices = keyboard.nextInt();
//        File entrada = new File("entrada.txt");
        BufferedReader reader = new BufferedReader(new FileReader("/home/rafael/grands/genetico_pcv/src/entrada.txt"));
        int quantidadeVertices = Integer.parseInt(reader.readLine());
        int[] populacao = new int[quantidadeVertices];

         float[][] matrizAdjacente = new float[quantidadeVertices][quantidadeVertices];
        HashMap<Integer, Vertice> vertices = new HashMap<>();
        for (int i = 0; i < quantidadeVertices; i++) {
            populacao[i] = i;
            String[] linha = reader.readLine().split("(\\s)+");
            Vertice vertice = new Vertice(linha,i);
            calcularDistancias(vertice,matrizAdjacente,vertices);
            vertices.put(i, vertice);
        }

        System.out.println(quantidadeVertices);

        algoritmo(new Rota(populacao),matrizAdjacente);
    }



    private static void algoritmo(Rota inicial,float[][] matriz) {
        Rota[] populacao = populacaoInicial(inicial,matriz);
        gerarAleatorio(inicial);
        inicial.calcularPesoTotal(matriz);
        System.out.println(Arrays.toString(inicial.getValores()));
        System.out.println(inicial.getPesoTotal());
    }

    private static Rota[] populacaoInicial(Rota inicial, float[][] matriz) {
        for(int i = 0; i < matriz.length;i++){

        }
    }

    public static void gerarAleatorio(Rota rota){
        Random random = new Random();
        int[] arr = rota.getValores();
        for(int i = 0;i<arr.length;i++){
            int position = random.nextInt(arr.length);
            int positionAux = arr[position];
            arr[position] = arr[i];
            arr[i] = positionAux;
        }
        rota.setValores(arr);
    }

    private static void calcularDistancias(Vertice vertice, float[][] matrizAdjacente, HashMap<Integer,Vertice> vertices) {
        matrizAdjacente[vertice.getIndice()][vertice.getIndice()] = 0;
        for(Vertice v:vertices.values()){
            matrizAdjacente[vertice.getIndice()][v.getIndice()] =
                    matrizAdjacente[v.getIndice()][vertice.getIndice()] =
                    vertice.calcularDistancia(v);
        }
    }

}

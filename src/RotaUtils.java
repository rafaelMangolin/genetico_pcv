import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by rafael on 06/08/16.
 */
public class RotaUtils {
    private static float[][] matrizAdjacente;

    public static float[][] matrizAdjacente(int quantidadeVertices, BufferedReader reader) throws IOException {
        matrizAdjacente = new float[quantidadeVertices][quantidadeVertices];
        HashMap<Integer, Vertice> vertices = new HashMap<>();
        for (int i = 0; i < quantidadeVertices; i++) {
            String[] linha = reader.readLine().split("(\\s)+");
            Vertice vertice = new Vertice(linha,i);
            calcularDistancias(vertice,vertices);
            vertices.put(i, vertice);
        }
        return matrizAdjacente;
    }

    private static void calcularDistancias(Vertice vertice, HashMap<Integer,Vertice> vertices) {
        matrizAdjacente[vertice.getIndice()][vertice.getIndice()] = 0;
        for(Vertice v:vertices.values()){
            matrizAdjacente[vertice.getIndice()][v.getIndice()] =
                    matrizAdjacente[v.getIndice()][vertice.getIndice()] =
                            vertice.calcularDistancia(v);
        }
    }
}

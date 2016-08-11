import java.io.*;

public class Main {
    private static float[][] matrizAdjacente;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/rafael/grands/genetico_pcv/src/entrada.txt"));
        int quantidadeVertices = Integer.parseInt(reader.readLine());

        matrizAdjacente = RotaUtils.matrizAdjacente(quantidadeVertices,reader);
        Populacao populacao = new Populacao();
        populacao.inicializar(Genetico.TAMANHO_POPULACAO,matrizAdjacente);
        Genetico.setMatrizAdjacencia(matrizAdjacente);

        long start = System.currentTimeMillis();
        Float atual;
        long breakT;
        do{
            System.out.println(populacao.obterMelhor().getPesoTotal());
            populacao = Genetico.atualizarPopulacao(populacao);
            breakT = (System.currentTimeMillis()-start);
        }while(breakT < Genetico.TEMPO_PARADA);
        atual = populacao.obterMelhor().getPesoTotal();
        System.out.println(atual + " " +  breakT);

    }
}

import java.util.*;

/**
 * Created by rafael on 06/08/16.
 */
public class Genetico {
    private static final int TAMANHO_TORNEIO = 4;
    private static final float TAXA_MUTACAO = .0015f;
    private static final boolean ELITISMO = true;
    public static final long TEMPO_PARADA = 9000;
    public static final int TAMANHO_POPULACAO = 500;

    private static Random random = new Random();

    public static float[][] matrizAdjacencia;

    public static Populacao atualizarPopulacao(Populacao populacao) {
        Populacao novaPopulacao = new Populacao(populacao.tamanho());

        int indiceElitista = 0;
        if (ELITISMO) {
            novaPopulacao.adicionaRota(0, populacao.obterMelhor());
            indiceElitista = 1;
        }

        for (int i = indiceElitista; i < novaPopulacao.tamanho(); i++) {
            Rota pai = torneio(populacao);
            Rota mae = torneio(populacao);
            Rota filho = retornaFilho(pai, mae);
            novaPopulacao.adicionaRota(i, filho);
        }

        for (int i = indiceElitista; i < novaPopulacao.tamanho(); i++) {
            mutacao(novaPopulacao.pegarRota(i));
        }

        int indiceBusca = random.nextInt(novaPopulacao.tamanho());
        Rota toSearch = novaPopulacao.pegarRota(indiceBusca);
//        int indiceBusca = 0;
//        Rota toSearch = novaPopulacao.obterPior();
        novaPopulacao.adicionaRota(indiceBusca,BuscaLocal.bestImprovement(toSearch));

        return novaPopulacao;
    }

    private static void mutacao(Rota mutante) {
        int indice = mutante.getValores().length;
        int i;
        int geneX;
        int geneY;
        Random random = new Random();
        for(i = 0; i < indice;i++){
            geneX = i;
            geneY = random.nextInt(indice);
            if(random.nextDouble()<TAXA_MUTACAO){
                int aux = mutante.getValores()[geneX];
                mutante.getValores()[geneX] = mutante.getValores()[geneY];
                mutante.getValores()[geneY] = aux;
            }

        }
    }

    private static Rota torneio(Populacao populacao) {
        Populacao torneio = new Populacao(TAMANHO_TORNEIO);
        for (int i = 0; i < TAMANHO_TORNEIO; i++) {
            int indice = random.nextInt(populacao.tamanho());
            torneio.adicionaRota(i, populacao.pegarRota(indice));
        }
        return torneio.obterMelhor();
    }

    private static Rota retornaFilho(Rota pai, Rota mae) {
        int position = random.nextInt((pai.getValores().length/2) - 1);
        Integer[] valores = new Integer[pai.getValores().length];
        Integer[] fixoPai = Arrays.copyOfRange(pai.getValores(),position,(pai.getValores().length/2) + position);
        Integer[] fixoMae = Arrays.copyOfRange(mae.getValores(),position,(mae.getValores().length/2) + position);
        Integer[] restoMae = outRange(mae.getValores(),position);
        int iFixo = 0;
        int iRestoFixo = 0;
        int iVariavel = 0;
        int i = 0;
        while(i<valores.length){
            if(i >= position && i < (position+(valores.length/2))){
                valores[i++] = fixoPai[iFixo++];
            }else{
                Integer variavel;
                if(iVariavel < fixoMae.length){
                    variavel = fixoMae[iVariavel++];
                }else{
                    variavel = restoMae[iRestoFixo++];
                }
                if(!containsInArrays(variavel,valores,fixoPai)){
                    valores[i++] = variavel;
                }
            }
        }

        return new Rota(valores, matrizAdjacencia);
    }

    private static Integer[] outRange(Integer[] valores, int position) {
        Integer[] arrPrefix = Arrays.copyOfRange(valores,0,position);
        Integer[] arrSufix = Arrays.copyOfRange(valores,position+(valores.length/2),valores.length);
        List<Integer> integers = new ArrayList<>(Arrays.asList(arrPrefix));
        integers.addAll(Arrays.asList(arrSufix));
        Integer[] toReturn = new Integer[integers.size()];
        return integers.toArray(toReturn);
    }

    private static boolean containsInArrays(Integer variavel, Integer[] valores, Integer[] fixos) {
        Set<Integer> toCompare = new HashSet<>(Arrays.asList(valores));
        toCompare.addAll(Arrays.asList(fixos));
        return toCompare.contains(variavel);
    }

    public static void setMatrizAdjacencia(float[][] matrizAdjacencia) {
        Genetico.matrizAdjacencia = matrizAdjacencia;
    }
}

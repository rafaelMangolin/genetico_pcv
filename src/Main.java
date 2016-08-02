import java.io.*;
import java.util.*;

public class Main {
    private static float[][] matrizAdjacente;
    public static void main(String[] args) throws IOException {
//        Scanner keyboard = new Scanner(System.in);
//        int quantidadeVertices = keyboard.nextInt();
//        File entrada = new File("entrada.txt");
//        BufferedReader reader = new BufferedReader(new FileReader("/home/rafael/grands/genetico_pcv/src/entrada.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("/home/rafael/grands/genetico_pcv/src/entrada2.txt"));
        int quantidadeVertices = Integer.parseInt(reader.readLine());
        Integer[] populacao = new Integer[quantidadeVertices];

        matrizAdjacente = new float[quantidadeVertices][quantidadeVertices];
        HashMap<Integer, Vertice> vertices = new HashMap<>();
        for (int i = 0; i < quantidadeVertices; i++) {
            populacao[i] = i;
            String[] linha = reader.readLine().split("(\\s)+");
            Vertice vertice = new Vertice(linha,i);
            calcularDistancias(vertice,vertices);
            vertices.put(i, vertice);
        }

        System.out.println(algoritmo(new Rota(populacao, matrizAdjacente)));
    }



    private static float algoritmo(Rota inicial) {
        Float primeiro;
        Float primeiroAtualizado;
        TreeMap<Float,Rota> populacao = populacaoInicial(inicial);
        do{
            primeiro = populacao.firstKey();
            atualizarPopulacao(populacao);
            primeiroAtualizado = populacao.firstKey();
        }while(primeiro != primeiroAtualizado);
        return populacao.firstEntry().getValue().getPesoTotal();

    }

    private static void atualizarPopulacao(TreeMap<Float, Rota> populacao) {
        int length = populacao.size();
        Iterator<Rota> rotaIterator = populacao.values().iterator();
        Set<Rota> filhos = geraFilhos(rotaIterator.next(),rotaIterator.next());
        for(Rota filho : filhos){
            populacao.put(filho.getPesoTotal(),filho);
            if(populacao.size() > length){
                removeLast(populacao);
            }
        }
    }

    private static void removeLast(TreeMap<Float, Rota> populacao) {
        Float key = populacao.lastKey();
        populacao.remove(key);
    }

    private static TreeMap<Float, Rota> populacaoInicial(Rota inicial) {
        int length  = matrizAdjacente.length;
        TreeMap<Float,Rota> toReturn = new TreeMap<>();
        for(int i = 0; i < length;i++){
            Rota toPut = gerarAleatorio(inicial);
            toReturn.put(toPut.getPesoTotal(),toPut);
        }
        return toReturn;
    }

    private static Rota gerarAleatorio(Rota rota){
        Random random = new Random();
        Integer[] arr = rota.getValores().clone();
        for(int i = 0;i<arr.length;i++){
            int position = random.nextInt(arr.length);
            int positionAux = arr[position];
            arr[position] = arr[i];
            arr[i] = positionAux;
        }
        return new Rota(arr,matrizAdjacente);
    }

    private static void calcularDistancias(Vertice vertice, HashMap<Integer,Vertice> vertices) {
        matrizAdjacente[vertice.getIndice()][vertice.getIndice()] = 0;
        for(Vertice v:vertices.values()){
            matrizAdjacente[vertice.getIndice()][v.getIndice()] =
                    matrizAdjacente[v.getIndice()][vertice.getIndice()] =
                    vertice.calcularDistancia(v);
        }
    }

    private static Set<Rota> geraFilhos(Rota rotaPai, Rota rotaMae){
        Set<Rota> toReturn = new HashSet<>();
        Random random = new Random();
        int position = random.nextInt((rotaPai.getValores().length/2) - 1);

        Integer[] fixoPai = Arrays.copyOfRange(rotaPai.getValores(),position,(rotaPai.getValores().length/2) + position);
        Integer[] variavelPai = outRange(rotaPai.getValores(),position);
        Integer[] fixoMae = Arrays.copyOfRange(rotaMae.getValores(),position,(rotaMae.getValores().length/2) + position);
        Integer[] variavelMae = outRange(rotaMae.getValores(),position);

        toReturn.add(retornaFilho(fixoPai,variavelMae,variavelPai,position));
        toReturn.add(retornaFilho(fixoMae,variavelPai,variavelMae,position));

        return toReturn;
    }

    private static Rota retornaFilho(Integer[] fixos, Integer[] variavels, Integer[] restoFixo, int position) {
        Integer[] valores = new Integer[matrizAdjacente.length];
        int iFixo = 0;
        int iRestoFixo = 0;
        int iVariavel = 0;
        int i = 0;
        while(i<valores.length){
            if(i >= position && i < (position+(valores.length/2))){
                valores[i++] = fixos[iFixo++];
            }else{
                Integer variavel;
                if(iVariavel < variavels.length){
                    variavel = variavels[iVariavel++];
                }else{
                    variavel = restoFixo[iRestoFixo++];
                }
                if(!containsInArrays(variavel,valores,fixos)){
                    valores[i++] = variavel;
                }
            }
        }
        return new Rota(valores,matrizAdjacente);
    }

    private static boolean containsInArrays(Integer variavel, Integer[] valores, Integer[] fixos) {
        Set<Integer> toCompare = new HashSet<>(Arrays.asList(valores));
        toCompare.addAll(Arrays.asList(fixos));
        return toCompare.contains(variavel);
    }

    private static Integer[] outRange(Integer[] valores, int position) {
        Integer[] arrPrefix = Arrays.copyOfRange(valores,0,position);
        Integer[] arrSufix = Arrays.copyOfRange(valores,position+(valores.length/2),valores.length);
        List<Integer> integers = new ArrayList<>(Arrays.asList(arrPrefix));
        integers.addAll(Arrays.asList(arrSufix));
        Integer[] toReturn = new Integer[integers.size()];
        return integers.toArray(toReturn);
    }

}

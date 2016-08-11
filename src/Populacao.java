import java.util.Iterator;

/**
 * Created by rafael and leonardo on 06/08/16.
 */
public class Populacao {
    private Rota[] populacao;


    public Populacao(){
    }

    public Populacao(int tamanho) {
        populacao = new Rota[tamanho];
    }

    public void inicializar(int tamanho, float[][] matrizAdjacencia){
        populacao = new Rota[tamanho];
        for (int i = 0; i < tamanho(); i++) {
            Rota novaRota = new Rota(matrizAdjacencia);
            adicionaRota(i, novaRota);
        }
    }

    public void adicionaRota(int i, Rota rota) {
        populacao[i] = rota;
    }

    public Rota pegarRota(int i){
        return populacao[i];
    }

    public int tamanho(){
        return populacao.length;
    }

    public Rota obterMelhor(){
        Rota melhor = populacao[0];
        int i;
        for(i=1;i<populacao.length;i++){
            if(melhor.getPesoTotal()>populacao[i].getPesoTotal()){
                melhor = populacao[i];
            }
        }
        return melhor;
    }

    public Rota obterPior() {
        Rota pior = populacao[0];
        for(int i=1;i<populacao.length;i++){
            if(pior.getPesoTotal()<populacao[i].getPesoTotal()){
                pior = populacao[i];
            }
        }
        return pior;
    }

    public Integer obterPiorIndice() {
        Rota pior = populacao[0];
        int indice = 0;
        for(int i=1;i<populacao.length;i++){
            if(pior.getPesoTotal()<populacao[i].getPesoTotal()){
                pior = populacao[i];
                indice = i;
            }
        }
        return indice;
    }

    public void add(Rota filho) {
        int indicePior = obterPiorIndice();
        if(filho.getPesoTotal() < populacao[indicePior].getPesoTotal()){
            populacao[indicePior] = filho;
        }
    }
}

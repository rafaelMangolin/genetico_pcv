import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

/**
 * Created by rafael on 06/08/16.
 */
public class BuscaLocal {

    public static Rota bestImprovement(Rota rota) {
        boolean melhoria = true;
        Rota atual = rota;
        while(melhoria){
            melhoria = false;
            float minimo = Float.MAX_VALUE;
            Rota rMinimo = null;
            for(Rota sL:vizinhos(atual)){
                if(minimo > sL.getPesoTotal()){
                    minimo = sL.getPesoTotal();
                    rMinimo = sL;
                }
            }
            if (minimo < atual.getPesoTotal()){
                atual = rMinimo;
                melhoria = true;
            }
        }
        return atual;
    }

    private static ArrayList<Rota> vizinhos(Rota atual) {
        ArrayList<Rota> toReturn = new ArrayList<>();
        for(int i = 0; i < atual.tamanho()-2; i++){
            for(int j = i+2;j<atual.tamanho();j++){
                toReturn.add(new Rota(troca2opt(atual.getValores(),i,j),Genetico.matrizAdjacencia));
            }
        }
        return toReturn;
    }

    private static Integer[] troca2opt(Integer[] valores, int i, int j) {
        Integer[] toReturn = valores.clone();
        for(int x = i+1;x < j; x++){
            toReturn[x] = valores[j-(x-(i+1))];
        }
        toReturn[j] = valores[i+1];
        return toReturn;
    }
}

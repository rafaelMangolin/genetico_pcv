import java.util.Arrays;

/**
 * Created by rafael on 22/07/16.
 */
public class Rota {
    private Integer[] valores;
    private float pesoTotal;

    public Rota(Integer[] populacao, float[][] matrizAdjacente) {
        this.valores = populacao;
        calcularPesoTotal(matrizAdjacente);
    }

    public Integer[] getValores() {
        return valores;
    }

    public void setValores(Integer[] valores) {
        this.valores = valores;
    }

    private void calcularPesoTotal(float[][] matriz){
        this.pesoTotal = 0;
        for(int i = 0;i< valores.length;i++){
            int a = valores[i];
            int b;
            if(i+1 < valores.length){
                b = valores[i+1];
            }else{
                b = valores[0];
            }
            this.pesoTotal += matriz[a][b];
        }
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    @Override
    public String toString() {
        return pesoTotal+"\n";
    }
}

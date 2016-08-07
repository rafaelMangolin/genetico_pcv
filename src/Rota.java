import java.util.Arrays;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

/**
 * Created by rafael on 22/07/16.
 */
public class Rota {
    private Integer[] valores;
    private float pesoTotal;

    private IntFunction<Integer> operator = (operand) -> operand;



    public Rota(int size) {
        valores = new Integer[size];
    }

    public Rota(Integer[] populacao, float[][] matrizAdjacente) {
        this.valores = populacao;
        calcularPesoTotal(matrizAdjacente);
    }

    public Rota(float[][] matrizAdjacencia) {
        this.valores = new Integer[matrizAdjacencia.length];
        Arrays.setAll(this.valores, operator);
        this.valores = novaRota(this.valores);
        calcularPesoTotal(matrizAdjacencia);
    }

    private Integer[] novaRota(Integer[] rota) {
        Random random = new Random();
        Integer[] arr = rota.clone();
        for(int i = 0;i<arr.length;i++){
            int position = random.nextInt(arr.length);
            int positionAux = arr[position];
            arr[position] = arr[i];
            arr[i] = positionAux;
        }
        return arr;
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

    public int tamanho() {
        return valores.length;
    }
}

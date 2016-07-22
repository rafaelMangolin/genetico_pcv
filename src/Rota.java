/**
 * Created by rafael on 22/07/16.
 */
public class Rota {
    private int[] valores;
    private float pesoTotal;

    public Rota(int[] populacao) {
        this.valores = populacao;
    }

    public int[] getValores() {
        return valores;
    }

    public void setValores(int[] valores) {
        this.valores = valores;
    }

    public void calcularPesoTotal(float[][] matriz){
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
}

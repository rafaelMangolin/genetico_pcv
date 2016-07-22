/**
 * Created by rafael on 21/07/16.
 */
public class Vertice {
    private int indice;
    private float x;
    private float y;

    public Vertice(String[] linha, int i) {
        this.indice = i;
        this.x = Float.parseFloat(linha[1]);
        this.y = Float.parseFloat(linha[2]);
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float calcularDistancia(Vertice v) {
        float distX = v.getX() - x;
        float distY = v.getY() - y;
        return ((float) Math.sqrt(distX * distX + distY * distY));
    }
}


import java.io.Serializable;


public class exemplar implements Serializable {

    private int numero;
    private int ISBN;
    private double preco;
    private int numero2;
    private String status;

    public exemplar(int numero, int ISBN, double preco, String status, int numero2) {
        this.numero = numero;
        this.ISBN = ISBN;
        this.preco = preco;
        this.status = status;
        this.numero2 = numero2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumero2() {
        return numero2;
    }

    public int getNumero() {
        return numero;
    }

    public int getISBN() {
        return ISBN;
    }

    public double getPreco() {
        return preco;
    }
}

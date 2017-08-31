
import java.io.Serializable;


public class associado implements Serializable {

    private int codigo;
    private String nome;
    private String endereco;
    private String email;
    private String status;

    public associado(int codigo, String nome, String endereco, String email, String status) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

}

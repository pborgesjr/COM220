
import java.io.Serializable;


public class publicacao implements Serializable {

    int ISBN;
    String titulo;
    String autor;
    String editora;


    public publicacao(int ISBN, String titulo, String autor, String editora) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
    }
    
    public int getISBN() {
        return ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

}

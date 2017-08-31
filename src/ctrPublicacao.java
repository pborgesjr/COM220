
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class ctrPublicacao {

    private final String arquivo = "discPub.dat";
    private Vector listaPublicacoes = new Vector();

    public ctrPublicacao() throws Exception {
        desserializaPub();
    }

    //Método de inserção 
    public void inserePublicacao(int pISBN, String pTitulo, String pAutor, String pEditora) throws Exception {
        publicacao pub = new publicacao(pISBN, pTitulo, pAutor, pEditora);
        listaPublicacoes.add(pub);
        serializaPub();
    }

    //Método de impressão da lista de publicações
    public String imprimePublicacoes() {
        String result = "";
        publicacao objPub = null;
        for (int intIdx = 0; intIdx < listaPublicacoes.size(); intIdx++) {
            objPub = (publicacao) listaPublicacoes.elementAt(intIdx);
            result += imprimePub(objPub);
        }
        if (result.equalsIgnoreCase("")) {
            return "Não existem publicações cadastradas.";
        } else {
            return result;
        }
    }

    //Método utilizado para imprimir uma determinada publicação
    public String imprimePub(publicacao pub) {
        return "Título :" + pub.getTitulo()
                + " -  ISBN : " + pub.getISBN()
                + " -  Autor : " + pub.getAutor()
                + " -  Editora : " + pub.getEditora()
                + "\n";
    }

    public void serializaPub() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaPublicacoes);
        objOS.flush();
        objOS.close();
    }

    public void desserializaPub() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaPublicacoes = (Vector) objIS.readObject();
            objIS.close();
        }
    }
}

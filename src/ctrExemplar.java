
import java.io.*;
import java.util.Vector;

public class ctrExemplar {

    private final String arquivo = "discExemp.dat";
    private Vector listaExemplares = new Vector();

    public ctrExemplar() throws Exception {
        desserializaExemplar();
    }

    //Método de inserção 
    public void insereExemplar(int pNumero, int pISBN, double pPreco, String pStatus, int pNumero2) throws Exception {
        exemplar exemp = new exemplar(pNumero, pISBN, pPreco, pStatus, pNumero2);
        listaExemplares.add(exemp);
        serializaExemplar();
    }

    public Vector getListaExemplares() throws Exception {
        return listaExemplares;
    }

    //Método de impressão da lista de exemplares
    public String imprimeExemplares(int ISBN) {
        String result = "";
        exemplar objExemplar = null;
        for (int intIdx = 0; intIdx < listaExemplares.size(); intIdx++) {
            objExemplar = (exemplar) listaExemplares.elementAt(intIdx);
            if (objExemplar.getISBN() == ISBN) {
                result += imprimeExemplar(objExemplar);
            }

        }
        if (result.equalsIgnoreCase("")) {
            return "Não existem exemplares cadastrados.";
        } else {
            return result;
        }
    }

    //Método utilizado para imprimir um determinado exemplar
    public String imprimeExemplar(exemplar exemp) {
        return "ISBN :" + exemp.getISBN()
                + " -  Número : " + exemp.getNumero()
                + " -  Preço : " + exemp.getPreco()
                + " -  Status : " + exemp.getStatus()
                + "\n";
    }

    public int getAuto(int pISBN) {
        int j = 0;
        if (listaExemplares.isEmpty() == true) {
            return j = 1;
        } else {
            int k = 1;
            for (int i = 0; i < listaExemplares.size(); i++) {
                exemplar exemp = (exemplar) listaExemplares.elementAt(i);
                if (exemp.getISBN() == pISBN) {
                    k++;
                }
            }
            return k;
        }

    }

    public void serializaExemplar() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaExemplares);
        objOS.flush();
        objOS.close();
    }

    public void desserializaExemplar() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaExemplares = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public void setaStatus(int numero) throws Exception {
        for (int i = 0; i < listaExemplares.size(); i++) {
            exemplar exemp = (exemplar) listaExemplares.elementAt(i);
            if (numero == exemp.getNumero()) {
                exemp.setStatus("Indisponível");
                serializaExemplar();
            }

        }
    }

    public void desetaStatus(int numero) throws Exception {
        ctrRegistro registro = new ctrRegistro();
        for (int i = 0; i < listaExemplares.size(); i++) {
            exemplar exemp = (exemplar) listaExemplares.elementAt(i);
            if (numero == exemp.getNumero()) {
                registro.removeRegistro(i);
                exemp.setStatus("Disponível");
                serializaExemplar();
            }

        }
    }

    public boolean checkStatus(int numero) {
        for (int i = 0; i < listaExemplares.size(); i++) {
            exemplar exemp = (exemplar) listaExemplares.elementAt(i);
            if (exemp.getNumero() == numero) {
                if (exemp.getStatus().equals("Indisponível")) {
                    return true;
                }
            }
        }
        return false;
    }
}

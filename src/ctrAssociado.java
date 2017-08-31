
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class ctrAssociado {

    private Vector listaAssociados = new Vector();
    private final String arquivo = "discAss.dat";

    public ctrAssociado() throws Exception {
        desserializaAssociado();
    }

    //Método de inserção 
    public void insereAssociado(int pCodigo, String pNome, String pEndereco, String pEmail, String pStatus) throws Exception {
        associado ass = new associado(pCodigo, pNome, pEndereco, pEmail, pStatus);
        listaAssociados.add(ass);
        serializaAssociado();
    }

    public void serializaAssociado() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaAssociados);
        objOS.flush();
        objOS.close();
    }

    public void desserializaAssociado() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaAssociados = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    //Método de impressão da lista de exemplares
    public String imprimeAssociados() {
        String result = "";
        associado objAssociado = null;
        for (int intIdx = 0; intIdx < listaAssociados.size(); intIdx++) {
            objAssociado = (associado) listaAssociados.elementAt(intIdx);
            result += imprimeAssociado(objAssociado);
        }
        if (result.equalsIgnoreCase("")) {
            return "Não existem exemplares cadastrados.";
        } else {
            return result;
        }
    }

    //Método utilizado para imprimir um determinado exemplar
    public String imprimeAssociado(associado ass) {
        return "Nome :" + ass.getNome()
                + " -  Endereço : " + ass.getEndereco()
                + " -  E-mail : " + ass.getEmail()
                + " -  Status : " + ass.getStatus()
                + "\n";
    }

    public String verificaStatus(int codigo) {
        associado ass;
        for (int i = 0; i < listaAssociados.size(); i++) {
            ass = (associado) listaAssociados.elementAt(i);
            if (ass.getCodigo() == codigo) {
                return ass.getStatus();
            }
        }
        return "Código de Associado não encontrado.";
    }

    public int converteStatus(int codigoAssociado) {
        associado ass;
        int limiteEntrega;
        for (int i = 0; i < listaAssociados.size(); i++) {
            ass = (associado) listaAssociados.elementAt(i);
            if (ass.getStatus().equals("Graduação")) {
                return limiteEntrega = 7;
            }
            if (ass.getStatus().equals("Pós Graduação")) {
                return limiteEntrega = 10;
            }
        }
        return limiteEntrega = 14;
    }

    public boolean verificaCodigo(int codigoAss) {
        if (listaAssociados.isEmpty() == true) {
            return false;
        } else {
            for (int i = 0; i < listaAssociados.size(); i++) {
                associado ass = (associado) listaAssociados.elementAt(i);
                if (ass.getCodigo() == codigoAss) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public String verificaNome(int codigoAss) {
        if (listaAssociados.isEmpty() == true) {
            return "";
        } else {
            for (int i = 0; i < listaAssociados.size(); i++) {
                associado ass = (associado) listaAssociados.elementAt(i);
                if (ass.getCodigo() == codigoAss) {
                    return ass.getNome();
                } else {
                    return "";
                }
            }
        }
        return "";
    }
}

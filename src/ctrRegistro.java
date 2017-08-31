
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class ctrRegistro {

    private final String arquivo = "discReg.dat";
    private final String arquivoDois = "discRegAtr.dat";
    private Vector listaRegistros = new Vector();
    private Vector listaRegistrosAtrasados = new Vector();

    public ctrRegistro() throws Exception {
        desserializaRegistro();
        desserializaRegistro2();
    }

    //Método de inserção 
    public void insereRegistro(int pNumeroExemp, int pISBNExemp, int pCodAss, Date pData, Date pDataLimite) throws Exception {
        registro reg = new registro(pNumeroExemp, pISBNExemp, pCodAss, pData, pDataLimite);
        listaRegistros.add(reg);
        serializaRegistro();
    }

    public void removeRegistro(int numero) throws Exception {
        for (int i = 0; i < listaRegistros.size(); i++) {
            registro reg = (registro) listaRegistros.elementAt(i);
            if (reg.getNumeroExemp() == numero) {
                listaRegistros.removeElementAt(i);
            }
        }
        for (int i = 0; i < listaRegistrosAtrasados.size(); i++) {
            registro reg = (registro) listaRegistrosAtrasados.elementAt(i);
            if (reg.getNumeroExemp() == numero) {
                listaRegistrosAtrasados.removeElementAt(i);
            }
        }

        serializaRegistro();
        serializaRegistro2();
    }

    public Vector getListaRegistros() {
        return listaRegistros;
    }

    //Método de impressão da lista de registros
    public String imprimeRegistrosAtrasados() throws Exception {
        String result = "";
        registro objRegistro = null;
        for (int intIdx = 0; intIdx < listaRegistrosAtrasados.size(); intIdx++) {
            objRegistro = (registro) listaRegistrosAtrasados.elementAt(intIdx);
            result += imprimeRegistro(objRegistro);
        }
        return result;
    }

    //Método utilizado para imprimir um determinado registro
    public String imprimeRegistro(registro reg) throws Exception {
        ctrAssociado ass = new ctrAssociado();

        return " Código do Associado : " + reg.getCodAss()
                + " -  Número do Exemplar : " + reg.getNumeroExemp()
                + "\n";
    }

    public void serializaRegistro() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaRegistros);
        objOS.flush();
        objOS.close();
    }

    public void desserializaRegistro() throws Exception {
        File objFile = new File(arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            try (ObjectInputStream objIS = new ObjectInputStream(objFileIS)) {
                listaRegistros = (Vector) objIS.readObject();
            }
        }
    }

    public void serializaRegistro2() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(arquivoDois);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(listaRegistrosAtrasados);
        objOS.flush();
        objOS.close();
    }

    public void desserializaRegistro2() throws Exception {
        File objFile = new File(arquivoDois);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivoDois);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            listaRegistrosAtrasados = (Vector) objIS.readObject();
            objIS.close();
        }
    }

    public Date transformaData(String data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatador.parse(data);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Date getDataExemplar(int numero) {
        registro registro = null;
        Date data = null;
        for (int i = 0; i < listaRegistros.size(); i++) {
            registro = (registro) listaRegistros.elementAt(i);
            if (registro.getNumeroExemp() == numero) {
                return registro.getData();
            }
        }
        return data;
    }

    public void verificaAtraso() throws Exception {
        registro reg;
        for (int i = 0; i < listaRegistros.size(); i++) {
            reg = (registro) listaRegistros.elementAt(i);
            String verifica = verificaRegistro(reg);
            Calendar calendarLimite = Calendar.getInstance();
            Date dataAtual = new Date(System.currentTimeMillis());
            Calendar calendarioAtual = Calendar.getInstance();
            calendarioAtual.setTime(dataAtual);
            calendarLimite.setTime(reg.getDataLimite());
            if (calendarioAtual.getTimeInMillis() > calendarLimite.getTimeInMillis() && verifica.equals("")) {
                listaRegistrosAtrasados.add(reg);
            }
        }
        serializaRegistro2();
    }

    public String verificaRegistro(registro reg) {
        for (int i = 0; i < listaRegistrosAtrasados.size(); i++) {
            registro reg2 = (registro) listaRegistrosAtrasados.elementAt(i);
            if (reg.getNumeroExemp() == reg2.getNumeroExemp()) {
                return "possui";
            }
        }
        return "";
    }
}

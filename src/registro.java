
import java.util.Date;
import java.io.Serializable;

public class registro implements Serializable {

    int numeroExemp;
    int ISBNExemp;
    int codAss;
    Date data;
    Date dataLimite;

    public registro(int numeroExemp, int ISBNExemp, int codAss, Date data, Date dataLimite) {
        this.numeroExemp = numeroExemp;
        this.ISBNExemp = ISBNExemp;
        this.codAss = codAss;
        this.data = data;
        this.dataLimite = dataLimite;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNumeroExemp() {
        return numeroExemp;
    }

    public void setNumeroExemp(int numeroExemp) {
        this.numeroExemp = numeroExemp;
    }

    public int getISBNExemp() {
        return ISBNExemp;
    }

    public void setISBNExemp(int ISBNExemp) {
        this.ISBNExemp = ISBNExemp;
    }

    public int getCodAss() {
        return codAss;
    }

    public void setCodAss(int codAss) {
        this.codAss = codAss;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

}

package ifes.domain;

public class Periodo {

    private int cdPeriodo;

    private String nome;

    private int preferencia;

    public int getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(int preferencia) {
        this.preferencia = preferencia;
    }

    public int getCdPeriodo() {
        return cdPeriodo;
    }

    public void setCdPeriodo(int cdPeriodo) {
        this.cdPeriodo = cdPeriodo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return this.getNome();
    }
}

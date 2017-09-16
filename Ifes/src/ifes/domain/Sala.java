package ifes.domain;

public class Sala {

    private int cdSala;

    private String nome;

    private int qnt;

    public Sala() {

    }

    public int getCdSala() {
        return cdSala;
    }

    /**
     * @param cdSala the cdSala to set
     */
    public void setCdSala(int cdSala) {
        this.cdSala = cdSala;
    }

    /**
     * @return the localizacao
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the qnt
     */
    public int getQnt() {
        return qnt;
    }

    /**
     * @param qnt the qnt to set
     */
    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public String toString() {
        return this.getNome();
    }
}

package ifes.domain;

public class Disciplina {

    private int cdDisciplina;

    private String nome;

    private Periodo periodo;

    private int cargaHoraria;

    /**
     * @return the cdDisciplina
     */
    public int getCdDisciplina() {
        return cdDisciplina;
    }

    /**
     * @param cdDisciplina the cdDisciplina to set
     */
    public void setCdDisciplina(int cdDisciplina) {
        this.cdDisciplina = cdDisciplina;
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

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String toString() {
        return this.getNome();
    }
}

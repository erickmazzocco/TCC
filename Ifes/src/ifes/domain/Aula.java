package ifes.domain;

public class Aula {

    private int cdAula;
    private Turma turmaA;
    private Turma turmaB;

    /**
     * @return the cdAula
     */
    public int getCdAula() {
        return cdAula;
    }

    /**
     * @param cdAula the cdAula to set
     */
    public void setCdAula(int cdAula) {
        this.cdAula = cdAula;
    }

    public Turma getTurmaA() {
        return turmaA;
    }

    public void setTurmaA(Turma turmaA) {
        this.turmaA = turmaA;
    }

    public Turma getTurmaB() {
        return turmaB;
    }

    public void setTurmaB(Turma turmaB) {
        this.turmaB = turmaB;
    }

    public int toInt() {
        return this.getCdAula();
    }
}

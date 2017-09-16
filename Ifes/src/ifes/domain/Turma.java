package ifes.domain;

public class Turma {

    private int cdTurma;

    private Disciplina disciplina;

    private Professor professor;

    private Sala sala;

    private int numeroAulas;

    private int tipo;

    public int getCdTurma() {
        return cdTurma;
    }

    public void setCdTurma(int cdTurma) {
        this.cdTurma = cdTurma;
    }

    public int getNumeroAulas() {
        return numeroAulas;
    }

    public void setNumeroAulas(int numeroAulas) {
        this.numeroAulas = numeroAulas;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    
    
}

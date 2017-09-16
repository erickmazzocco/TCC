
package ifes.domain;

public class Indisponibilidade {
    
    private int cdIndisponibilidade;
    
    private Professor professor;
    
    private int dia;
    
    private int horario;

    public int getCdIndisponibilidade() {
        return cdIndisponibilidade;
    }

    public void setCdIndisponibilidade(int cdIndisponibilidade) {
        this.cdIndisponibilidade = cdIndisponibilidade;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
   
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }
    
}

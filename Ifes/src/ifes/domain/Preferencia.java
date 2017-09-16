/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.domain;

/**
 *
 * @author erickmazzocco
 */
public class Preferencia {

    private int cdPreferencia;

    private Professor professor;

    private int dia;

    private int horario;
    
    private int tipo;

    public int getCdPreferencia() {
        return cdPreferencia;
    }

    public void setCdPreferencia(int cdPreferencia) {
        this.cdPreferencia = cdPreferencia;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}

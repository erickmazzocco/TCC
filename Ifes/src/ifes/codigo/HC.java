/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.codigo;

import ifes.domain.Aula;
import ifes.domain.Professor;
import ifes.domain.Sala;
import ifes.domain.Turma;

/**
 *
 * @author erickmazzocco
 */
public class HC {

    public boolean validarInsercao(Aula[][][] quadro, Aula aula, int dia, int horario, int periodo,
            boolean[][][] quadroIndisponibilidade, int[] diaHora, int quantidadePeriodos,
            int numeroAulas, boolean existeTurmaB) {

        if (!(hardConstraintInsercaoDeHorarioManhaTarde(horario, numeroAulas)
                || hardConstraintPeriodoSobrecarga(quadro, periodo, dia, horario, numeroAulas)
                || hardConstraintProfessorSobrecarga(quadro, dia, horario, aula, quantidadePeriodos, existeTurmaB)
                || hardConstraintSalaSobrecarga(quadro, dia, horario, aula, quantidadePeriodos, existeTurmaB)
                || hardConstraintTurmaSobrecargaDia(quadro, periodo, dia, aula, existeTurmaB)
                || hardConstraintMaximoAulasNoDia(quadro, periodo, dia, numeroAulas)
                || hardConstraintIndisponibilidadeProfessor(quadroIndisponibilidade, dia, horario, aula, existeTurmaB))) {

            diaHora[0] = dia;
            diaHora[1] = horario;
            return true;
        }
        return false;
    }

    private boolean hardConstraintTurmaSobrecargaDia(Aula[][][] quadro, int periodo, int dia, Aula aula, boolean existeTurmaB) {

        Turma turma = aula.getTurmaA();
        Turma turmaAux = null;

        if (existeTurmaB) {
            turmaAux = aula.getTurmaB();
        }

        for (int horario = 0; horario < 12; horario++) {
            if (quadro[periodo][dia][horario].getTurmaA() != null) {
                if (quadro[periodo][dia][horario].getTurmaA().getCdTurma()
                        == turma.getCdTurma()) {
                    return true;
                }
                if (existeTurmaB) {
                    if (quadro[periodo][dia][horario].getTurmaA().getCdTurma()
                            == turmaAux.getCdTurma()) {
                        return true;
                    }
                }
                if (quadro[periodo][dia][horario].getTurmaB() != null) {
                    if (quadro[periodo][dia][horario].getTurmaB().getCdTurma()
                            == turma.getCdTurma()) {
                        return true;
                    }
                    if (existeTurmaB) {
                        if (quadro[periodo][dia][horario].getTurmaB().getCdTurma()
                                == turmaAux.getCdTurma()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean hardConstraintProfessorSobrecarga(Aula[][][] quadro, int dia, int horario,
            Aula aula, int quantidadePeriodos, boolean existeTurmaB) {

        Professor professor = aula.getTurmaA().getProfessor();
        Professor professorAux = null;

        if (existeTurmaB) {
            professorAux = aula.getTurmaB().getProfessor();
        }

        for (int periodo = 0; periodo < quantidadePeriodos; periodo++) {
            if (quadro[periodo][dia][horario].getTurmaA() != null) {
                if (quadro[periodo][dia][horario].getTurmaA().getProfessor().getCdProfessor()
                        == professor.getCdProfessor()) {
                    return true;
                }
                if (existeTurmaB) {
                    if (quadro[periodo][dia][horario].getTurmaA().getProfessor().getCdProfessor()
                            == professorAux.getCdProfessor()) {
                        return true;
                    }
                }
                if (quadro[periodo][dia][horario].getTurmaB() != null) {
                    if (quadro[periodo][dia][horario].getTurmaB().getProfessor().getCdProfessor()
                            == professor.getCdProfessor()) {
                        return true;
                    }
                    if (existeTurmaB) {
                        if (quadro[periodo][dia][horario].getTurmaB().getProfessor().getCdProfessor()
                                == professorAux.getCdProfessor()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean hardConstraintSalaSobrecarga(Aula[][][] quadro, int dia, int horario,
            Aula aula, int quantidadePeriodos, boolean existeTurmaB) {

        Sala sala = aula.getTurmaA().getSala();
        Sala salaAux = null;

        if (existeTurmaB) {
            salaAux = aula.getTurmaB().getSala();
        }

        for (int periodo = 0; periodo < quantidadePeriodos; periodo++) {
            if (quadro[periodo][dia][horario].getTurmaB() != null) {
                if (quadro[periodo][dia][horario].getTurmaA().getSala().getCdSala()
                        == sala.getCdSala()) {
                    return true;
                }
                if (existeTurmaB) {
                    if (quadro[periodo][dia][horario].getTurmaA().getSala().getCdSala()
                            == salaAux.getCdSala()) {
                        return true;
                    }
                }
                if (quadro[periodo][dia][horario].getTurmaB() != null) {
                    if (quadro[periodo][dia][horario].getTurmaB().getSala().getCdSala()
                            == sala.getCdSala()) {
                        return true;
                    }
                    if (existeTurmaB) {
                        if (quadro[periodo][dia][horario].getTurmaB().getSala().getCdSala()
                                == salaAux.getCdSala()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean hardConstraintPeriodoSobrecarga(Aula[][][] quadro, int periodo, int dia,
            int horario, int numeroAulas) {

        for (int i = 0; i < numeroAulas; i++) {
            if ((quadro[periodo][dia][horario + i].getTurmaA() != null)) {
                return true;
            }
        }
        return false;
    }

    private boolean hardConstraintMaximoAulasNoDia(Aula[][][] quadro, int periodo, int dia, int numeroAulas) {
        int numeroAulasNoDia = 0;

        for (int horario = 0; horario < 12; horario++) {
            if (quadro[periodo][dia][horario].getTurmaA() != null) {
                numeroAulasNoDia++;
            }
        }

        switch (numeroAulas) {
            case 2:
                if (numeroAulasNoDia > 5) {
                    return true;
                }
                break;
            case 3:
                if (numeroAulasNoDia > 4) {
                    return true;
                }
                break;
            case 6:
                if (numeroAulasNoDia > 0) {
                    return true;
                }
                break;
            default:
                break;
        }

        return false;
    }

    private boolean hardConstraintIndisponibilidadeProfessor(boolean[][][] quadroIndisponibilidade,
            int dia, int horario, Aula aula, boolean existeTurmaB) {

        Professor professor = aula.getTurmaA().getProfessor();
        Professor professorAux = null;

        if (existeTurmaB) {
            professorAux = aula.getTurmaB().getProfessor();
            if (quadroIndisponibilidade[professorAux.getCdProfessor() - 1][dia][horario] == true) {
                return true;
            }
        }

        if (quadroIndisponibilidade[professor.getCdProfessor() - 1][dia][horario] == true) {
            return true;
        }

        return false;
    }

    private boolean hardConstraintInsercaoDeHorarioManhaTarde(int horario, int numeroAulas) {
        return hardConstraintInsericaoDeHorarioManha(horario, numeroAulas)
                && hardConstraintInsericaoDeHorarioTarde(horario, numeroAulas);

    }

    private boolean hardConstraintInsericaoDeHorarioManha(int horario, int numeroAulas) {
        if (horario >= 0 && horario <= 5 && horario < (7 - numeroAulas)) {
            return false;
        }
        return true;
    }

    private boolean hardConstraintInsericaoDeHorarioTarde(int horario, int numeroAulas) {
        if (horario >= 6 && horario <= 11 && horario < (13 - numeroAulas)) {
            return false;
        }
        return true;
    }

}

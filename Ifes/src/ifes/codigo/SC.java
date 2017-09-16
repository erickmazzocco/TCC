/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.codigo;

import ifes.domain.Aula;

/**
 *
 * @author erickmazzocco
 */
public class SC {

    public double calcularFuncaoCusto(Aula[][][] quadro, int[][][] quadroPref, int quantidadePeriodos) {
        double funcaoCusto = 0;

        funcaoCusto += softConstraintAproximarAulasTriplas(quadro, quantidadePeriodos)
                + softConstraintEvitarLacunas(quadro, quantidadePeriodos)
                + softConstraintTurnoPreferencial(quadro, quantidadePeriodos)
                + softConstraintPreferencia(quadro, quadroPref, quantidadePeriodos)
                ;

        return funcaoCusto;
    }

    private double softConstraintPreferencia(Aula[][][] quadro, int[][][] quadroPref,
            int quantidadePeriodos) {
        double funcaoCusto = 0;
        int professorA;
        int professorB;

        for (int i = 0; i < quantidadePeriodos; i++) {
            for (int k = 0; k < 12; k++) {
                for (int j = 0; j < 5; j++) {
                    if (quadro[i][j][k].getTurmaA() != null) {
                        professorA = quadro[i][j][k].getTurmaA().getProfessor().getCdProfessor();
                        if (quadroPref[professorA - 1][j][k] == 0) {
                            funcaoCusto += 10;
                        } else if (quadroPref[professorA - 1][j][k] == 1) {
                            funcaoCusto -= 10;
                        }
                    }
                    if (quadro[i][j][k].getTurmaB() != null) {
                        professorB = quadro[i][j][k].getTurmaB().getProfessor().getCdProfessor();
                        if (quadroPref[professorB - 1][j][k] == 0) {
                            funcaoCusto += 10;
                        } else if (quadroPref[professorB - 1][j][k] == 1) {
                            funcaoCusto -= 10;
                        }
                    }
                }
            }
        }
        return funcaoCusto;
    }

    private double softConstraintTurnoPreferencial(Aula[][][] quadro, int quantidadePeriodos) {
        double funcaoCusto = 0;

        for (int i = 0; i < quantidadePeriodos; i++) {
            for (int j = 0; j < 12; j++) {
                for (int k = 0; k < 5; k++) {
                    if (quadro[i][k][j].getTurmaA() != null) {
                        if (quadro[i][k][j].getTurmaA().getDisciplina().getPeriodo().getPreferencia() == 1
                                && j >= 0 && j <= 5) {
                            funcaoCusto += 20;
                        }
                        if (quadro[i][k][j].getTurmaA().getDisciplina().getPeriodo().getPreferencia() == 2
                                && j >= 6 && j <= 11) {
                            funcaoCusto += 20;
                        }
                    }
                }
            }
        }

        return funcaoCusto;
    }

    private double softConstraintAproximarAulasTriplas(Aula[][][] quadro, int quantidadePeriodos) {
        double funcaoCusto = 0;

        for (int i = 0; i < quantidadePeriodos; i++) {
            for (int j = 0; j < 12; j += 6) {
                for (int k = 0; k < 5; k++) {
                    if (quadro[i][k][j].getTurmaA() != null) {
                        if (quadro[i][k][j].getTurmaA().getNumeroAulas() == 3) {
                            if (quadro[i][k][j + 3].getTurmaA() != null) {
                                if (quadro[i][k][j + 3].getTurmaA().getNumeroAulas() == 3) {
                                    funcaoCusto += 500;
                                }
                            }
                        }
                    }
                }
            }
        }

        return funcaoCusto;
    }

    private double softConstraintEvitarLacunas(Aula[][][] quadro, int quantidadePeriodos) {
        double funcaoCusto = 0;

        for (int i = 0; i < quantidadePeriodos; i++) {
            for (int j = 0; j < 12; j++) {
                for (int k = 0; k < 5; k++) {
                    if (quadro[i][k][j].getTurmaA() != null) {
                        funcaoCusto += 12.5;
                    }
                }
                if (j == 3 || j == 9) {
                    j += 2;
                }
            }
        }

        return funcaoCusto;
    }

}

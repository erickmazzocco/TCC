/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.codigo;

import ifes.domain.Aula;
import ifes.domain.Indisponibilidade;
import ifes.domain.Periodo;
import ifes.domain.Preferencia;
import ifes.domain.Professor;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author erickmazzocco
 */
public class Quadro {

    public static final int DIAS = 5;
    public static final int HORARIOS = 12;

    int quantidadeSolucoesAnalisadas = 0;

    public void gerarQuadroHorario() {
        Acesso a = new Acesso();
        HC h = new HC();
        SC s = new SC();

        List<Aula> listAula = a.carregarAula();
        List<Professor> listProfessor = a.carregarProfessor();
        List<Periodo> listPeriodo = a.carregarPeriodo();
        List<Indisponibilidade> listIndisponibilidade = a.carregarIndisponibilidade();
        List<Preferencia> listPreferencia = a.carregarPreferencia();

        // Quantidade de Períodos, Aulas e Professores
        int qntP = listPeriodo.size();
        int qntA = listAula.size();
        int qntPr = listProfessor.size();

        // Sequencia Atual, Local e Global
        int[] seqA = new int[qntA];
        int[] seqL = new int[qntA];
        int[] seqG = new int[qntA];

        Aula[][][] quadA = new Aula[qntP][DIAS][HORARIOS];
        Aula[][][] quadL = new Aula[qntP][DIAS][HORARIOS];
        Aula[][][] quadG = new Aula[qntP][DIAS][HORARIOS];

        boolean[][][] quadI = new boolean[qntPr][DIAS][HORARIOS];
        int[][][] quadPref = new int[qntPr][DIAS][HORARIOS];

        a.carregarQuadroIndisponibilidade(quadI, listIndisponibilidade);
        a.carregarSequencia(seqA, qntA);
        a.carregarQuadros(quadA, quadL, quadG, qntP);
        a.carregarQuadroPreferencia(quadPref, listPreferencia, qntPr);

        a.copiarSequenciaEQuadros(seqA, seqL, seqG, quadA, quadL, quadG,
                quadI, qntP, qntA, listAula);

        Collections.shuffle(listAula);

        System.out.println("Função de Custo Inicial: " + s.calcularFuncaoCusto(quadA, quadPref, qntP));

        variableNeighborhoodSearch(seqA, seqL, seqG, quadA, quadL, quadG,
                quadI, quadPref, qntA, qntP, listAula, h, s);

        System.out.println("Quantidade de Soluções Analisadas: " + quantidadeSolucoesAnalisadas);
        System.out.println("Função de Custo Global: " + s.calcularFuncaoCusto(quadG, quadPref, qntP));
        exibirHorarioGrafico(quadG, qntP);

    }

    private void variableNeighborhoodSearch(int[] seqA, int[] seqL, int[] seqG,
            Aula[][][] quadA, Aula[][][] quadL, Aula[][][] quadG, boolean[][][] quadI,
            int[][][] quadPref, int qntA, int qntP, List<Aula> listAula, HC h, SC s) {

        int quantidadeEstruturas = 3;
        int quantidadeMaximaIteracoesSemMelhora = 10;
        int iteracoesSemMelhora = 0;
        int estruturaDeVizinhanca;

        while (iteracoesSemMelhora < quantidadeMaximaIteracoesSemMelhora) {
            Collections.shuffle(listAula);

            estruturaDeVizinhanca = 1;

            while (estruturaDeVizinhanca <= quantidadeEstruturas) {
                variableNeighborhoodDescent(seqA, seqL, quadA, quadL, quadI, quadPref,
                        qntA, qntP, listAula, h, estruturaDeVizinhanca, s);

                if (s.calcularFuncaoCusto(quadL, quadPref, qntP) > s.calcularFuncaoCusto(quadG, quadPref, qntP)) {
                    System.arraycopy(seqL, 0, seqG, 0, qntA);
                    System.arraycopy(seqL, 0, seqA, 0, qntA);
                    preencherQuadroMetodoGuloso(listAula, seqG, qntA, quadG, quadI, qntP, h);

                    estruturaDeVizinhanca = 1;
                    iteracoesSemMelhora = 0;
                }                
                estruturaDeVizinhanca++;
            }

            iteracoesSemMelhora++;
        }
    }

    private void variableNeighborhoodDescent(int[] seqA, int[] seqL,
            Aula[][][] quadA, Aula[][][] quadL, boolean[][][] quadI, int[][][] quadPref,
            int qntA, int qntP, List<Aula> listAula, HC h, int estrutura, SC s) {

        boolean houveMelhoria;

        preencherQuadroMetodoGuloso(listAula, seqA, qntA, quadA, quadI, qntP, h);

        do {
            houveMelhoria = executarBuscaLocal(seqA, seqL, quadA, quadL, quadI, quadPref,
                    qntA, qntP, listAula, h, estrutura, s);

            if (houveMelhoria) {
                preencherQuadroMetodoGuloso(listAula, seqL, qntA, quadL, quadI, qntP, h);
                System.arraycopy(seqL, 0, seqA, 0, qntA);
            }

        } while (houveMelhoria);

    }

    private boolean executarBuscaLocal(int[] seqA, int[] seqL,
            Aula[][][] quadA, Aula[][][] quadL, boolean[][][] quadI, int[][][] quadPref,
            int qntA, int qntP, List<Aula> listAula, HC h, int estrutura, SC s) {

        switch (estrutura) {
            case 1:
                return estruturaDeVizinhanca1Opt(seqA, seqL, quadA, quadL,
                        quadI, quadPref, qntA, qntP, listAula, h, s);
            case 2:
                return estruturaDeVizinhanca2Opt(seqA, seqL, quadA, quadL, quadI, quadPref,
                        qntA, qntP, listAula, h, s);
            case 3:
                return estruturaDeVizinhanca3Opt(seqA, seqL, quadA, quadL, quadI, quadPref,
                        qntA, qntP, listAula, h, s);
            case 4:
                return true;
        }

        return false;

    }

    private boolean estruturaDeVizinhanca1Opt(int[] seqA, int[] seqL,
            Aula[][][] quadA, Aula[][][] quadL, boolean[][][] quadI, int[][][] quadPref,
            int qntA, int qntP, List<Aula> listAula, HC h, SC s) {

        double funcaoCustoLocal = s.calcularFuncaoCusto(quadL, quadPref, qntP);
        boolean houveMelhoria = false;
        double funcaoCustoVizinha;
        int aux;

        loopExterno:
        for (int i = 0; i < qntA; i++) {
            for (int j = i + 1; j < qntA; j++) {
                quantidadeSolucoesAnalisadas++;
                aux = seqA[i];
                for (int x = i; x < j; x++) {
                    seqA[x] = seqA[x + 1];
                }
                seqA[j] = aux;

                preencherQuadroMetodoGuloso(listAula, seqA, qntA, quadA, quadI, qntP, h);
                funcaoCustoVizinha = s.calcularFuncaoCusto(quadA, quadPref, qntP);
                if (funcaoCustoVizinha > funcaoCustoLocal) {
                    System.arraycopy(seqA, 0, seqL, 0, qntA);
                    houveMelhoria = true;
                    break loopExterno;
                } else {
                    // Reverte a troca de i por j
                    aux = seqA[j];
                    for (int x = j; x > i; x--) {
                        seqA[x] = seqA[x - 1];
                    }
                    seqA[i] = aux;
                }
            }
        }

        return houveMelhoria;

    }

    private boolean estruturaDeVizinhanca2Opt(int[] seqA, int[] seqL,
            Aula[][][] quadA, Aula[][][] quadL, boolean[][][] quadI, int[][][] quadPref,
            int qntA, int qntP, List<Aula> listAula, HC h, SC s) {

        double funcaoCustoLocal = s.calcularFuncaoCusto(quadL, quadPref, qntP);
        boolean houveMelhoria = false;
        double funcaoCustoVizinha;
        int aux;

        loopExterno:
        for (int i = 0; i < qntA; i++) {
            for (int j = i + 1; j < qntA; j++) {
                quantidadeSolucoesAnalisadas++;

                // Troca i por j
                aux = seqA[j];
                seqA[j] = seqA[i];
                seqA[i] = aux;

                preencherQuadroMetodoGuloso(listAula, seqA, qntA, quadA, quadI, qntP, h);
                funcaoCustoVizinha = s.calcularFuncaoCusto(quadA, quadPref, qntP);
                if (funcaoCustoVizinha > funcaoCustoLocal) {
                    System.arraycopy(seqA, 0, seqL, 0, qntA);
                    houveMelhoria = true;
                    break loopExterno;
                } else {
                    // Reverte a troca de i por j
                    aux = seqA[j];
                    seqA[j] = seqA[i];
                    seqA[i] = aux;
                }
            }
        }

        return houveMelhoria;
    }

    private boolean estruturaDeVizinhanca3Opt(int[] seqA, int[] seqL,
            Aula[][][] quadA, Aula[][][] quadL, boolean[][][] quadI, int[][][] quadPref,
            int qntA, int qntP, List<Aula> listAula, HC h, SC s) {

        double funcaoCustoLocal = s.calcularFuncaoCusto(quadL, quadPref, qntP);
        boolean houveMelhoria = false;
        double funcaoCustoVizinha;
        int aux;

        loopExterno:
        for (int i = 0; i < qntA; i++) {
            for (int j = i + 1; j < qntA; j++) {
                for (int k = i + 2; k < qntA; k++) {
                    quantidadeSolucoesAnalisadas++;

                    // Troca i,j,k
                    aux = seqA[k];
                    seqA[k] = seqA[j];
                    seqA[j] = seqA[i];
                    seqA[i] = aux;

                    preencherQuadroMetodoGuloso(listAula, seqA, qntA, quadA, quadI, qntP, h);
                    funcaoCustoVizinha = s.calcularFuncaoCusto(quadA, quadPref, qntP);
                    if (funcaoCustoVizinha > funcaoCustoLocal) {
                        System.arraycopy(seqA, 0, seqL, 0, qntA);
                        houveMelhoria = true;
                        break loopExterno;
                    } else {
                        // Reverte i,j,k
                        aux = seqA[i];
                        seqA[i] = seqA[j];
                        seqA[j] = seqA[k];
                        seqA[k] = aux;
                    }
                }
            }
        }

        return houveMelhoria;
    }

    private boolean estruturaDeVizinhanca4Opt(int[] seqA, int[] seqL,
            Aula[][][] quadA, Aula[][][] quadL, boolean[][][] quadI, int[][][] quadPref,
            int qntA, int qntP, List<Aula> listAula, HC h, SC s) {
        return true;
    }

    public void preencherQuadroMetodoGuloso(List<Aula> listAula, int[] seqA, int qntA,
            Aula[][][] quadA, boolean[][][] quadI, int qntP,
            HC h) {

        Aula aula;
        int periodo;
        int numeroAulas;
        int preferencia;
        int[] diaHora = new int[2];

        esvaziarQuadro(quadA, qntP);

        loop:
        for (int i = 0; i < qntA; i++) {
            aula = listAula.get(seqA[i]);
            periodo = aula.getTurmaA().getDisciplina().getPeriodo().getCdPeriodo() - 1;
            numeroAulas = aula.getTurmaA().getNumeroAulas();
            preferencia = aula.getTurmaA().getDisciplina().getPeriodo().getPreferencia();
            boolean existeTurmaB = false;

            if (aula.getTurmaB() != null) {
                existeTurmaB = true;
            }

            if (proximoHorarioDisponivel(aula, quadA, quadI, periodo, numeroAulas,
                    diaHora, qntP, preferencia, h, existeTurmaB)) {

                if (!existeTurmaB) {
                    for (int j = 0; j < numeroAulas; j++) {
                        quadA[periodo][diaHora[0]][diaHora[1] + j].setTurmaA(aula.getTurmaA());
                    }
                } else {
                    for (int j = 0; j < numeroAulas; j++) {
                        quadA[periodo][diaHora[0]][diaHora[1] + j].setTurmaA(aula.getTurmaA());
                        if (j < aula.getTurmaB().getNumeroAulas()) {
                            quadA[periodo][diaHora[0]][diaHora[1] + j].setTurmaB(aula.getTurmaB());
                        }
                    }
                }
            } else {
                esvaziarQuadro(quadA, qntP);
                break loop;
            }
        }
    }

    private boolean proximoHorarioDisponivel(Aula aula, Aula[][][] quadA, boolean[][][] quadI,
            int periodo, int numeroAulas, int[] diaHora, int qntP, int preferencia,
            HC h, boolean existeTurmaB) {

        int limiteComeco = -1, limiteFim = -1;

        switch (preferencia) {
            case 0:
                // Horário sem preferencia.
                limiteComeco = 0;
                limiteFim = 12;
                if (!verificarProximoHorarioValido(aula, quadA, quadI, periodo,
                        numeroAulas, diaHora, qntP, preferencia, h, existeTurmaB, limiteComeco, limiteFim)) {
                    return false;
                }
                break;
            case 1:
                // Horário com preferencia pela manha.
                limiteComeco = 0;
                limiteFim = 6;
                if (!verificarProximoHorarioValido(aula, quadA, quadI, periodo,
                        numeroAulas, diaHora, qntP, preferencia, h, existeTurmaB, limiteComeco, limiteFim)) {
                    limiteComeco = 6;
                    limiteFim = 12;
                    if (!verificarProximoHorarioValido(aula, quadA, quadI, periodo,
                            numeroAulas, diaHora, qntP, preferencia, h, existeTurmaB, limiteComeco, limiteFim)) {
                        return false;
                    }
                }
                break;
            case 2:
                // Horário com preferencia pela tarde.
                limiteComeco = 6;
                limiteFim = 12;
                if (!verificarProximoHorarioValido(aula, quadA, quadI, periodo,
                        numeroAulas, diaHora, qntP, preferencia, h, existeTurmaB, limiteComeco, limiteFim)) {
                    limiteComeco = 0;
                    limiteFim = 6;
                    if (!verificarProximoHorarioValido(aula, quadA, quadI, periodo,
                            numeroAulas, diaHora, qntP, preferencia, h, existeTurmaB, limiteComeco, limiteFim)) {
                        return false;
                    }
                }
                break;
            case 3:
                // Horário obrigatóriamente pela manha.
                limiteComeco = 0;
                limiteFim = 6;
                if (!verificarProximoHorarioValido(aula, quadA, quadI, periodo,
                        numeroAulas, diaHora, qntP, preferencia, h, existeTurmaB, limiteComeco, limiteFim)) {
                    return false;
                }
                break;
            case 4:
                // Horário obrigatóriamente pela tarde.
                limiteComeco = 6;
                limiteFim = 12;
                if (!verificarProximoHorarioValido(aula, quadA, quadI, periodo,
                        numeroAulas, diaHora, qntP, preferencia, h, existeTurmaB, limiteComeco, limiteFim)) {
                    return false;
                }
                break;
        }

        return true;
    }

    private boolean verificarProximoHorarioValido(Aula aula, Aula[][][] quadA, boolean[][][] quadI,
            int periodo, int numeroAulas, int[] diaHora, int qntP, int preferencia,
            HC h, boolean existeTurmaB, int limiteComeco, int limiteFim) {

        for (int horario = limiteComeco; horario < limiteFim; horario++) {
            for (int dia = 0; dia < 5; dia++) {
                if (h.validarInsercao(quadA, aula, dia, horario, periodo, quadI,
                        diaHora, qntP, numeroAulas, existeTurmaB)) {
                    diaHora[0] = dia;
                    diaHora[1] = horario;
                    return true;
                }

            }
        }
        return false;
    }

    private void exibirHorarioGrafico(Aula[][][] quadA, int qntP) {
        String espacamento = "                              ";
        String slot;
        String nomeDisciplina = "";
        String slotP1;
        String slotP2;
        String turma = "";

        int tamanhoNomeDisciplina;
        int tamanhoSlot;

        for (int k = 0; k < qntP; k++) {

            System.out.println("Horário do Período: " + k);
            System.out.println("                 SEGUNDA          ||            TERÇA            ||           QUARTA            ||          QUINTA             ||         SEXTA                ");
            System.out.println("     ------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < 12; i++) {
                if (i == 6) {
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
                if (i < 10) {
                    System.out.print(i + "  - ");
                } else {
                    System.out.print(i + " - ");
                }

                for (int j = 0; j < 5; j++) {

                    if (quadA[k][j][i].getTurmaA() == null) {
                        slot = espacamento;
                        nomeDisciplina = "Vazio";

                    } else {
                        slot = espacamento;
                        if (quadA[k][j][i].getTurmaA().getTipo() == 0) {
                            turma = "";
                        } else if (quadA[k][j][i].getTurmaA().getTipo() == 1) {
                            turma = " A";
                        } else {
                            turma = " B";
                        }
                        nomeDisciplina = quadA[k][j][i].getTurmaA().getDisciplina().getNome() + turma;
                        if (quadA[k][j][i].getTurmaB() != null) {
                            if (quadA[k][j][i].getTurmaB().getTipo() == 0) {
                                turma = "";
                            } else if (quadA[k][j][i].getTurmaB().getTipo() == 1) {
                                turma = " A";
                            } else {
                                turma = " B";
                            }
                            nomeDisciplina += " | "
                                    + quadA[k][j][i].getTurmaB().getDisciplina().getNome()
                                    + turma;
                        } else {
                            if (quadA[k][j][i].getTurmaA().getTipo() > 0) {
                                nomeDisciplina += " | Vazio  ";
                            }
                        }
                    }
                    tamanhoNomeDisciplina = nomeDisciplina.length();
                    slot = slot.substring(tamanhoNomeDisciplina);
                    tamanhoSlot = slot.length() / 2;
                    slotP1 = slot.substring(tamanhoSlot);
                    slotP2 = slot.substring(0, tamanhoSlot);
                    System.out.print(slotP1 + nomeDisciplina + slotP2);
                }
                System.out.println("");
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");

        }

    }

    private void esvaziarQuadro(Aula[][][] quadA, int qntP) {
        for (int periodo = 0; periodo < qntP; periodo++) {
            for (int dia = 0; dia < 5; dia++) {
                for (int horario = 0; horario < 12; horario++) {
                    quadA[periodo][dia][horario].setTurmaA(null);
                    quadA[periodo][dia][horario].setTurmaB(null);
                }
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.codigo;

import ifes.database.Database;
import ifes.database.DatabaseFactory;
import ifes.domain.Aula;
import ifes.domain.Indisponibilidade;
import ifes.domain.Periodo;
import ifes.domain.Preferencia;
import ifes.domain.Professor;
import ifes.domain.Turma;
import ifes.model.AulaDAO;
import ifes.model.IndisponibilidadeDAO;
import ifes.model.PeriodoDAO;
import ifes.model.PreferenciaDAO;
import ifes.model.ProfessorDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author erickmazzocco
 */
public class Acesso {

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final AulaDAO aulaDAO = new AulaDAO();
    private final IndisponibilidadeDAO indisponibilidadeDAO = new IndisponibilidadeDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final PeriodoDAO periodoDAO = new PeriodoDAO();
    private final PreferenciaDAO preferenciaDAO = new PreferenciaDAO();

    public List<Aula> carregarAula() {
        aulaDAO.setConnection(connection);
        return aulaDAO.listar();
    }
    
    public List<Preferencia> carregarPreferencia() {
        preferenciaDAO.setConnection(connection);
        return preferenciaDAO.listar();
    }

    public List<Professor> carregarProfessor() {
        professorDAO.setConnection(connection);
        return professorDAO.listar();
    }

    public List<Periodo> carregarPeriodo() {
        periodoDAO.setConnection(connection);
        return periodoDAO.listar();
    }

    public List<Indisponibilidade> carregarIndisponibilidade() {
        indisponibilidadeDAO.setConnection(connection);
        return indisponibilidadeDAO.listar();
    }
    
    public void carregarQuadroPreferencia(int[][][] quadroPreferencia,
            List<Preferencia> listPreferencia, int qntPr){
        
        for(int i = 0; i < qntPr; i++){
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 12; k++){
                    quadroPreferencia[i][j][k] = -1;
                }
            }
        }
        
        for(Preferencia p : listPreferencia){
            quadroPreferencia[p.getProfessor().getCdProfessor() - 1][p.getDia()][p.getHorario()] = p.getTipo();
        }
        
    }

    public void carregarQuadroIndisponibilidade(boolean[][][] quadroIndisponibilidade,
            List<Indisponibilidade> listIndisponibilidade) {

        for (Indisponibilidade i : listIndisponibilidade) {
            quadroIndisponibilidade[i.getProfessor().getCdProfessor() - 1][i.getDia()][i.getHorario()] = true;
        }

    }

    public void carregarSequencia(int[] sequencia, int quantidadeAulas) {
        for (int i = 0; i < quantidadeAulas; i++) {
            sequencia[i] = i;
        }
    }

    public void carregarQuadros(Aula[][][] quadro, Aula[][][] quadroLocal,
            Aula[][][] quadroGlobal, int quantidadePeriodos) {

        for (int periodo = 0; periodo < quantidadePeriodos; periodo++) {
            for (int dia = 0; dia < 5; dia++) {
                for (int horario = 0; horario < 12; horario++) {

                    quadro[periodo][dia][horario] = new Aula();
                    quadroLocal[periodo][dia][horario] = new Aula();
                    quadroGlobal[periodo][dia][horario] = new Aula();

                }
            }
        }
    }
    
    public void copiarSequenciaEQuadros(int[] sequencia, int[] sequenciaLocal, int[]sequenciaGlobal,
            Aula[][][] quadro, Aula[][][] quadroLocal, Aula[][][] quadroGlobal, boolean[][][] quadroIndisponibilidade,
            int quantidadePeriodos, int quantidadeAulas, List<Aula> listAula){
        
        HC v = new HC();
        Quadro q = new Quadro();
        
        System.arraycopy(sequencia, 0, sequenciaLocal, 0, quantidadeAulas);
        System.arraycopy(sequencia, 0, sequenciaGlobal, 0, quantidadeAulas);

        q.preencherQuadroMetodoGuloso(listAula, sequencia, quantidadeAulas, quadro,
                quadroIndisponibilidade, quantidadePeriodos, v);
        q.preencherQuadroMetodoGuloso(listAula, sequencia, quantidadeAulas, quadroLocal,
                quadroIndisponibilidade, quantidadePeriodos, v);
        q.preencherQuadroMetodoGuloso(listAula, sequencia, quantidadeAulas, quadroGlobal,
                quadroIndisponibilidade, quantidadePeriodos, v);
        
        
        
    }

}

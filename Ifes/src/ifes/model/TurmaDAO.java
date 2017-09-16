package ifes.model;

import ifes.domain.Disciplina;
import ifes.domain.Professor;
import ifes.domain.Sala;
import ifes.domain.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TurmaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Turma turma) {
        String sql = "INSERT INTO turma(disciplina,professor,sala,numeroAulas, tipo) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, turma.getDisciplina().getCdDisciplina());
            stmt.setInt(2, turma.getProfessor().getCdProfessor());
            stmt.setInt(3, turma.getSala().getCdSala());
            stmt.setInt(4, turma.getNumeroAulas());
            stmt.setInt(5, turma.getTipo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Turma turma) {
        String sql = "DELETE FROM turma WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, turma.getCdTurma());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Turma turma) {
        String sql = "UPDATE turma SET disciplina=?, professor=?, sala=?, numeroAulas=?, tipo=? WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, turma.getDisciplina().getCdDisciplina());
            stmt.setInt(2, turma.getProfessor().getCdProfessor());
            stmt.setInt(3, turma.getSala().getCdSala());
            stmt.setInt(4, turma.getNumeroAulas());
            stmt.setInt(5, turma.getTipo());
            stmt.setInt(8, turma.getCdTurma());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Turma> listar() {
        String sql = "SELECT * FROM turma";
        List<Turma> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Turma turma = new Turma();
                Professor professor = new Professor();
                Disciplina disciplina = new Disciplina();
                Sala sala = new Sala();

                turma.setCdTurma(resultado.getInt("cd"));
                turma.setNumeroAulas(resultado.getInt("numeroAulas"));
                turma.setTipo(resultado.getInt("tipo"));

                ProfessorDAO professorDAO = new ProfessorDAO();
                professorDAO.setConnection(connection);
                professor.setCdProfessor(resultado.getInt("professor"));
                professor = professorDAO.buscar(professor);
                turma.setProfessor(professor);

                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                disciplinaDAO.setConnection(connection);
                disciplina.setCdDisciplina(resultado.getInt("disciplina"));
                disciplina = disciplinaDAO.buscar(disciplina);
                turma.setDisciplina(disciplina);

                SalaDAO salaDAO = new SalaDAO();
                salaDAO.setConnection(connection);
                sala.setCdSala(resultado.getInt("sala"));
                sala = salaDAO.buscar(sala);
                turma.setSala(sala);

                retorno.add(turma);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Turma buscar(Turma turma) {
        String sql = "SELECT * FROM turma WHERE cd=?";
        Turma retorno = new Turma();
        
        if(turma.getCdTurma() == 1){            
            return null;
        }
            
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, turma.getCdTurma());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                Professor professor = new Professor();
                Disciplina disciplina = new Disciplina();
                Sala sala = new Sala();

                turma.setCdTurma(resultado.getInt("cd"));
                turma.setNumeroAulas(resultado.getInt("numeroAulas"));
                turma.setTipo(resultado.getInt("tipo"));

                ProfessorDAO professorDAO = new ProfessorDAO();
                professorDAO.setConnection(connection);
                professor.setCdProfessor(resultado.getInt("professor"));
                professor = professorDAO.buscar(professor);
                turma.setProfessor(professor);

                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                disciplinaDAO.setConnection(connection);
                disciplina.setCdDisciplina(resultado.getInt("disciplina"));
                disciplina = disciplinaDAO.buscar(disciplina);
                turma.setDisciplina(disciplina);

                SalaDAO salaDAO = new SalaDAO();
                salaDAO.setConnection(connection);
                sala.setCdSala(resultado.getInt("sala"));
                sala = salaDAO.buscar(sala);
                turma.setSala(sala);

                retorno = turma;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

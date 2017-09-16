/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.model;

import ifes.domain.Aula;
import ifes.domain.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erickmazzocco
 */
public class AulaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Aula aula) {
        String sql = "INSERT INTO aula(turmaA, turmaB) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aula.getTurmaA().getCdTurma());
            stmt.setInt(2, aula.getTurmaB().getCdTurma());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Aula aula) {
        String sql = "DELETE FROM aula WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aula.getCdAula());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Aula aula) {
        String sql = "UPDATE aula SET turmaA=?, turmaB=? WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aula.getTurmaA().getCdTurma());
            stmt.setInt(2, aula.getTurmaB().getCdTurma());
            stmt.setInt(3, aula.getCdAula());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Aula> listar() {
        String sql = "SELECT * FROM aula";
        List<Aula> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Aula aula = new Aula();
                Turma turmaA = new Turma();
                Turma turmaB = new Turma();

                aula.setCdAula(resultado.getInt("cd"));

                TurmaDAO turmaDAO = new TurmaDAO();
                turmaDAO.setConnection(connection);
                turmaA.setCdTurma(resultado.getInt("turmaA"));
                turmaA = turmaDAO.buscar(turmaA);
                aula.setTurmaA(turmaA);

                turmaB.setCdTurma(resultado.getInt("turmaB"));
                turmaB = turmaDAO.buscar(turmaB);
                aula.setTurmaB(turmaB);

                retorno.add(aula);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AulaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Aula buscar(Aula aula) {
        String sql = "SELECT * FROM aula WHERE cd=?";
        Aula retorno = new Aula();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aula.getCdAula());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                Turma turmaA = new Turma();
                Turma turmaB = new Turma();

                aula.setCdAula(resultado.getInt("cd"));

                TurmaDAO turmaDAO = new TurmaDAO();
                turmaDAO.setConnection(connection);
                turmaA.setCdTurma(resultado.getInt("turmaA"));
                turmaA = turmaDAO.buscar(turmaA);
                aula.setTurmaA(turmaA);
                turmaB.setCdTurma(resultado.getInt("turmaB"));
                turmaB = turmaDAO.buscar(turmaB);
                aula.setTurmaB(turmaB);

                retorno = aula;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AulaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Aula> listarPorTurma(String nome) {
        String sql = "select * "
                + "from aula a "
                + "inner join turma o on a.turma = o.cd "
                + "inner join disciplina d on o.disciplina = d.cd "
                + "inner join curso c on d.curso = c.cd "
                + "where c.nome=?";
        List<Aula> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.execute();
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Aula aula = new Aula();
                Turma turma = new Turma();

                aula.setCdAula(resultado.getInt("cd"));

                TurmaDAO turmaDAO = new TurmaDAO();
                turmaDAO.setConnection(connection);
                turma.setCdTurma(resultado.getInt("turma"));
                turma = turmaDAO.buscar(turma);
                aula.setTurmaA(turma);

                retorno.add(aula);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AulaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public void apagarRegistros() {
        String sql = "TRUNCATE aula";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AulaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

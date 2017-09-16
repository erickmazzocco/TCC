package ifes.model;

import ifes.domain.Disciplina;
import ifes.domain.Periodo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisciplinaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina(nome, cargaHoraria, periodo,) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());            
            stmt.setInt(3, disciplina.getPeriodo().getCdPeriodo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Disciplina disciplina) {
        String sql = "DELETE FROM disciplina WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, disciplina.getCdDisciplina());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Disciplina disciplina) {
        String sql = "UPDATE disciplina SET  nome=?, periodo=?, cargaHoraria=? WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getPeriodo().getCdPeriodo());            
            stmt.setInt(3, disciplina.getCargaHoraria());
            stmt.setInt(4, disciplina.getCdDisciplina());
            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Disciplina> listar() {
        String sql = "SELECT * FROM disciplina";
        List<Disciplina> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Disciplina disciplina = new Disciplina();
                Periodo periodo = new Periodo();
                
                disciplina.setCdDisciplina(resultado.getInt("cd"));
                disciplina.setNome(resultado.getString("nome"));                
                disciplina.setCargaHoraria(resultado.getInt("cargaHoraria"));
                
                PeriodoDAO periodoDAO = new PeriodoDAO();
                periodoDAO.setConnection(connection);                
                periodo.setCdPeriodo((resultado.getInt("periodo")));
                periodo = periodoDAO.buscar(periodo);                
                disciplina.setPeriodo(periodo);

                retorno.add(disciplina);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Disciplina buscar(Disciplina disciplina) {
        String sql = "SELECT * FROM disciplina WHERE cd=?";
        Disciplina retorno = new Disciplina();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, disciplina.getCdDisciplina());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                Periodo periodo = new Periodo();
                
                disciplina.setCdDisciplina(resultado.getInt("cd"));
                disciplina.setNome(resultado.getString("nome"));                
                disciplina.setCargaHoraria(resultado.getInt("cargaHoraria"));
                
                PeriodoDAO periodoDAO = new PeriodoDAO();
                periodoDAO.setConnection(connection);                
                periodo.setCdPeriodo((resultado.getInt("periodo")));
                periodo = periodoDAO.buscar(periodo);                
                disciplina.setPeriodo(periodo);

                retorno = disciplina;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

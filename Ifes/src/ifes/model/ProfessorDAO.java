
package ifes.model;

import ifes.domain.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Professor professor) {
        String sql = "INSERT INTO professor(nome, cidade) VALUES(?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setString(1, professor.getNome());            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Professor professor) {
        String sql = "DELETE FROM professor WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, professor.getCdProfessor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Professor professor) {
        String sql = "UPDATE professor SET nome=? WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setString(1, professor.getNome());            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Professor> listar() {
        String sql = "SELECT * FROM professor";
        List<Professor> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Professor professor = new Professor();
                
                professor.setCdProfessor(resultado.getInt("cd"));
                professor.setNome(resultado.getString("nome"));
                
                retorno.add(professor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Professor buscar(Professor professor) {
        String sql = "SELECT * FROM professor WHERE cd=?";
        Professor retorno = new Professor();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, professor.getCdProfessor());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) { 
                
                professor.setCdProfessor(resultado.getInt("cd"));
                professor.setNome(resultado.getString("nome"));
                
                retorno = professor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

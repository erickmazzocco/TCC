
package ifes.model;

import ifes.domain.Indisponibilidade;
import ifes.domain.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class IndisponibilidadeDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public List<Indisponibilidade> listar() {
        String sql = "SELECT * FROM indisponibilidade";
        List<Indisponibilidade> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Indisponibilidade indisponibilidade = new Indisponibilidade();
                Professor professor = new Professor();
                
                indisponibilidade.setCdIndisponibilidade(resultado.getInt("cd"));
                indisponibilidade.setDia(resultado.getInt("dia"));
                indisponibilidade.setHorario(resultado.getInt("horario"));
                
                ProfessorDAO professorDAO = new ProfessorDAO();
                professorDAO.setConnection(connection);
                professor.setCdProfessor(resultado.getInt("professor"));
                professor = professorDAO.buscar(professor);
                indisponibilidade.setProfessor(professor);

                retorno.add(indisponibilidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IndisponibilidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.model;

import java.sql.Connection;
import java.util.List;
import ifes.domain.Preferencia;
import ifes.domain.Professor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erickmazzocco
 */
public class PreferenciaDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Preferencia> listar() {
        String sql = "SELECT * FROM preferencia";
        List<Preferencia> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Preferencia preferencia = new Preferencia();
                Professor professor = new Professor();
                
                preferencia.setCdPreferencia(resultado.getInt("cd"));
                preferencia.setDia(resultado.getInt("dia"));
                preferencia.setHorario(resultado.getInt("horario"));
                preferencia.setTipo(resultado.getInt("tipo"));

                ProfessorDAO professorDAO = new ProfessorDAO();
                professorDAO.setConnection(connection);
                professor.setCdProfessor(resultado.getInt("professor"));
                professor = professorDAO.buscar(professor);
                preferencia.setProfessor(professor);

                retorno.add(preferencia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PreferenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}


package ifes.model;

import ifes.domain.Sala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalaDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Sala sala) {
        String sql = "INSERT INTO sala(nome) VALUES(?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setString(1, sala.getNome());            
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Sala sala) {
        String sql = "DELETE FROM sala WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sala.getCdSala());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Sala sala) {
        String sql = "UPDATE sala SET nome=? WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setString(1, sala.getNome());            
            stmt.setInt(2, sala.getCdSala());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Sala> listar() {
        String sql = "SELECT * FROM sala";
        List<Sala> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Sala sala = new Sala();
                             
                sala.setCdSala(resultado.getInt("cd"));
                sala.setNome(resultado.getString("nome"));                

                retorno.add(sala);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Sala buscar(Sala sala) {
        String sql = "SELECT * FROM sala WHERE cd=?";
        Sala retorno = new Sala();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, sala.getCdSala());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {                
                
                sala.setCdSala(resultado.getInt("cd"));
                sala.setNome(resultado.getString("nome"));                

                retorno = sala;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

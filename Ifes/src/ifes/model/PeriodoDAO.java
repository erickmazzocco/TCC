
package ifes.model;

import ifes.domain.Periodo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeriodoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Periodo periodo) {
        String sql = "INSERT INTO periodo(nome, preferencia) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setString(1, periodo.getNome());
            stmt.setInt(2, periodo.getPreferencia());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Periodo periodo) {
        String sql = "DELETE FROM periodo WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, periodo.getCdPeriodo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Periodo periodo) {
        String sql = "UPDATE periodo SET nome=?, preferencia=? WHERE cd=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);     
            stmt.setString(1, periodo.getNome());
            stmt.setInt(2, periodo.getPreferencia());
            stmt.setInt(3, periodo.getCdPeriodo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Periodo> listar() {
        String sql = "SELECT * FROM periodo";
        List<Periodo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Periodo periodo = new Periodo();
                             
                periodo.setCdPeriodo(resultado.getInt("cd"));
                periodo.setNome(resultado.getString("nome"));          
                periodo.setPreferencia(resultado.getInt("preferencia"));

                retorno.add(periodo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Periodo buscar(Periodo periodo) {
        String sql = "SELECT * FROM periodo WHERE cd=?";
        Periodo retorno = new Periodo();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, periodo.getCdPeriodo());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {                
                
                periodo.setCdPeriodo(resultado.getInt("cd"));
                periodo.setNome(resultado.getString("nome"));    
                periodo.setPreferencia(resultado.getInt("preferencia"));

                retorno = periodo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}

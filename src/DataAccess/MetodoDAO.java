/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DomainModel.MetodoPags;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iara
 */
public class MetodoDAO {

    private BD bd;

    public MetodoDAO() {
        bd = new BD();
    }

    public boolean Salvar(MetodoPags obj) {
        try{
            if (obj.getCodMetodo() == 0) {
                PreparedStatement comando = bd.getConexao().prepareStatement("insert into metodopags(nome) values(?)");
                comando.setString(1, obj.getNome());
                comando.executeUpdate();
            } else {
                PreparedStatement comando = bd.getConexao().prepareStatement("update metodopags set nome = ? where codmetodo = ?");
                comando.setString(1, obj.getNome());
                comando.setInt(2, obj.getCodMetodo());
                comando.executeUpdate();
            }
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MetodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public MetodoPags Abrir(int codigo) {
        try {
            MetodoPags metodo = new MetodoPags (0, "");

            PreparedStatement comando = bd.getConexao().prepareStatement("select * from metodopags where codmetodo = ?");
            comando.setInt(1, codigo);
            ResultSet resultado = comando.executeQuery();

            resultado.first();
            
            metodo.setCodMetodo(resultado.getInt("codmetodo"));
            metodo.setNome(resultado.getString("nome"));

            return metodo;

        } catch (SQLException ex) {
            Logger.getLogger(MetodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Apagar(MetodoPags obj) {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("delete from metodopags where codmetodo = ?");
            comando.setInt(1, obj.getCodMetodo());
            comando.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MetodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<MetodoPags> listarTodos() {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("select * from metodopags ");
            ResultSet resultado = comando.executeQuery();
            
            List<MetodoPags> metodo = new LinkedList<>();
            while (resultado.next()) {
                MetodoPags tmp = new MetodoPags(0,"");
                
                tmp.setCodMetodo(resultado.getInt("codmetodo"));
                tmp.setNome(resultado.getString("nome"));
                
                metodo.add(tmp);
            }
            return metodo;
        } catch (SQLException ex) {
            Logger.getLogger(MetodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<MetodoPags> buscar(MetodoPags filtro) {
        try {
            
            String sql = "select * from metodopags ";
            String where = "";
            
            if(filtro.getNome().length() > 0){
                where = "nome like '%"+filtro.getNome()+"%'";
            }
                       
            
            if (filtro.getCodMetodo() > 0) {
                if(where.length() > 0) {
                    where = where + " and ";
                }
                where = where + " codmetodo = " + filtro.getCodMetodo();
            }
            
            if(where.length() > 0){
                sql = sql + " where " + where;
            }
            
            Statement comando = bd.getConexao().createStatement();
            
            ResultSet resultado = comando.executeQuery(sql);
            
            List<MetodoPags> metodo = new LinkedList<>();
            while (resultado.next()) {
                
                MetodoPags tmp = new MetodoPags(0,"");
                
                tmp.setCodMetodo(resultado.getInt("codmetodo"));
                tmp.setNome(resultado.getString("nome"));
                
                
                metodo.add(tmp);
            }
            return metodo;
        } catch (SQLException ex) {
            Logger.getLogger(MetodoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DomainModel.Usuarios;
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
 * @author Iara
 */
public class UsuarioDAO {
    private BD bd;
    
    public UsuarioDAO(){
        bd = new BD();
    }
    
    public boolean Salvar(Usuarios obj) {
        try {
            if (obj.getCodUsuario() == 0) {
                PreparedStatement comando = bd.getConexao().prepareStatement("insert into usuarios(nome, rg, cpf, usuario, senha) values(?,?,?,?,?)");
                comando.setString(1, obj.getNome());
                comando.setString(2, obj.getRg());
                comando.setString(3, obj.getCpf());
                comando.setString(4, obj.getUsuario());
                comando.setString(5, obj.getSenha());
                comando.executeUpdate();
            } else {
                PreparedStatement comando = bd.getConexao().prepareStatement("update usuarios set nome = ?, rg = ?, cpf = ?, usuario =?, senha = ? where codusuario = ?");
                comando.setString(1, obj.getNome());
                comando.setString(2, obj.getRg());
                comando.setString(3, obj.getCpf());
                comando.setString(4, obj.getUsuario());
                comando.setString(5, obj.getSenha());
                comando.setInt(6, obj.getCodUsuario());
                comando.executeUpdate();
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Usuarios Abrir(int codigo) throws Exception {
        try {
            Usuarios usu = new Usuarios (0, "", "", "", "", "");

            PreparedStatement comando = bd.getConexao().prepareStatement("select * from usuarios where codusuario = ?");
            comando.setInt(1, codigo);
            ResultSet resultado = comando.executeQuery();

            resultado.first();
            
            usu.setCodUsuario(resultado.getInt("codusuario"));
            usu.setNome(resultado.getString("nome"));
            usu.setRg(resultado.getString("rg"));
            usu.setCpf(resultado.getString("cpf"));
            usu.setUsuario(resultado.getString("usuario"));
            usu.setSenha(resultado.getString("senha"));
                        
            return usu;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Apagar(Usuarios obj) {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("delete from usuarios where codusuario = ?");
            comando.setInt(1, obj.getCodUsuario());
            comando.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Usuarios> listarTodos() {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("select * from usuarios ");
            ResultSet resultado = comando.executeQuery();
            
            List<Usuarios> usu = new LinkedList<>();
            while (resultado.next()) {
                Usuarios tmp = new Usuarios(0,"", "", "", "", "");
                
                tmp.setCodUsuario(resultado.getInt("codusuario"));
                tmp.setNome(resultado.getString("nome"));
                tmp.setRg(resultado.getString("rg"));
                tmp.setCpf(resultado.getString("cpf"));
                tmp.setUsuario(resultado.getString("usuario"));
                tmp.setSenha(resultado.getString("senha"));
                
                
                usu.add(tmp);
            }
            return usu;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Usuarios> buscar(Usuarios filtro) {
        try {
            
            String sql = "select * from usuarios ";
            String where = "";
            
            if(filtro.getNome().length() > 0){
                where = "nome like '%"+filtro.getNome()+"%'";
            }
                       
            
            if (filtro.getCodUsuario() > 0) {
                if(where.length() > 0) {
                    where = where + " and ";
                }
                where = where + " id = " + filtro.getCodUsuario();
            }
            
            if(where.length() > 0){
                sql = sql + " where " + where;
            }
            
            Statement comando = bd.getConexao().createStatement();
            
            ResultSet resultado = comando.executeQuery(sql);
            
            List<Usuarios> metodo = new LinkedList<>();
            while (resultado.next()) {
                
                Usuarios tmp = new Usuarios(0,"","","","","");
                
                tmp.setCodUsuario(resultado.getInt("codusuario"));
                tmp.setNome(resultado.getString("nome"));
                tmp.setRg(resultado.getString("rg"));
                tmp.setCpf(resultado.getString("cpf"));
                tmp.setUsuario(resultado.getString("usuario"));
                tmp.setSenha(resultado.getString("senha"));
                
                
                metodo.add(tmp);
            }
            return metodo;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}

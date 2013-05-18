/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DomainModel.Clientes;
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
public class ClienteDAO {
    private BD bd;
    
    public ClienteDAO(){
        bd = new BD();
    }
    
    public boolean Salvar(Clientes obj) {
        try {
            if (obj.getCodCliente() == 0) {
                PreparedStatement comando = bd.getConexao().prepareStatement("insert into clientes(nome, cpf, rg, telefone, email, endereco) values(?,?,?,?,?,?)");
                comando.setString(1, obj.getNome());
                comando.setString(2, obj.getCpf());
                comando.setString(3, obj.getRg());
                comando.setString(4, obj.getTelefone());
                comando.setString(5, obj.getEmail());
                comando.setString(6, obj.getEndereco());
                comando.executeUpdate();
            } else {
                PreparedStatement comando = bd.getConexao().prepareStatement("update clientes set nome=?, cpf=?, rg=?, telefone=?, email=?, endereco=? where codcliente = ?");
                comando.setString(1, obj.getNome());
                comando.setString(2, obj.getCpf());
                comando.setString(3, obj.getRg());
                comando.setString(4, obj.getTelefone());
                comando.setString(5, obj.getEmail());
                comando.setString(6, obj.getEndereco());
                comando.setString(7, Integer.toString(obj.getCodCliente()));
                comando.executeUpdate();
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Clientes Abrir(int codigo) throws Exception {
        try {
            Clientes cliente = new Clientes ();

            PreparedStatement comando = bd.getConexao().prepareStatement("select * from clientes where codcliente = ?");
            comando.setInt(1, codigo);
            ResultSet resultado = comando.executeQuery();

            resultado.first();
            
            cliente.setCodCliente(resultado.getInt("codcliente"));
            cliente.setNome(resultado.getString("nome"));
            cliente.setCpf(resultado.getString("cpf"));
            cliente.setRg(resultado.getString("rg"));
            cliente.setTelefone(resultado.getString("telefone"));
            cliente.setEmail(resultado.getString("email"));
            cliente.setEndereco(resultado.getString("endereco"));
            
            return cliente;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Apagar(Clientes obj) {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("delete from clientes where codcliente = ?");
            comando.setInt(1, obj.getCodCliente());
            comando.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Clientes> listarTodos() throws Exception {
        try {
            
            PreparedStatement comando = bd.getConexao().prepareStatement("select * from clientes ");
            ResultSet resultado = comando.executeQuery();
            
            List<Clientes> cliente = new LinkedList<>();
            while (resultado.next()) {
                Clientes tmp = new Clientes();
                
                tmp.setCodCliente(resultado.getInt("codcliente"));
                tmp.setNome(resultado.getString("nome"));
                tmp.setCpf(resultado.getString("cpf"));
                tmp.setRg(resultado.getString("rg"));
                tmp.setTelefone(resultado.getString("telefone"));
                tmp.setEmail(resultado.getString("email"));
                tmp.setEndereco(resultado.getString("endereco"));
                
                
                cliente.add(tmp);
            }
            return cliente;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Clientes> buscar(Clientes filtro) throws Exception {
        try {
            
            String sql = "select * from clientes ";
            String where = "";
            
            if(filtro.getNome().length() > 0){
                where = "nome like '%"+filtro.getNome()+"%'";
            }
                       
            
            if (filtro.getCodCliente() > 0) {
                if(where.length() > 0) {
                    where = where + " and ";
                }
                where = where + " codcliente = " + filtro.getCodCliente();
            }
            
            if(where.length() > 0){
                sql = sql + " where " + where;
            }
            
            Statement comando = bd.getConexao().createStatement();
            
            ResultSet resultado = comando.executeQuery(sql);
            
            List<Clientes> metodo = new LinkedList<>();
            while (resultado.next()) {
                
                Clientes tmp = new Clientes();
                
                tmp.setCodCliente(resultado.getInt("codcliente"));
                tmp.setNome(resultado.getString("nome"));
                tmp.setCpf(resultado.getString("cpf"));
                tmp.setRg(resultado.getString("rg"));
                tmp.setEndereco(resultado.getString("endereco"));
                tmp.setTelefone(resultado.getString("telefone"));
                tmp.setEmail(resultado.getString("email"));
                        

                metodo.add(tmp);
            }
            return metodo;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}

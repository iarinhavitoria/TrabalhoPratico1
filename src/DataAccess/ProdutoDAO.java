/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DomainModel.Produto;
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
public class ProdutoDAO {

    private BD bd;

    public ProdutoDAO() {
        bd = new BD();
    }

    public boolean Salvar(Produto obj) {
        try {
            if (obj.getCodProduto() == 0) {
                PreparedStatement comando = bd.getConexao().prepareStatement("insert into produtos(descricao,valorcompra, valorvenda, estoque) values(?,?,?,?)");
                comando.setString(1, obj.getNome());
                comando.setDouble(2, obj.getValorcompra());
                comando.setDouble(3, obj.getValorvenda());
                comando.setInt(4, obj.getEstoque());
                comando.executeUpdate();
            } else {
                PreparedStatement comando = bd.getConexao().prepareStatement("update produtos set descricao = ?,valorcompra = ?, valorvenda = ?, estoque = ? where codproduto = ?");
                comando.setString(1, obj.getNome());
                comando.setDouble(2, obj.getValorcompra());
                comando.setDouble(3, obj.getValorvenda());
                comando.setInt(4, obj.getEstoque());
                comando.setInt(5, obj.getCodProduto());
                comando.executeUpdate();
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Produto Abrir(int id) {
        try {
            Produto produto = new Produto(0, "", 0, 0, 0);

            PreparedStatement comando = bd.getConexao().prepareStatement("select * from produtos where codproduto = ?");
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();

            resultado.first();

            produto.setCodProduto(resultado.getInt("codproduto"));
            produto.setNome(resultado.getString("descricao"));
            produto.setValorcompra(resultado.getDouble("valorcompra"));
            produto.setValorvenda(resultado.getDouble("valorvenda"));
            produto.setEstoque(resultado.getInt("estoque"));

            return produto;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean Apagar(Produto obj) {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("delete from produtos where codproduto = ?");
            comando.setInt(1, obj.getCodProduto());
            comando.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Produto> listarTodos() {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("select * from produtos ");
            ResultSet resultado = comando.executeQuery();

            List<Produto> produtos = new LinkedList<>();
            while (resultado.next()) {
                Produto tmp = new Produto(0, "", 0, 0, 0);

                tmp.setCodProduto(resultado.getInt("codproduto"));
                tmp.setNome(resultado.getString("descricao"));
                tmp.setValorcompra(resultado.getDouble("valorcompra"));
                tmp.setValorvenda(resultado.getDouble("valorvenda"));
                tmp.setEstoque(resultado.getInt("estoque"));

                produtos.add(tmp);
            }
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Produto> buscar(Produto filtro) {
        try {

            String sql = "select * from produtos ";
            String where = "";

            if (filtro.getNome().length() > 0) {
                where = "descricao like '%" + filtro.getNome() + "%'";
            }

            if (filtro.getValorcompra() > 0) {
                if (where.length() > 0) {
                    where = where + " and ";
                }
                where = where + " valorcompra = " + filtro.getValorcompra();
            }
            if (filtro.getValorvenda() > 0) {
                if (where.length() > 0) {
                    where = where + " and ";
                }
                where = where + " valorvenda = " + filtro.getValorvenda();
            }
            if (filtro.getCodProduto() > 0) {
                if (where.length() > 0) {
                    where = where + " and ";
                }
                where = where + " codproduto = " + filtro.getCodProduto();
            }

            if (where.length() > 0) {
                sql = sql + " where " + where;
            }

            Statement comando = bd.getConexao().createStatement();

            ResultSet resultado = comando.executeQuery(sql);

            List<Produto> produtos = new LinkedList<>();
            while (resultado.next()) {

                Produto tmp = new Produto(0, "", 0, 0, 0);

                tmp.setCodProduto(resultado.getInt("codproduto"));
                tmp.setNome(resultado.getString("descricao"));
                tmp.setValorcompra(resultado.getDouble("valorcompra"));
                tmp.setValorvenda(resultado.getDouble("valorvenda"));
                tmp.setEstoque(resultado.getInt("estoque"));

                produtos.add(tmp);
            }
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

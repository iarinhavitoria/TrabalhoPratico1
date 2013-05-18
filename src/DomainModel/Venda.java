/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author iara
 */
public class Venda {
    int codvenda;
    Date data;
    double valortotal;
    int codcliente;
    int codusuario;
    int codmetodo;
    int codprodvenda;
    List<ProdutoVenda> produto;
    int cliente;
    
    public Venda() {
        codvenda = 0;
        valortotal = 0;
        data = new Date();
        produto = new LinkedList <>();
        cliente = 0;
    }

    public int getCodvenda() {
        return codvenda;
    }

    public void setCodvenda(int codvenda) {
        this.codvenda = codvenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }
    

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(float valortotal) {
        this.valortotal = valortotal;
        
    }

    public int getCodcliente() {
        return codcliente;
    }

    public void setCodcliente(int codcliente) {
        this.codcliente = codcliente;
    }

    public int getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(int codusuario) {
        this.codusuario = codusuario;
    }

    public int getCodmetodo() {
        return codmetodo;
    }

    public void setCodmetodo(int codmetodo) {
        this.codmetodo = codmetodo;
    }

    public int getCodprodvenda() {
        return codprodvenda;
    }

    public void setCodprodvenda(int codprodvenda) {
        this.codprodvenda = codprodvenda;
    }
    
    

    public List<ProdutoVenda> getProduto() {
        return produto;
    }

    public void setProduto(List<ProdutoVenda> produto) {
        this.produto = produto;
    }
    
    public void add(ProdutoVenda pv){
        if (!produto.contains(pv)){
            produto.add(pv);
            valortotal += pv.getProduto().getValorvenda() * pv.getQuantidade();
        }
    }
    
    public void remove(ProdutoVenda pv){
        if(produto.contains(pv)){
            produto.remove(pv);
            valortotal -= pv.getProduto().getValorvenda() * pv.getQuantidade();
        }
    }

        
 }
   
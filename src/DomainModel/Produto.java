/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainModel;

/**
 *
 * @author iara
 */
public class Produto {
    int codProduto;
    String nome;
    double valorcompra;
    double valorvenda;
    int estoque;
    
    public Produto(int codProd, String nom, double valc, double valv, int est){
        this.codProduto = codProd;
        this.nome = nom;
        this.valorcompra = valc;
        this.valorvenda = valv;
        this.estoque = est;
    }

    public Produto() {
        this.codProduto = 0;
        this.nome = "";
        this.valorcompra = 0;
        this.valorvenda = 0;
        this.estoque = 0;
    }

    

    

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorcompra() {
        return valorcompra;
    }

    public void setValorcompra(double valorcompra) {
        this.valorcompra = valorcompra;
    }

    public double getValorvenda() {
        return valorvenda;
    }

    public void setValorvenda(double valorvenda) {
        this.valorvenda = valorvenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
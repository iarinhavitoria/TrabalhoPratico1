/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainModel;

import java.util.Objects;

/**
 *
 * @author iara
 */
public class ProdutoVenda {
    int codprodvenda;
    Produto produto;
    int quantidade;
    boolean ativo;

    public int getCodprodvenda() {
        return codprodvenda;
    }

    public void setCodprodvenda(int codprodvenda) {
        this.codprodvenda = codprodvenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.codprodvenda;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutoVenda other = (ProdutoVenda) obj;
        if (this.codprodvenda != other.codprodvenda) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (this.quantidade != other.quantidade) {
            return false;
        }
        return true;
    }
@Override
    public String toString() {
        return produto.getNome();
    }
    

    
}

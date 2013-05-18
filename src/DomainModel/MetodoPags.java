/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainModel;

/**
 *
 * @author iara
 */
public class MetodoPags {
    int CodMetodo;
    String nome;
    
    public MetodoPags(int i, String string){
        this.CodMetodo = i;
        this.nome =string;
    }

    public MetodoPags() {
        this.CodMetodo = 0;
        this.nome = "";
    }

    

    public int getCodMetodo() {
        return CodMetodo;
    }

    public void setCodMetodo(int CodMetodo) {
        this.CodMetodo = CodMetodo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}

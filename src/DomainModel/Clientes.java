/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author iara
 */
public class Clientes {
    int codCliente;
    String nome;
    String cpf;
    String rg;
    List <Telefones> telefone;
    List <Enderecos> endereco;
    List <Emails> email;
  
    /**
     *
     */
    public Clientes(){
        codCliente = 0;
        nome = "";
        cpf = "";
        rg = "";
        email = new LinkedList <> ();
        telefone = new LinkedList <> ();
        endereco = new LinkedList <> ();
        
        
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente, int v) {
        if (v>1){
            codCliente = v;
        }
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codCliente;
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final Clientes other = (Clientes) obj;
        if (this.codCliente != other.codCliente) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    public void setNome(String nom) throws Exception {
        if (nom.length()>3 && nom.length()<250){
            nome = nom;
        }
        throw new Exception ("Nao podem haver nomes com menos de 3 letras e mais do que 250 letras");
    }

    @Override
    public String toString() {
        return "Cliente{" + "codigo=" + codCliente + ", nome=" + nome + '}';
    }

    /**
     *
     * @return
     */
    public List<Emails> getEmails() {
        return email;
    }

    public void setEmails(List<Emails> emails) {
        this.email = emails;
    }

    public List<Telefones> getTelefones() {
        return telefone;
    }

    public void setTelefones(List<Telefones> telefones) {
        this.telefone = telefones;
    }

    /**
     *
     * @return
     */
    public List<Enderecos> getEnderecos() {
        return endereco;
    }

    public void setEnderecos(List<Enderecos> endereco) {
        this.endereco = endereco;
    }
    
    public void addEmail(Emails obj){
        if (!email.contains(obj)){
            email.add(obj);
        }
    }
    public void removeEmail(Emails obj){
        if (email.contains(obj)){
            email.remove(obj);
        }
    }
    public void addTelefones(Telefones obj){
        if (!telefone.contains(obj)){
            telefone.add(obj);
        }
    }
    public void removeTelefone(Telefones obj){
        if (telefone.contains(obj)){
            telefone.remove(obj);
        }
    }
    public void addEndereco(Enderecos obj){
        if (!endereco.contains(obj)){
            endereco.add(obj);
        }
    }
    public void removeEndereco(Enderecos obj){
        if (endereco.contains(obj)){
            endereco.remove(obj);
        }
    }
    
}

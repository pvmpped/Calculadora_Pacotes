package entities;

public class Pacote {
    private String nome;
    private TipoPacote tipo;
    //TipoPacote é a variavel enum que criei no outro arquivo
    private int unidadesPorPacote;

    public Pacote(String nome, TipoPacote tipo, int unidadesPorPacote){
        this.nome = nome;
        this.tipo = tipo;
        this.unidadesPorPacote = unidadesPorPacote;
    }
//Geter == um metodo publico que retorna o valor de um atributo privado
public String getNome() {
    return nome;
}

public TipoPacote getTipo(){
    return tipo;
}

public int getUnidadesPorPacote(){
    return unidadesPorPacote;

}
//override reescreve o metodo, no caso o toString() padrãp
@Override
public String toString(){
    return String.format("%s (%s) - %d Unidades por pacote", nome, tipo.getDescricao(), unidadesPorPacote);
    
    }
    
}

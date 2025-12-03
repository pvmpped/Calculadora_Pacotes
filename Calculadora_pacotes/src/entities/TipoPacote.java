package entities;
//pack especial para garantir que exista apenas pacotes com tampa e sem tampa
//enum = tipo uma lista, varias opções;
public enum TipoPacote {
    COM_TAMPA("Com Tampa"),
    SEM_TAMPA("Sem Tampa");
    
    //defino a variavel descrição, em private final para não ser mudado
    private final String descricao;

    TipoPacote(String descricao) {
        this.descricao = descricao;
    }

    //com get eu busco os dados definidos em descricao
    public String getDescricao() {
        return descricao;
    }
}
    
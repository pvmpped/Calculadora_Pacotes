package entities;
//este arquivo e tipo uma caixa que guarda cada conversao
public class ResultadoConversao {
    private int pacotesCompletos;
    private int unidadesRestantes;
    private int totalUnidades;
    private Pacote pacoteUsado;
    
    public ResultadoConversao(int pacotesCompletos, int unidadesRestantes, 
                             int totalUnidades, Pacote pacoteUsado) {
        this.pacotesCompletos = pacotesCompletos;
        this.unidadesRestantes = unidadesRestantes;
        this.totalUnidades = totalUnidades;
        this.pacoteUsado = pacoteUsado;
    }
    
    // Getters
    public int getPacotesCompletos() {
        return pacotesCompletos;
    }
    
    public int getUnidadesRestantes() {
        return unidadesRestantes;
    }
    
    public int getTotalUnidades() {
        return totalUnidades;
    }
    
    public Pacote getPacoteUsado() {
        return pacoteUsado;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Resultado da Conversão:\n" +
            "Pacote usado: %s\n" +
            "Total de unidades: %d\n" +
            "Pacotes completos: %d\n" +
            "Unidades restantes: %d\n" +
            "Total equivalente: %d unidades",
            pacoteUsado.getNome(),
            totalUnidades,
            pacotesCompletos,
            unidadesRestantes,
            (pacotesCompletos * pacoteUsado.getUnidadesPorPacote()) + unidadesRestantes
        );
    }
}
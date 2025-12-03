package services;

import entities.Pacote;
import entities.ResultadoConversao;
import entities.TipoPacote;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraService {
    private List<Pacote> pacotesDisponiveis;
    
    public CalculadoraService() {
        pacotesDisponiveis = new ArrayList<>();
        inicializarPacotes();
    }
    
    private void inicializarPacotes() {
        // Pacotes com tampa
        pacotesDisponiveis.add(new Pacote("Pacote Frutaria 1 500ml", TipoPacote.COM_TAMPA, 50));
        pacotesDisponiveis.add(new Pacote("Pacote Frutaria 1 1.100", TipoPacote.COM_TAMPA, 50));
        pacotesDisponiveis.add(new Pacote("Pacote Emporio 500ml", TipoPacote.COM_TAMPA, 50));
        pacotesDisponiveis.add(new Pacote("Pacote Emporio 1.100ml", TipoPacote.COM_TAMPA, 50));
        
        // Pacotes sem tampa
        pacotesDisponiveis.add(new Pacote("Rascal G,M e P", TipoPacote.SEM_TAMPA, 40));
        pacotesDisponiveis.add(new Pacote("Cortes M e P", TipoPacote.SEM_TAMPA, 40));
        pacotesDisponiveis.add(new Pacote("Cortes GG", TipoPacote.SEM_TAMPA, 20));
    }
    
    public List<Pacote> getPacotesDisponiveis() {
        return new ArrayList<>(pacotesDisponiveis);
    }
    
    public List<Pacote> getPacotesPorTipo(TipoPacote tipo) {
        List<Pacote> filtrados = new ArrayList<>();
        for (Pacote pacote : pacotesDisponiveis) {
            if (pacote.getTipo() == tipo) {
                filtrados.add(pacote);
            }
        }
        return filtrados;
    }
    
    public ResultadoConversao converterUnidadesParaPacotes(int unidades, Pacote pacote) {
        if (unidades < 0) {
            throw new IllegalArgumentException("Número de unidades não pode ser negativo");
        }
        
        int unidadesPorPacote = pacote.getUnidadesPorPacote();
        int pacotesCompletos = unidades / unidadesPorPacote;
        int unidadesRestantes = unidades % unidadesPorPacote;
        
        return new ResultadoConversao(pacotesCompletos, unidadesRestantes, unidades, pacote);
    }
    
    public int converterPacotesParaUnidades(int pacotes, Pacote pacote) {
        if (pacotes < 0) {
            throw new IllegalArgumentException("Número de pacotes não pode ser negativo");
        }
        
        return pacotes * pacote.getUnidadesPorPacote();
    }
    
    public ResultadoConversao encontrarMelhorCombinacao(int unidades, TipoPacote tipo) {
        List<Pacote> pacotesTipo = getPacotesPorTipo(tipo);
        Pacote melhorPacote = null;
        int menorSobra = Integer.MAX_VALUE;
        int menosPacotes = Integer.MAX_VALUE;
        
        for (Pacote pacote : pacotesTipo) {
            int unidadesPorPacote = pacote.getUnidadesPorPacote();
            int pacotesNecessarios = (int) Math.ceil((double) unidades / unidadesPorPacote);
            int sobra = (pacotesNecessarios * unidadesPorPacote) - unidades;
            
            if (sobra < menorSobra || (sobra == menorSobra && pacotesNecessarios < menosPacotes)) {
                menorSobra = sobra;
                menosPacotes = pacotesNecessarios;
                melhorPacote = pacote;
            }
        }
        
        if (melhorPacote == null) {
            throw new IllegalStateException("Nenhum pacote disponível para o tipo especificado");
        }
        
        int pacotesCompletos = (int) Math.ceil((double) unidades / melhorPacote.getUnidadesPorPacote());
        int unidadesTotal = pacotesCompletos * melhorPacote.getUnidadesPorPacote();
        int unidadesRestantes = unidadesTotal - unidades;
        
        return new ResultadoConversao(pacotesCompletos, unidadesRestantes, unidades, melhorPacote);
    }
    
    public void adicionarPacotePersonalizado(String nome, TipoPacote tipo, int unidadesPorPacote) {
        if (unidadesPorPacote <= 0) {
            throw new IllegalArgumentException("Unidades por pacote deve ser maior que zero");
        }
        pacotesDisponiveis.add(new Pacote(nome, tipo, unidadesPorPacote));
    }
}
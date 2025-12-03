package main;

import entities.Pacote;
import entities.ResultadoConversao;
import entities.TipoPacote;
import services.CalculadoraService;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static CalculadoraService calculadora = new CalculadoraService();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== CALCULADORA DE PACOTES ===");
        
        boolean continuar = true;
        
        while (continuar) {
            exibirMenu();
            int opcao = lerInteiro("Digite sua opção: ");
            
            switch (opcao) {
                case 1:
                    converterUnidadesParaPacotes();
                    break;
                case 2:
                    converterPacotesParaUnidades();
                    break;
                case 3:
                    encontrarMelhorCombinacao();
                    break;
                case 4:
                    listarPacotesDisponiveis();
                    break;
                case 5:
                    adicionarPacotePersonalizado();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Encerrando a calculadora...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
            if (continuar) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Converter Unidades para Pacotes");
        System.out.println("2. Converter Pacotes para Unidades");
        System.out.println("3. Encontrar Melhor Combinação");
        System.out.println("4. Listar Pacotes Disponíveis");
        System.out.println("5. Adicionar Pacote Personalizado");
        System.out.println("0. Sair");
        System.out.println("======================");
    }
    
    private static void converterUnidadesParaPacotes() {
        System.out.println("\n=== CONVERTER UNIDADES PARA PACOTES ===");
        
        listarPacotesDisponiveis();
        
        int unidades = lerInteiro("\nDigite o número de unidades: ");
        
        System.out.println("\nTipos de pacote disponíveis:");
        System.out.println("1. Com Tampa");
        System.out.println("2. Sem Tampa");
        int tipoOpcao = lerInteiro("Escolha o tipo de pacote (1 ou 2): ");
        
        TipoPacote tipo = (tipoOpcao == 1) ? TipoPacote.COM_TAMPA : TipoPacote.SEM_TAMPA;
        List<Pacote> pacotes = calculadora.getPacotesPorTipo(tipo);
        
        System.out.println("\nPacotes disponíveis para " + tipo.getDescricao() + ":");
        for (int i = 0; i < pacotes.size(); i++) {
            System.out.println((i + 1) + ". " + pacotes.get(i));
        }
        
        int escolha = lerInteiro("Escolha o pacote (1-" + pacotes.size() + "): ") - 1;
        
        if (escolha >= 0 && escolha < pacotes.size()) {
            Pacote pacoteEscolhido = pacotes.get(escolha);
            ResultadoConversao resultado = calculadora.converterUnidadesParaPacotes(unidades, pacoteEscolhido);
            System.out.println("\n" + resultado);
        } else {
            System.out.println("Escolha inválida!");
        }
    }
    
    private static void converterPacotesParaUnidades() {
        System.out.println("\n=== CONVERTER PACOTES PARA UNIDADES ===");
        
        listarPacotesDisponiveis();
        
        List<Pacote> todosPacotes = calculadora.getPacotesDisponiveis();
        System.out.println("\nPacotes disponíveis:");
        for (int i = 0; i < todosPacotes.size(); i++) {
            System.out.println((i + 1) + ". " + todosPacotes.get(i));
        }
        
        int escolha = lerInteiro("Escolha o pacote (1-" + todosPacotes.size() + "): ") - 1;
        
        if (escolha >= 0 && escolha < todosPacotes.size()) {
            Pacote pacoteEscolhido = todosPacotes.get(escolha);
            int pacotes = lerInteiro("Digite o número de pacotes: ");
            
            int unidades = calculadora.converterPacotesParaUnidades(pacotes, pacoteEscolhido);
            System.out.println("\nResultado:");
            System.out.println(pacotes + " pacote(s) de " + pacoteEscolhido.getNome());
            System.out.println("= " + unidades + " unidades");
        } else {
            System.out.println("Escolha inválida!");
        }
    }
    
    private static void encontrarMelhorCombinacao() {
        System.out.println("\n=== ENCONTRAR MELHOR COMBINAÇÃO ===");
        
        int unidades = lerInteiro("Digite o número de unidades: ");
        
        System.out.println("\nTipos de pacote:");
        System.out.println("1. Com Tampa");
        System.out.println("2. Sem Tampa");
        int tipoOpcao = lerInteiro("Escolha o tipo de pacote (1 ou 2): ");
        
        TipoPacote tipo = (tipoOpcao == 1) ? TipoPacote.COM_TAMPA : TipoPacote.SEM_TAMPA;
        
        ResultadoConversao resultado = calculadora.encontrarMelhorCombinacao(unidades, tipo);
        System.out.println("\n" + resultado);
    }
    
    private static void listarPacotesDisponiveis() {
        System.out.println("\n=== PACOTES DISPONÍVEIS ===");
        List<Pacote> pacotes = calculadora.getPacotesDisponiveis();
        
        System.out.println("PACOTES COM TAMPA:");
        for (Pacote pacote : pacotes) {
            if (pacote.getTipo() == TipoPacote.COM_TAMPA) {
                System.out.println("  - " + pacote);
            }
        }
        
        System.out.println("\nPACOTES SEM TAMPA:");
        for (Pacote pacote : pacotes) {
            if (pacote.getTipo() == TipoPacote.SEM_TAMPA) {
                System.out.println("  - " + pacote);
            }
        }
    }
    
    private static void adicionarPacotePersonalizado() {
        System.out.println("\n=== ADICIONAR PACOTE PERSONALIZADO ===");
        
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Nome do pacote: ");
        String nome = scanner.nextLine();
        
        System.out.println("Tipo de pacote:");
        System.out.println("1. Com Tampa");
        System.out.println("2. Sem Tampa");
        int tipoOpcao = lerInteiro("Escolha o tipo (1 ou 2): ");
        
        TipoPacote tipo = (tipoOpcao == 1) ? TipoPacote.COM_TAMPA : TipoPacote.SEM_TAMPA;
        
        int unidadesPorPacote = lerInteiro("Unidades por pacote: ");
        
        try {
            calculadora.adicionarPacotePersonalizado(nome, tipo, unidadesPorPacote);
            System.out.println("Pacote adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
    }
}
package org.example;

import com.google.gson.Gson;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Inicializa o scanner para entrada do usuário

        var gson = new Gson(); // Inicializa o Gson para trabalhar com JSON
        var apiConnection = new ApiConnection(); // Cria uma instância da classe ApiConnection
        var json = apiConnection.connection("https://v6.exchangerate-api.com/v6/SUA_KEY_AQUI/latest/USD");  // Obtém o JSON com as taxas de câmbio da API

        var data = gson.fromJson(json, Currency.class);  // Converte o JSON para um objeto Currency
        var jsonObject = gson.toJsonTree(data).getAsJsonObject(); // Obtém o objeto JSON principal
        var rates = jsonObject.getAsJsonObject("conversion_rates"); // Obtém as taxas de conversão

        // Menu de opções para o usuário
        var menu = """ 
                *******************************************
                Seja bem vindo(a) ao conversor de Moeda =)
                
                1) Dólar ->> Peso argentino
                2) Peso argentino ->> Dólar
                3) Dólar ->> Boliviano boliviano
                4) Boliviano boliviano ->> Dólar
                5) Dólar ->> Real brasileiro
                6) Real brasileiro ->> Dólar
                7) Dólar ->> Peso chileno
                8) Peso chileno ->> Dólar
                9) Dólar ->> Peso colombiano
                10) Peso colombiano ->> Dólar
                11) Sair
                Escolha uma opção válida:
                *******************************************
                """;

        while (true) {
            System.out.println(menu);
            int choice;

            try {
                choice = scanner.nextInt(); // Lê a escolha do usuário
            } catch (Exception e) { // Trata exceções em caso de entrada inválida
                System.out.println("Opção inválida. Por favor, escolha um número de 1 a 11.");
                scanner.next(); // Limpar o buffer do scanner
                continue; // Volta ao início do loop
            }

            if (choice == 11) { // Opção para sair do conversor
                break;
            }

            if (choice < 1 || choice > 10) { // Verifica se a escolha é válida
                System.out.println("Opção inválida. Escolha um número de 1 a 11.");
                continue; // Volta ao início do loop
            }

            System.out.println("Digite o valor que deseja converter:");
            double value = scanner.nextDouble(); // Lê o valor a ser convertido
            double result;
            String originalCurrency, targetCurrency;

            switch (choice) { // Realiza a conversão de acordo com a escolha
                case 1, 3, 5, 7, 9 -> { // Casos em que a moeda original é USD
                    originalCurrency = "USD";
                    targetCurrency = switch (choice) {
                        case 1 -> "ARS";
                        case 3 -> "BOB";
                        case 5 -> "BRL";
                        case 7 -> "CLP";
                        default -> "COP";
                    };
                    result = value * rates.getAsJsonPrimitive(targetCurrency).getAsDouble(); // Faz o calculo de conversâo
                }
                case 2, 4, 6, 8, 10 -> { // Casos em que a moeda alvo é USD
                    originalCurrency = switch (choice) {
                        case 2 -> "ARS";
                        case 4 -> "BOB";
                        case 6 -> "BRL";
                        case 8 -> "CLP";
                        default -> "COP";
                    };
                    targetCurrency = "USD";
                    result = value / rates.getAsJsonPrimitive(originalCurrency).getAsDouble(); // Faz o calculo de conversâo
                }
                default -> throw new IllegalStateException("Unexpected value: " + choice); // Caso de escolha inesperada
            }
            System.out.printf("O valor %.2f [%s] corresponde ao valor final de -> %.2f [%s]%n", value, originalCurrency, result, targetCurrency); // Exibe o resultado da conversão
        }
        System.out.println("Saindo do conversor de moedas. Até logo!");
        scanner.close(); // Fecha o scanner
    }
}
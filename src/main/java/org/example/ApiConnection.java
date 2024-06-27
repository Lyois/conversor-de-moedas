package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ApiConnection {

    // Constante para armazenar a chave da API, carregada do arquivo config.properties
    private static final String API_KEY;

    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) { // Bloco estático para inicializar a chave da API ao carregar a classe
            Properties properties = new Properties(); // Cria um objeto Properties para ler o arquivo
            properties.load(fis); // Carrega as propriedades do arquivo
            API_KEY = properties.getProperty("API_KEY"); // Obtém a chave da API da propriedade "API_KEY"
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar a chave da API do arquivo de configuração.", e); // Lança uma exceção em tempo de execução se ocorrer um erro ao carregar a chave
        }
    }


    public String connection(String url){ // Método para estabelecer a conexão com a API e obter o JSON
        var client = HttpClient.newHttpClient(); // Cria um cliente HTTP
        var request = HttpRequest.newBuilder() // Constrói a requisição HTTP, substituindo "SUA_CHAVE_AQUI" pela chave da API carregada
                .uri(URI.create(url.replace("SUA_KEY_AQUI", API_KEY))) // Define a URL da API
                .build(); // Constrói o pedido

        HttpResponse<String> response; // Inicializa a resposta

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString()); // Envia o pedido e obtém a resposta
        }catch (IOException | InterruptedException e){ // Trata exceções de conexão
            throw new RuntimeException(e);
        }

        return response.body(); // Retorna o corpo da resposta (JSON)
    }
}

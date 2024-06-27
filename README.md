# Conversor de Moedas
Este é um conversor de moedas simples que utiliza a API ExchangeRate-API para obter as taxas de câmbio atualizadas e permite ao usuário realizar conversões entre diferentes moedas.

# Funcionalidades
Converte o Dólar americano (USD) para as seguintes moedas e vice-versa:
* Peso argentino (ARS)
* Boliviano (BOB)
* Real brasileiro (BRL)
* Peso chileno (CLP)
* Peso colombiano (COP)
Interface de menu interativa para facilitar a seleção das moedas e do valor a ser convertido.
Utiliza a biblioteca Gson para fazer o parse do JSON da API.
Obtem a chave da API de um arquivo de configuração externo (config.properties).

# Como usar

1. Clone o repositório:
`https://github.com/Lyois/conversor-de-moedas.git`

3. Obtenha uma chave da API ExchangeRate-API:

Acesse o site da [ExchangeRate-API](https://www.exchangerate-api.com/) e crie uma conta gratuita.
Copie a chave da API fornecida.
Configure o arquivo config.properties:

3. Crie um arquivo chamado config.properties na raiz do projeto.

Adicione a seguinte linha ao arquivo, substituindo SUA_CHAVE_AQUI pela sua chave da API:
`API_KEY=SUA_CHAVE_AQUI`
Execute o conversor:
`java Main.java`

4. Siga as instruções no menu:

* Escolha as moedas que deseja converter.
* Digite o valor a ser convertido.
* O resultado da conversão será exibido.

# Estrutura do projeto
* `ApiConnection.java`: Classe responsável por estabelecer a conexão com a API e obter os dados das taxas de câmbio.
* `Currency.java`: Classe que representa os dados da moeda base e as taxas de conversão.
* `CurrencyCode.java`: Classe que armazena os códigos das moedas e seus valores de conversão em relação à moeda base.
* `Main.java`: Classe principal que contém a lógica do conversor de moedas, incluindo o menu interativo e as operações de conversão.
* `config.properties`: Arquivo de configuração que armazena a chave da API.

# Dependências
Gson: Biblioteca Java para trabalhar com JSON.

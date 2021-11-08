# Desafio Sicredi - Assembléia

Olá. Tudo bem? Abaixo segue algumas informações importantes que devem ser levadas em consideração ao avaliar a implemetação

# Acessos - Swagger

* Swagger: http://localhost:8080/api-assembleia/swagger-ui/#
* Recursos relacionados ao Associado: http://localhost:8080/api-assembleia/swagger-ui/#/associado-controller-v-1
* Recursos relacionados à Pauta: http://localhost:8080/api-assembleia/swagger-ui/#/pauta-controller-v-1
* Recursos relacionados à Sessão: http://localhost:8080/api-assembleia/swagger-ui/#/sessao-controller-v-1
* Recursos relacionados ao Votação: http://localhost:8080/api-assembleia/swagger-ui/#/votacao-controller-v-1

# Detalhes de negócio

* Só pode existir uma Sessão por Pauta
* A validação do CPF feita através da API do Heroku é feita no momento da inclusão de um Associado
* Uma observação importante é que a API do Heroku pode retorna "ABLE_TO_VOTE" e "UNABLE_TO_VOTE" para o mesmo CPF


# Detalhes de implementação
* O projeto foi desenvolvido em SpringBoot. Para executar a aplicação, basta rodar o arquivo ApiAssebleiaApplication.
* A API foi dividida tem três pacotes em nível de negócio: "associado", "assembleia" e "votacao", mais um pacote "core"
* Foi utilizado alguns conceitos de Portas e Adaptadores
* A aplicação foi desenvolvida utilizando o banco de dados PostGreSQL para atender ao requisito no qual solicita a permanência dos dados mesmo reiniciando a aplicação. De todo modo, é possível também utilizar o banco de dados H2. Para tal basta desabilitar as propriedades relacionas ao PostGreSQL e habilitar as propriedades relacionadas ao H2, encontradas no arquivo application.properties.
* Para utilização do PostGreSQL, basta apenas criar um banco de dados "db_assembleia" e as tabelas serão criadas automaticamente assim que o projeto for executado pela primeiva vez
* Foi utilizado o Apache Kafka para mensageria. As configurações estão no arquivo application.properties 
* Foi feito o tratamento de exceção
* Foi utilizado o Swagger para documentação da aplicação
* A API está versionada  
* Não foi utilizado o Lombok
* Não consegui implementar a camada de Logs em tempo. Mas dando uma visão de implementação, eu utilizaria a anotação @Slf4j para logar o passo a passo do código
* Não consegui implementar a camada de testes com JUnit e Jacoco em tempo. Mas dando uma visão de implementação, eu faria os asserts utilizando as mensagens das exceções lançadas

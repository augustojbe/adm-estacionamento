## ADM Estacionamento
Este projeto é um sistema de gerenciamento de estacionamento desenvolvido para facilitar o controle de veículos, vagas e pagamentos em um estacionamento. Ele foi criado como parte de um estudo ou projeto pessoal e pode ser adaptado para diferentes necessidades.

## Funcionalidades Principais
Cadastro de Veículos: Registro de veículos estacionados.

Controle de Vagas: Gerenciamento das vagas disponíveis no estacionamento.

Registro de Entrada e Saída: Controle do horário de entrada e saída dos veículos.

Cálculo de Tarifas: Cálculo automático do valor a ser pago com base no tempo de permanência.

Relatórios: Geração de relatórios de movimentação e faturamento.

## Tecnologias Utilizadas
Linguagem de Programação: Java

Banco de Dados: MySQL (ou outro SGBD relacional)

Frameworks: Possivelmente Java Swing ou JavaFX para a interface gráfica (dependendo da implementação).

Ferramentas de Build: Maven (gerenciamento de dependências).

## Pré-requisitos
Antes de executar o projeto, certifique-se de ter instalado:

Java Development Kit (JDK): Versão 8 ou superior.

MySQL: Para o banco de dados.

Maven: Para gerenciar as dependências do projeto.

Git: Para clonar o repositório.

## Como Executar o Projeto
Siga os passos abaixo para configurar e executar o projeto:

1. Clonar o Repositório
bash
git clone https://github.com/augustojbe/adm-estacionamento.git
cd adm-estacionamento

2. Configurar o Banco de Dados
Crie um banco de dados no MySQL chamado estacionamento.

Importe o arquivo SQL (se houver) para criar as tabelas necessárias.

3. Configurar o Projeto
Abra o arquivo de configuração do banco de dados (se houver) e atualize as credenciais de acesso ao MySQL (usuário e senha).

4. Compilar e Executar
Compile o projeto usando o Maven:

bash
mvn clean install
Execute o projeto:

bash
mvn exec:java -Dexec.mainClass="com.estacionamento.Main"
(Substitua com.estacionamento.Main pela classe principal do projeto, se necessário.)

Estrutura do Projeto
Aqui está uma visão geral da estrutura do projeto:

![image](https://github.com/user-attachments/assets/5d6d5c98-77c2-4b6a-b996-4776ba08e508)

## Contribuição
Se você quiser contribuir para este projeto, siga estas etapas:

Faça um fork do repositório.

Crie uma branch para sua feature (git checkout -b feature/nova-feature).

Faça commit das suas alterações (git commit -m 'Adicionando nova feature').

Faça push para a branch (git push origin feature/nova-feature).

Abra um Pull Request no repositório original.

## Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.

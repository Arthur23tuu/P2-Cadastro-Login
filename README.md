# ⚡ Sistema de Autenticação e CRUD (Java POO)

Projeto desenvolvido como requisito para a avaliação P2 da disciplina de **Desenvolvimento Para Servidores I**. Este sistema expande os conceitos de **Programação Orientada a Objetos (POO)** aplicados na P1, introduzindo agora a persistência de dados com banco de dados relacional e a construção de interfaces gráficas.

## 🎓 Dados Acadêmicos
- **Instituição:** Fatec São Roque
- **Aluno:** Arthur Camargo Laranjeira
- **Semestre:** 4º
- **Disciplina:** Desenvolvimento Para Servidores I
- **Professor:** Marcos Costa de Sousa

## 📝 Sobre o Projeto
Este projeto consiste em uma aplicação Desktop de **Login e Cadastro de Usuários** com operações completas de CRUD (Create, Read, Update, Delete) conectada a um banco de dados. O sistema foi desenvolvido com interfaces gráficas utilizando a biblioteca Swing do Java e aplica a arquitetura e os pilares de Orientação a Objetos abordados em sala.

A aplicação demonstra o ciclo completo de gerenciamento de um usuário:
- **Create (Cadastro):** Valida entradas vazias, verifica se as senhas coincidem, checa no banco se o usuário já existe e, caso esteja tudo certo, insere um novo registro mascarando a senha no banco de dados.
- **Read (Consulta/Login):** Autentica o usuário validando as credenciais no banco. Em caso de sucesso, direciona para a tela inicial (Home) exibindo uma mensagem de boas-vindas personalizada.
- **Update (Alteração):** Permite que o usuário logado atualize seu nome ou sua senha, exigindo a confirmação da senha atual por questões de segurança antes de aplicar o `UPDATE` no banco.
- **Delete (Exclusão):** Fornece a opção do próprio usuário excluir sua conta do sistema. Após a confirmação, o `DELETE` é executado no banco e o sistema retorna para a tela de login.

## 🛠️ Ferramentas Utilizadas
Para a construção deste projeto, afastei-me do ambiente padrão apresentado em aula e configurei meu próprio fluxo de trabalho, utilizando as seguintes ferramentas:

- **Editor de Código:** Visual Studio Code (VS Code)
- **Banco de Dados:** Microsoft SQL Server Management Studio (SSMS) 22
- **Linguagem:** Java (JDK 17)

## ⚙️ Conexão Banco - Java (SQL Server)
Como optei por utilizar o **SQL Server** em vez do MySQL (utilizado como base no material da disciplina), a comunicação entre a aplicação Java e o banco de dados exigiu algumas adaptações na classe `Conexao.java` e a inclusão de bibliotecas específicas da Microsoft no projeto (`Referenced Libraries`):

1. **`mssql-jdbc-13.6.0.jre11.jar`**: É o Driver JDBC oficial da Microsoft. Ele atua como um "tradutor", ensinando o Java a se comunicar, enviar comandos SQL e receber dados do SQL Server.
2. **`mssql-jdbc_auth-13.6.0.x64.dll`**: Como configurei meu banco de dados para usar a **Autenticação do Windows** (sem necessidade de usuário e senha na string de conexão), o Java precisa desta biblioteca dinâmica (`.dll`) colocada na raiz do projeto para solicitar e validar as permissões de acesso diretamente com o sistema operacional.
3. **Adaptação de Queries:** A função nativa de criptografia `md5()` do MySQL foi substituída nas instruções SQL do Java pela função equivalente do SQL Server (`HASHBYTES`).

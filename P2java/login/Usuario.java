package login;

import java.sql.SQLException;

public class Usuario {
    private String usuario;
    private String nome;
    private String senha;
    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultAlteracao;
    private boolean resultExclusao;

    static String nomeUsuario;
    static String usuarioSistema;

    public String getUsuario() { return this.usuario; }
    public void setUsuario(String novoUsuario) { this.usuario = novoUsuario; }

    public String getNome() { return this.nome; }
    public void setNome(String novoNome) { this.nome = novoNome; }

    public String getSenha() { return this.senha; }
    public void setSenha(String novaSenha) { this.senha = novaSenha; }

    public boolean verificaUsuario(String usuario, String senha) {
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            banco.stmt = banco.con.createStatement();
            // Função MD5 adaptada para SQL Server
            banco.resultset = banco.stmt.executeQuery("SELECT * FROM usuario WHERE usuario = '" + usuario + "' AND senha = CONVERT(VARCHAR(32), HASHBYTES('MD5', '" + senha + "'), 2)");
            
            if (banco.resultset.next()) {
                resultUsuario = true;
                setUsuario(banco.resultset.getString(1));
                setNome(banco.resultset.getString(2));
                
                nomeUsuario = getNome();
                usuarioSistema = getUsuario();
            } else {
                resultUsuario = false;
            }
            banco.fecharConexao();
        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }
        return resultUsuario;
    }

    public boolean verificaUsuario(String usuario) {
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.resultset = banco.stmt.executeQuery("SELECT * FROM usuario WHERE usuario = '" + usuario + "'");
            
            if (banco.resultset.next()) {
                resultUsuario = true;
            } else {
                resultUsuario = false;
            }
            banco.fecharConexao();
        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }
        return resultUsuario;
    }

    public boolean cadastraUsuario(String nome, String usuario, String senha) {
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.stmt.execute("INSERT INTO usuario (nome, usuario, senha) VALUES ('" + nome + "','" + usuario + "', CONVERT(VARCHAR(32), HASHBYTES('MD5', '" + senha + "'), 2))");
            resultCadastro = true;
        } catch (SQLException ec) {
            System.out.println("Erro ao inserir o usuário " + ec.getMessage());
            resultCadastro = false;
        }
        banco.fecharConexao();
        return resultCadastro;
    }

    public boolean alteraUsuario(String nome, String usuario, String senha) {
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.stmt.execute("UPDATE usuario SET nome = '" + nome + "', senha = CONVERT(VARCHAR(32), HASHBYTES('MD5', '" + senha + "'), 2) WHERE usuario = '" + usuario + "'");
            resultAlteracao = true;
        } catch (SQLException ec) {
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
            resultAlteracao = false;
        }
        banco.fecharConexao();
        return resultAlteracao;
    }

    public boolean excluiUsuario(String usuario) {
        Conexao banco = new Conexao();
        try {
            banco.abrirConexao();
            banco.stmt = banco.con.createStatement();
            banco.stmt.execute("DELETE FROM usuario WHERE usuario = '" + usuario + "'");
            resultExclusao = true;
        } catch (SQLException ec) {
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
            resultExclusao = false;
        }
        banco.fecharConexao();
        return resultExclusao;
    }
}
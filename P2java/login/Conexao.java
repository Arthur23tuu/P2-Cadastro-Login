package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    
    // Conexão configurada para o seu SQL Server com Autenticação do Windows
    private final String servidor = "jdbc:sqlserver://localhost\\MSSQLSERVER01;databaseName=bd__login;integratedSecurity=true;encrypt=false;";
    private final String usuario = "";
    private final String senha = "";
    private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public Connection abrirConexao() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(servidor, usuario, senha);
            stmt = con.createStatement();
            System.out.println("Conexão aberta com sucesso");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao acessar banco de dados, verifique! " + e.getMessage());
        }
        return con;
    }

    public void fecharConexao() {
        try {
            con.close();
            System.out.println("Conexão finalizada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar conexão " + e.getMessage());
        }
    }
}
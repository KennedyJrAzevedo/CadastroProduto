package com.mycompany.cadastrarproduto;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProdutoDAO {

    private static MySQLConnection mysql;
    private static Connection connection;
    private static ResultSetMetaData metaData;
    private static CallableStatement statement;

    public void insertMySQL(String nome, int categoria, String descricao, String codBarras, int marca, double preco, double peso) {
        mysql = new MySQLConnection();
        connection = mysql.getConexao();
        if (connection != null) {
            try {
                String valores = "?, ?, ?, ?, ?, ?, ?, ?";
                String sql = "{CALL INSERIR_PRODUTO("+ valores + ")}";

                statement = connection.prepareCall(sql);
                statement.setString(1, nome);
                statement.setInt(2, categoria);
                statement.setString(3, descricao);
                statement.setString(4, codBarras);
                statement.setInt(5, marca);
                statement.setDouble(6, peso);
                statement.setDouble(7, preco);
                statement.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
                
                
                int qtdeCad = statement.executeUpdate();
                if (qtdeCad > 0) {
                    System.out.println("Produto cadastrado com sucesso!");
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!","Cadastro Realizado",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysql.desconectar();
            }
        }
    }

    public void atualizarProduto(int id_produto, String nome, int categoria, String descricao, String codBarras, int marca, double preco, double peso) {
        mysql = new MySQLConnection();
        connection = mysql.getConexao();
        if (connection != null) {
            try {
                String valores = "?, ?, ?, ?, ?, ?, ?, ?, ?";
                String sql = "{CALL ALTERAR_PRODUTO(" + valores + ")}";

                statement = connection.prepareCall(sql);
                statement.setInt(1, id_produto);
                statement.setString(2, nome);
                statement.setInt(3, categoria);
                statement.setString(4, descricao);
                statement.setString(5, codBarras);
                statement.setInt(6, marca);
                statement.setDouble(7, peso);
                statement.setDouble(8, preco);
                statement.setDate(9, java.sql.Date.valueOf(java.time.LocalDate.now()));

                int qtdeCad = statement.executeUpdate();
                if (qtdeCad > 0) {
                    System.out.println("Produto alterado com sucesso!");
                    JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!","Alteração Realizada",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysql.desconectar();
            }
        }
    }

    protected void excluirProduto(int id_produto) {
        mysql = new MySQLConnection();
        connection = mysql.getConexao();
        if (connection != null) {
            try {
                String valores = "?";
                String sql = "{CALL EXCLUIR_PRODUTO(" + valores + ")}";

                statement = connection.prepareCall(sql);
                statement.setInt(1, id_produto);

                int qtdeCad = statement.executeUpdate();
                if (qtdeCad > 0) {
                    System.out.println("Produto cadastrado com sucesso!");
                    JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!","Exclusão Realizada",JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysql.desconectar();
            }
        }
    }

    public DefaultTableModel selecionarProdutos() {
        mysql = new MySQLConnection();
        connection = mysql.getConexao();
        DefaultTableModel tabledados = new DefaultTableModel();
        if (connection != null) {
            try {

                String query = "{CALL SELECIONAR_PRODUTOS()}";
                statement = connection.prepareCall(query);
                ResultSet resultSet = statement.executeQuery();
                metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                String[] columnNames = {"ID", "NOME", "CATEGORIA", "DESCRICAO", "CODBARRAS", "MARCA", "PESO", "PRECO", "DATACADASTRO"};
                for (String n : columnNames) {
                    tabledados.addColumn(n);
                }
                while (resultSet.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getObject(i);
                    }
                    tabledados.addRow(row);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysql.desconectar();
            }
        }
        return tabledados;
    }

    public DefaultComboBoxModel selecionarCategoria() {
        mysql = new MySQLConnection();
        connection = mysql.getConexao();
        DefaultComboBoxModel tabledados = new DefaultComboBoxModel();
        if (connection != null) {
            try {
                String query = "{call selecionar_categoria()}";
                statement = connection.prepareCall(query);
                ResultSet resultSet = statement.executeQuery();
        
                // Adiciona as linhas ao modelo da tabela
                while (resultSet.next()) {
                    tabledados.addElement(resultSet.getString("categoria"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysql.desconectar();
            }
        }
        return tabledados;
    }

    public DefaultComboBoxModel selecionarMarca() {
        mysql = new MySQLConnection();
        connection = mysql.getConexao();
        DefaultComboBoxModel tabledados = new DefaultComboBoxModel();
        if (connection != null) {
            try {
                String query = "{CALL SELECIONAR_MARCA()}";
                statement = connection.prepareCall(query);
                ResultSet resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    tabledados.addElement(resultSet.getString("marca"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysql.desconectar();
            }
        }
        return tabledados;
    }
}

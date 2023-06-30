package model.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.entity.Produto;


public class ProdutoDAO {
    public void inserirproduto(Produto produto) {
        ConectaBD con = new ConectaBD();
        String sql = "INSERT INTO produto (numeroChassi ,placa ,modelo ,marca ,valor) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, produto.getnumeroChassi());
            pst.setString(2, produto.getplaca());
            pst.setString(3, produto.getmodelo());
            pst.setString(4, produto.getmarca());
            pst.setDouble(5, produto.getvalor());
            pst.execute();
            System.out.println(produto.getmarca() + " inserido");   
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean excluir(int chave) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try{
            ConectaBD conexao = new ConectaBD();
            PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, chave);
            int linhas = pst.executeUpdate();
            return linhas>0;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Produto consultarid(int id) {
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM produto WHERE id = ?";
        Produto p =null;
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String numeroChassi = rs.getString("numeroChassi");
                String placa = rs.getString("placa");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                Double valor = rs.getDouble("valor");
                p = new Produto(numeroChassi, placa, modelo, marca, valor);
                p.setID(rs.getInt("id"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }
     public Produto consultarplaca(String placa1) {
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM produto WHERE placa = ?";
        Produto p =null;
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, placa1);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String numeroChassi = rs.getString("numeroChassi");
                String placa = rs.getString("placa");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                Double valor = rs.getDouble("valor");
                p = new Produto(numeroChassi, placa, modelo, marca, valor);
                p.setString(rs.getNString("placa"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public Produto consultaProduto(String placa) {
        ConectaBD con = new ConectaBD();
        String sql = "SELECT  * FROM produto WHERE placa = ?";
        Produto p = null;
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, placa);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p = new Produto();
                p.setplaca(placa);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;

    }
  /*    public boolean atualizar(Produto produto) {
        ConectaBD con = new ConectaBD();
        String sql = "UPDATE produto SET numeroChassi = ?, placa = ?, modelo = ?, marca = ?, valor = ? WHERE placa = ?";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, produto.getnumeroChassi()); 
            pst.setString(2, produto.getplaca()); 
            pst.setString(3, produto.getmodelo()); 
            pst.setString(4, produto.getmarca()); 
            pst.setDouble(5, produto.getvalor()); 
            pst.setString(6, produto.getplaca()); 
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
        return false;
    }
public boolean atualizar(String placa1, String numeroChassi, String placa, String modelo, String marca, double valor) {
    // Restante do código
}

*/
    public boolean atualizar(String placa1, String numeroChassi1,String modelo1, String marca1, String marca2, String valor2) {
        ConectaBD con = new ConectaBD();
        Produto p = consultaProduto(placa1);
        String numeroChassi = p.getnumeroChassi();
        String placa = p.getplaca();
        String modelo = p.getmodelo();
        String marca = p.getmarca();
        double valor = p.getvalor();

        String nnumeroChassi = numeroChassi;
        String nplaca = placa;
        String nmodelo = modelo;
        String nmarca = marca;
        Double nvalor = valor;

        String sql = "UPDATE produto SET numeroChassi = ?, placa = ?, modelo = ?, marca = ?, valor = ? WHERE id = ?";
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, nnumeroChassi); 
            pst.setString(2, nplaca); 
            pst.setString(3, nmodelo); 
            pst.setString(4, nmarca); 
            pst.setDouble(5, nvalor); 
            pst.setInt(6, p.getId());
            pst.executeUpdate();

            if (pst.getUpdateCount() > 0) {
				return true;
            }
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
        return false;
    }
}
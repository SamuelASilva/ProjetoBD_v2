package model.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
    public Produto consultarplaca(String placa) {
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM produto WHERE placa = ?";
        Produto p =null;
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, placa);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String numeroChassi = rs.getString("numeroChassi");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                Double valor = rs.getDouble("valor");
                p = new Produto(numeroChassi, placa, modelo, marca, valor);
                p.setID(rs.getInt("placa"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }
    public boolean atualizar(Produto produto) {
        ConectaBD con = new ConectaBD();
        String sql = "UPDATE produto SET numeroChassi = ?, placa = ?, modelo = ?, marca = ?, valor = ? WHERE placa = ? ";
        try{
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, produto.getnumeroChassi());
            pst.setString(2, produto.getplaca());
            pst.setString(3, produto.getmodelo());
            pst.setString(4, produto.getmarca());
            pst.setDouble(5, produto.getvalor());
            pst.execute();
            return pst.getUpdateCount() > 0;
            // int linhas = pst.executeUpdate();
            // return linhas>0;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
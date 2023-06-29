package model.entity;


public class Produto {
    private int Id;
    private String numeroChassi;
    private String placa;
    private String modelo;
    private String marca;
    private double valor;

    public Produto() {

    }
    public Produto(String numeroChassi,String placa,String modelo,String marca,double valor) {
        this.numeroChassi = numeroChassi;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.valor = valor;
    }
    public int getId() {
        return Id;
    }
    public void setID (int id) {
        this.Id = id;
    }
    public String getnumeroChassi() {
        return numeroChassi;
    }
    public void setnumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }
    public String getplaca() {
        return placa;
    }
    public void setplaca(String placa) {
        this.placa = placa;
    }
    public String getmodelo() {
        return modelo;
    }
    public void setmodelo(String modelo) {
        this.modelo = modelo;
    }
    public String getmarca() {
        return marca;
    }
    public void setmarca(String marca) {
        this.marca = marca;
    }
    public void setnome(String marca) {
        this.marca = marca;
    }
    public double getvalor() {
        return valor;
    }
    public void setvalor(double valor) {
        this.valor = valor;
    }

}
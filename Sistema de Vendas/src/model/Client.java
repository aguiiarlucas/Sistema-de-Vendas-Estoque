package model;

import java.util.Date;
import java.util.List;

public class Client {

    private static int count =1;

    private int id ;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    private double saldo = 100;

    private String password;
    private Date dataNascimento;
    private List<Product >products;



    public Client() {
    }



    public Client(String nome, String endereco, String telefone, String email, String password, Date dataNascimento, double saldo) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.password = password;
        this.dataNascimento = dataNascimento;
        this.saldo = saldo;
        this.id =count;
        Client.count+=1;

    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }



    public String getEndereco() {
        return endereco;
    }



    public String getTelefone() {
        return telefone;
    }


    public String getEmail() {
        return email;
    }



    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getPassword() {
        return password;
    }


    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.getId() +
                ", nome='" + this.getNome() + '\'' +
                ", endereco='" + this.getEndereco() + '\'' +
                ", telefone='" + this.getTelefone() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", saldo=" + this.getSaldo() +
                ", password='" + this.getPassword() + '\'' +
                ", dataNascimento=" + this.getDataNascimento() +
                '}';
    }

    public void adicionarSaldo(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }
}

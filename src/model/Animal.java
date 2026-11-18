package model;

import java.time.LocalDate;

public class Animal {

    private final int id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private String porte;
    private String estadoSaude;
    private LocalDate dataResgate;
    private String statusAdocao;

    private static int contadorId = 1;

    public Animal(String nome, String especie, String raca, int idade,
                  String porte, String estadoSaude, LocalDate dataResgate) {

        this.id = contadorId++;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.porte = porte;
        this.estadoSaude = estadoSaude;
        this.dataResgate = dataResgate;
        this.statusAdocao = "Disponível";
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public String getPorte() {
        return porte;
    }

    public String getEstadoSaude() {
        return estadoSaude;
    }

    public LocalDate getDataResgate() {
        return dataResgate;
    }

    public String getStatusAdocao() {
        return statusAdocao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public void setEstadoSaude(String estadoSaude) {
        this.estadoSaude = estadoSaude;
    }

    public void setDataResgate(LocalDate dataResgate) {
        this.dataResgate = dataResgate;
    }

    public void setStatusAdocao(String statusAdocao) {
        this.statusAdocao = statusAdocao;
    }

    public void marcarComoAdotado() {
        this.statusAdocao = "Adotado";
    }

    public void disponibilizarParaAdocao() {
        this.statusAdocao = "Disponível";
    }

    public boolean estaDisponivelParaAdocao() {
        return this.statusAdocao.equals("Disponível");
    }
}
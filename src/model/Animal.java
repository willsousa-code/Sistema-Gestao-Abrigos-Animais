
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

        validarTexto(nome, "O nome do animal não pode ficar vazio!");
        validarTexto(especie, "A espécie não pode ficar vazia!");
        validarTexto(raca, "A raça não pode ficar vazia!");
        validarTexto(porte, "O porte não pode ficar vazio!");
        validarTexto(estadoSaude, "O estado de saúde não pode ficar vazio!");

        if (idade < 0) {
            throw new IllegalArgumentException(
                "A idade do animal não pode ser negativa!"
            );
        }

        if (dataResgate == null || dataResgate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                "A data de resgate é inválida!"
            );
        }

        this.id = contadorId++;
        this.nome = nome.trim();
        this.especie = especie.trim();
        this.raca = raca.trim();
        this.idade = idade;
        this.porte = porte.trim();
        this.estadoSaude = estadoSaude.trim();
        this.dataResgate = dataResgate;
        this.statusAdocao = "Disponível";
    }

    private void validarTexto(String valor, String mensagem) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
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
        validarTexto(nome, "O nome do animal não pode ficar vazio!");
        this.nome = nome.trim();
    }

    public void setEspecie(String especie) {
        validarTexto(especie, "A espécie não pode ficar vazia!");
        this.especie = especie.trim();
    }

    public void setRaca(String raca) {
        validarTexto(raca, "A raça não pode ficar vazia!");
        this.raca = raca.trim();
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException(
                "A idade do animal não pode ser negativa!"
            );
        }
        this.idade = idade;
    }

    public void setPorte(String porte) {
        validarTexto(porte, "O porte não pode ficar vazio!");
        this.porte = porte.trim();
    }

    public void setEstadoSaude(String estadoSaude) {
        validarTexto(
            estadoSaude,
            "O estado de saúde não pode ficar vazio!"
        );
        this.estadoSaude = estadoSaude.trim();
    }

    public void setDataResgate(LocalDate dataResgate) {
        if (dataResgate == null || dataResgate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                "A data de resgate é inválida!"
            );
        }
        this.dataResgate = dataResgate;
    }

    public void setStatusAdocao(String statusAdocao) {
        validarTexto(
            statusAdocao,
            "O status de adoção não pode ficar vazio!"
        );

        if ("Disponível".equalsIgnoreCase(statusAdocao.trim())
                && "Crítico".equalsIgnoreCase(estadoSaude)) {
            throw new IllegalStateException(
                "Animais em estado crítico não podem ficar disponíveis para adoção!"
            );
        }

        this.statusAdocao = statusAdocao.trim();
    }

    public void marcarComoAdotado() {
        this.statusAdocao = "Adotado";
    }

    public void disponibilizarParaAdocao() {
        if ("Crítico".equalsIgnoreCase(estadoSaude)) {
            throw new IllegalStateException(
                "Animais em estado crítico não podem ser disponibilizados para adoção!"
            );
        }

        this.statusAdocao = "Disponível";
    }

    public boolean estaDisponivelParaAdocao() {
        return "Disponível".equalsIgnoreCase(statusAdocao)
            && !"Crítico".equalsIgnoreCase(estadoSaude);
    }
}

package model;

public class Compatibilidade {

    private Animal animal;
    private Usuario interessado;
    private int pontuacao;
    private String justificativa;

    public Compatibilidade(Animal animal, Usuario interessado) {
        this.animal = animal;
        this.interessado = interessado;
        this.pontuacao = 0;
        this.justificativa = "";
    }

    public Animal getAnimal() {
        return animal;
    }

    public Usuario getInteressado() {
        return interessado;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public void calcularCompatibilidade() {
        pontuacao = 0;
        justificativa = "";

        if (animal.estaDisponivelParaAdocao()) {
            pontuacao += 40;
            justificativa += "Animal disponível para adoção. ";
        }

        if (animal.getEstadoSaude().equalsIgnoreCase("Saudável")) {
            pontuacao += 30;
            justificativa += "Animal com boa condição de saúde. ";
        }

        if (animal.getIdade() <= 3) {
            pontuacao += 30;
            justificativa += "Animal jovem. ";
        }
    }

    public String getClassificacao() {
        if (pontuacao >= 80) {
            return "Alta compatibilidade";
        } else if (pontuacao >= 50) {
            return "Média compatibilidade";
        } else {
            return "Baixa compatibilidade";
        }
    }
}
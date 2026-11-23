
package model;

import java.util.ArrayList;
import java.util.List;

public class Voluntario extends Usuario {

    private String areaAtuacao;
    private List<String> historicoParticipacoes;
    private List<Animal> animaisSobResponsabilidade;

    public Voluntario(String nome, String email, String senha,
                      String telefone, String areaAtuacao) {

        super(nome, email, senha, telefone);

        setAreaAtuacao(areaAtuacao);

        this.historicoParticipacoes = new ArrayList<>();
        this.animaisSobResponsabilidade = new ArrayList<>();
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        if (areaAtuacao == null || areaAtuacao.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "A área de atuação não pode ficar vazia!"
            );
        }

        this.areaAtuacao = areaAtuacao.trim();
    }

    public List<String> getHistoricoParticipacoes() {
        return new ArrayList<>(historicoParticipacoes);
    }

    public void setHistoricoParticipacoes(List<String> historicoParticipacoes) {
        if (historicoParticipacoes == null) {
            throw new IllegalArgumentException(
                "O histórico de participações não pode ser nulo!"
            );
        }

        for (String participacao : historicoParticipacoes) {
            if (participacao == null || participacao.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "O histórico contém uma participação inválida!"
                );
            }
        }

        this.historicoParticipacoes = new ArrayList<>(historicoParticipacoes);
    }

    public void adicionarParticipacao(String participacao) {
        if (participacao == null || participacao.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "A participação não pode ficar vazia!"
            );
        }

        historicoParticipacoes.add(participacao.trim());
    }

    public List<Animal> acompanharAnimais() {
        return new ArrayList<>(animaisSobResponsabilidade);
    }

    public List<Animal> getAnimaisSobResponsabilidade() {
        return acompanharAnimais();
    }

    public void assumirResponsabilidade(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal inválido!");
        }

        if (animaisSobResponsabilidade.contains(animal)) {
            throw new IllegalArgumentException(
                "Este animal já está sob responsabilidade do voluntário!"
            );
        }

        animaisSobResponsabilidade.add(animal);
    }

    public void removerResponsabilidade(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal inválido!");
        }

        if (!animaisSobResponsabilidade.remove(animal)) {
            throw new IllegalArgumentException(
                "Este animal não está sob responsabilidade do voluntário!"
            );
        }
    }

    @Override
    public String getTipoUsuario() {
        return "Voluntário";
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class Voluntario extends Usuario {

    private String areaAtuacao;
    private List<String> historicoParticipacoes;

    public Voluntario(String nome, String email, String senha, String telefone, String areaAtuacao) {
        super(nome, email, senha, telefone);
        this.areaAtuacao = areaAtuacao;
        this.historicoParticipacoes = new ArrayList<>();
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public List<String> getHistoricoParticipacoes() {
        return historicoParticipacoes;
    }

    public void setHistoricoParticipacoes(List<String> historicoParticipacoes) {
        this.historicoParticipacoes = historicoParticipacoes;
    }

    public void adicionarParticipacao(String participacao) {
        historicoParticipacoes.add(participacao);
    }

    @Override
    public String getTipoUsuario() {
        return "Voluntário";
    }
}
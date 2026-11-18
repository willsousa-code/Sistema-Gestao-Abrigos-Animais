package model;

import java.util.ArrayList;
import java.util.List;

public class Veterinario extends Usuario {

    private String crmv;
    private String especialidade;
    private List<String> historicoAtendimentos;

    public Veterinario(String nome, String email, String senha, String telefone,
                       String crmv, String especialidade) {

        super(nome, email, senha, telefone);
        this.crmv = crmv;
        this.especialidade = especialidade;
        this.historicoAtendimentos = new ArrayList<>();
    }

    public String getCrmv() {
        return crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public List<String> getHistoricoAtendimentos() {
        return historicoAtendimentos;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void setHistoricoAtendimentos(List<String> historicoAtendimentos) {
        this.historicoAtendimentos = historicoAtendimentos;
    }

    public void adicionarAtendimento(String atendimento) {
        historicoAtendimentos.add(atendimento);
    }

    @Override
    public String getTipoUsuario() {
        return "Veterinário";
    }
}
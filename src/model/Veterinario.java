
package model;

import java.util.ArrayList;
import java.util.List;

public class Veterinario extends Usuario {

    private String crmv;
    private String especialidade;
    private List<String> historicoAtendimentos;

    public Veterinario(String nome, String email, String senha,
                       String telefone, String crmv, String especialidade) {

        super(nome, email, senha, telefone);

        setCrmv(crmv);
        setEspecialidade(especialidade);

        this.historicoAtendimentos = new ArrayList<>();
    }

    public String getCrmv() {
        return crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public List<String> getHistoricoAtendimentos() {
        return new ArrayList<>(historicoAtendimentos);
    }

    public void setCrmv(String crmv) {
        if (crmv == null || crmv.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O CRMV não pode ficar vazio!"
            );
        }

        this.crmv = crmv.trim();
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "A especialidade não pode ficar vazia!"
            );
        }

        this.especialidade = especialidade.trim();
    }

    public void setHistoricoAtendimentos(List<String> historicoAtendimentos) {
        if (historicoAtendimentos == null) {
            throw new IllegalArgumentException(
                "O histórico de atendimentos não pode ser nulo!"
            );
        }

        for (String atendimento : historicoAtendimentos) {
            if (atendimento == null || atendimento.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "O histórico contém um atendimento inválido!"
                );
            }
        }

        this.historicoAtendimentos = new ArrayList<>(historicoAtendimentos);
    }

    public void adicionarAtendimento(String atendimento) {
        if (atendimento == null || atendimento.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "O atendimento não pode ficar vazio!"
            );
        }

        historicoAtendimentos.add(atendimento.trim());
    }

    @Override
    public String getTipoUsuario() {
        return "Veterinário";
    }
}

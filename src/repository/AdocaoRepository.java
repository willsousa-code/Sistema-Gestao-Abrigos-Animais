
package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Adocao;

public class AdocaoRepository {

    private List<Adocao> adocoes = new ArrayList<>();

    public void cadastrar(Adocao adocao) {

        if (adocao == null) {
            throw new IllegalArgumentException("Adoção inválida!");
        }

        if (buscarPorId(adocao.getId()) != null) {
            throw new IllegalArgumentException("Adoção já cadastrada!");
        }

        adocoes.add(adocao);
    }

    public List<Adocao> listar() {
        return Collections.unmodifiableList(new ArrayList<>(adocoes));
    }

    public Adocao buscarPorId(int id) {

        for (Adocao adocao : adocoes) {

            if (adocao.getId() == id) {
                return adocao;
            }
        }

        return null;
    }

    public void excluir(int id) {

        if (buscarPorId(id) == null) {
            throw new IllegalArgumentException("Adoção não encontrada!");
        }

        throw new UnsupportedOperationException(
            "O histórico de adoções não pode ser excluído!"
        );
    }

    public List<Adocao> buscarPorStatus(String status) {

        List<Adocao> resultado = new ArrayList<>();

        if (status == null || status.trim().isEmpty()) {
            return resultado;
        }

        for (Adocao adocao : adocoes) {

            if (adocao.getStatus() != null &&
                adocao.getStatus().equalsIgnoreCase(status.trim())) {

                resultado.add(adocao);
            }
        }

        return resultado;
    }
}

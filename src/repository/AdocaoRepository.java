package repository;

import java.util.ArrayList;
import java.util.List;
import model.Adocao;

public class AdocaoRepository {

    private List<Adocao> adocoes = new ArrayList<>();

    public void cadastrar(Adocao adocao) {
        adocoes.add(adocao);
    }

    public List<Adocao> listar() {
        return adocoes;
    }

    public Adocao buscarPorId(int id) {

        for (Adocao a : adocoes) {
            if (a.getId() == id) {
                return a;
            }
        }

        return null;
    }

    public void excluir(int id) {
        adocoes.removeIf(a -> a.getId() == id);
    }

    public List<Adocao> buscarPorStatus(String status) {

        List<Adocao> resultado = new ArrayList<>();

        for (Adocao a : adocoes) {
            if (a.getStatus().equalsIgnoreCase(status)) {
                resultado.add(a);
            }
        }

        return resultado;
    }
}
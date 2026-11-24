
package main;

import repository.*;
import service.*;
import model.Administrador;
import model.Usuario;

public class SistemaContexto {

    public final AnimalRepository animais = new AnimalRepository();
    public final UsuarioRepository usuarios = new UsuarioRepository();
    public final AdocaoRepository adocoes = new AdocaoRepository();
    public final ConsultaRepository consultas = new ConsultaRepository();
    public final VacinaRepository vacinas = new VacinaRepository();
    public final EventoRepository eventos = new EventoRepository();
    public final AtividadeRepository atividades = new AtividadeRepository();
    public final NotificacaoRepository notificacoes = new NotificacaoRepository();
    public final RelatorioRepository relatorios = new RelatorioRepository();

    public final AnimalService animalService =
        new AnimalService(animais);

    public final UsuarioService usuarioService =
        new UsuarioService(usuarios);

    public final AdocaoService adocaoService =
        new AdocaoService(adocoes);

    public final ConsultaService consultaService =
        new ConsultaService(consultas);

    public final VacinaService vacinaService =
        new VacinaService(vacinas);

    public final EventoService eventoService =
        new EventoService(eventos);

    public final AtividadeService atividadeService =
        new AtividadeService(atividades);

    public final NotificacaoService notificacaoService =
        new NotificacaoService(notificacoes);

    public final RelatorioService relatorioService =
        new RelatorioService(relatorios);

    public final CompatibilidadeService compatibilidadeService =
        new CompatibilidadeService();

    public SistemaContexto() {
        criarAdministradorInicial();
    }

    private void criarAdministradorInicial() {

        boolean existeAdministrador = false;

        for (Usuario usuario : usuarioService.listar()) {

            if (usuario instanceof Administrador) {
                existeAdministrador = true;
                break;
            }
        }

        if (!existeAdministrador) {

            Administrador administrador = new Administrador(
                "Administrador do Sistema",
                "admin@abrigo.com",
                "Admin@123",
                "88999999999",
                "Total"
            );

            usuarioService.cadastrar(administrador);
        }
    }
}

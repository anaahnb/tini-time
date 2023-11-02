package Controller;
import Model.FilmeAssistido;

import java.util.ArrayList;
import java.util.List;

public class FilmeAssistidoController {
    private final List<FilmeAssistido> filmesAssistidos = new ArrayList<>();

    public void adicionarFilmeAssistido(FilmeAssistido filmeAssistido) {
        filmesAssistidos.add(filmeAssistido);
    }

    public List<FilmeAssistido> listarFilmesAssistidos() {
        return filmesAssistidos;
    }

    public boolean atualizarFilmeAssistido(FilmeAssistido novoFilmeAssistido) {
        int id = novoFilmeAssistido.getId();

        for (int i = 0; i < filmesAssistidos.size(); i++) {
            FilmeAssistido filmeAssistido = filmesAssistidos.get(i);

            if (filmeAssistido.getId() == id) {
                filmesAssistidos.set(i, novoFilmeAssistido);
                return true;
            }
        }

        return false;
    }

    public void excluirFilmeAssistido(int id) {
        FilmeAssistido filmeAssistidoParaExcluir = null;

        for (FilmeAssistido filmeAssistido : filmesAssistidos) {
            if (filmeAssistido.getId() == id) {
                filmeAssistidoParaExcluir = filmeAssistido;
                break;
            }
        }

        if (filmeAssistidoParaExcluir != null) {
            filmesAssistidos.remove(filmeAssistidoParaExcluir);
        } else {
            System.out.println("Filme assistido com ID " + id + " não encontrado.");
        }
    }



}

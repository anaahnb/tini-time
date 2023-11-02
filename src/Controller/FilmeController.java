package Controller;

import Model.Filme;

import java.util.ArrayList;
import java.util.List;


public class FilmeController {
    private final List<Filme> filmes = new ArrayList<>();
    private int proximoId = 1;
    public void adicionarFilme(Filme filme) {
        // Verifica se o filme já possui um ID
        if (filme.getId() == 0) {
            // Se não possui ID, atribui o próximo ID disponível e, em seguida, incrementa o próximo ID
            filme.setId(proximoId);
            proximoId++;
        }
        filmes.add(filme);
    }

    public Filme buscarFilmePorId(int id) {
        for (Filme filme : filmes) {
            if (filme.getId() == id) {
                return filme;
            }
        }
        return null; // Retorna null se o filme com o ID especificado não for encontrado
    }


    public List<Filme> listarFilmes() {
        return filmes;
    }

    public void atualizarFilme(int id, String novoTitulo, String novoDiretor, int novoAnoLancamento) {
        Filme filmeParaAtualizar = null;
        for (Filme filme : filmes) {
            if (filme.getId() == id) {
                filmeParaAtualizar = filme;
                break;
            }
        }

        if (filmeParaAtualizar != null) {
            filmeParaAtualizar.setTitulo(novoTitulo);
            filmeParaAtualizar.setDiretor(novoDiretor);
            filmeParaAtualizar.setAnoLancamento(novoAnoLancamento);
        } else {
            System.out.println("Filme com ID " + id + " não encontrado.");
        }
    }

    public int proximoId() {
        return proximoId;
    }

    public void excluirFilme(int id) {
        Filme filmeParaExcluir = null;

        for (Filme filme : filmes) {
            if (filme.getId() == id) {
                filmeParaExcluir = filme;
                break;
            }
        }

        if (filmeParaExcluir != null) {
            filmes.remove(filmeParaExcluir);
        } else {
            System.out.println("Filme com ID " + id + " não encontrado.");
        }
    }


}


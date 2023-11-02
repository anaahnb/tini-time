package Model;

public class Filme {
    private int id;
    private String titulo;
    private String diretor;
    private int anoLancamento;

    public Filme(int id, String titulo, String diretor, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return this.diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getAnoLancamento() {
        return this.anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }


    @Override
    public String toString() {
        return "Filme{" +
                "id='" + id + '\'' +
                "titulo='" + titulo + '\'' +
                ", diretor='" + diretor + '\'' +
                ", anoLancamento=" + anoLancamento +
                '}';
    }
}


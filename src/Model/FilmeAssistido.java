package Model;
import java.util.Date;

public class FilmeAssistido extends Filme {
    private int avaliacaoUsuario;
    private Date dataAssistido;

    public FilmeAssistido(int id, String titulo, String diretor, int anoLancamento, int avaliacaoUsuario, Date dataAssistido) {
        super(id, titulo, diretor, anoLancamento);
        this.avaliacaoUsuario = avaliacaoUsuario;
        this.dataAssistido = dataAssistido;
    }

    public int getAvaliacaoUsuario() {
        return avaliacaoUsuario;
    }

    public void setAvaliacaoUsuario(int avaliacaoUsuario) {
        this.avaliacaoUsuario = avaliacaoUsuario;
    }

    public Date getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido(Date dataAssistido) {
        this.dataAssistido = dataAssistido;
    }

    public String getAvaliacaoEmEstrelas() {
        StringBuilder estrelas = new StringBuilder();
        estrelas.append("★".repeat(Math.max(0, avaliacaoUsuario)));
        return estrelas.toString();
    }
}

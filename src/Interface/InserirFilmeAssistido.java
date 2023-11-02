package Interface;

import Controller.FilmeAssistidoController;
import Controller.FilmeController;
import Model.Filme;
import Model.FilmeAssistido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class InserirFilmeAssistido extends JFrame {
    private FilmeAssistidoController filmeAssistidoController;
    private FilmeController filmeController;

    private JComboBox<Filme> listaFilmesCadastrados; // Use JComboBox<Filme> em vez de JComboBox<String>
    private JTextField campoAvaliacao;
    private JTextField campoDataAssistido;
    private JButton botaoInserir;

    public InserirFilmeAssistido(FilmeAssistidoController filmeAssistidoController, FilmeController filmeController) {
        this.filmeAssistidoController = filmeAssistidoController;
        this.filmeController = filmeController;
        setTitle("Inserir Filme Assistido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        JLabel rotuloFilme = new JLabel("Escolha um filme cadastrado:");
        listaFilmesCadastrados = new JComboBox<>();
        listaFilmesCadastrados.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Filme) {
                    value = ((Filme) value).getTitulo();
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        List<Filme> filmesCadastrados = filmeController.listarFilmes();
        for (Filme filme : filmesCadastrados) {
            listaFilmesCadastrados.addItem(filme);
        }

        JLabel rotuloAvaliacao = new JLabel("Avaliação (0-5):");
        campoAvaliacao = new JTextField(5);

        JLabel rotuloDataAssistido = new JLabel("Data Assistido (dd/MM/yyyy):");
        campoDataAssistido = new JTextField(10);

        botaoInserir = new JButton("Salvar filme assistido");

        c.gridx = 0;
        c.gridy = 0;
        painel.add(rotuloFilme, c);
        c.gridy = 1;
        painel.add(listaFilmesCadastrados, c);
        c.gridy = 2;
        painel.add(rotuloAvaliacao, c);
        c.gridy = 3;
        painel.add(campoAvaliacao, c);
        c.gridy = 4;
        painel.add(rotuloDataAssistido, c);
        c.gridy = 5;
        painel.add(campoDataAssistido, c);

        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 6;
        painel.add(botaoInserir, c);

        add(painel);

        botaoInserir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirFilmeAssistido(filmeAssistidoController, filmeController);
            }
        });
    }

    private void inserirFilmeAssistido(FilmeAssistidoController filmeAssistidoController, FilmeController filmeController) {
        int indexSelecionado = listaFilmesCadastrados.getSelectedIndex();

        if (indexSelecionado != -1) {
            Filme filmeSelecionado = (Filme) listaFilmesCadastrados.getSelectedItem(); // Obtenha o filme selecionado

            String avaliacaoTexto = campoAvaliacao.getText();
            String dataAssistidoTexto = campoDataAssistido.getText();

            try {
                int avaliacao = Integer.parseInt(avaliacaoTexto);

                if (avaliacao < 0 || avaliacao > 5) {
                    JOptionPane.showMessageDialog(this, "A avaliação deve estar no intervalo de 0 a 5.");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataAssistido = sdf.parse(dataAssistidoTexto);

                    FilmeAssistido filmeAssistido = new FilmeAssistido(
                            filmeSelecionado.getId(), // Forneça o ID do filme associado
                            filmeSelecionado.getTitulo(),
                            filmeSelecionado.getDiretor(),
                            filmeSelecionado.getAnoLancamento(),
                            avaliacao,
                            dataAssistido
                    );

                    filmeAssistidoController.adicionarFilmeAssistido(filmeAssistido);

                    JOptionPane.showMessageDialog(this, "Filme adicionado como 'ASSISTIDO' com sucesso!");
                    dispose();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Avaliação deve ser um número inteiro.");
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Data de assistido inválida. Use o formato dd/MM/yyyy.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um filme na lista.");
        }
    }
}

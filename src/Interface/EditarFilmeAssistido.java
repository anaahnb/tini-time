package Interface;

import Controller.FilmeAssistidoController;
import Controller.FilmeController;
import Model.FilmeAssistido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarFilmeAssistido extends JFrame {
    private JTextField campoTitulo;
    private JTextField campoAvaliacao;
    private FilmeAssistidoController filmeAssistidoController;
    private FilmeController filmeController;
    private FilmeAssistido filmeAssistido;

    public EditarFilmeAssistido(FilmeAssistidoController filmeAssistidoController, FilmeController filmeController, FilmeAssistido filmeAssistido) {
        this.filmeAssistidoController = filmeAssistidoController;
        this.filmeController = filmeController;
        this.filmeAssistido = filmeAssistido;

        setTitle("Editar Filme Assistido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        JLabel rotuloTitulo = new JLabel("Título:");
        campoTitulo = new JTextField(15);

        JLabel rotuloAvaliacao = new JLabel("Avaliação:");
        campoAvaliacao = new JTextField(15);

        campoTitulo.setText(filmeAssistido.getTitulo());
        campoAvaliacao.setText(String.valueOf(filmeAssistido.getAvaliacaoUsuario()));

        JButton botaoSalvar = new JButton("Salvar");

        c.gridx = 0;
        c.gridy = 0;
        painel.add(rotuloTitulo, c);
        c.gridy = 1;
        painel.add(campoTitulo, c);
        c.gridy = 2;
        painel.add(rotuloAvaliacao, c);
        c.gridy = 3;
        painel.add(campoAvaliacao, c);

        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 4;
        painel.add(botaoSalvar, c);

        add(painel);

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = campoTitulo.getText();
                int avaliacao = Integer.parseInt(campoAvaliacao.getText());

                filmeAssistido.setTitulo(titulo);
                filmeAssistido.setAvaliacaoUsuario(avaliacao);
                filmeAssistidoController.atualizarFilmeAssistido(filmeAssistido);

                JOptionPane.showMessageDialog(EditarFilmeAssistido.this, "Filme assistido atualizado com sucesso!");
                dispose();
            }
        });
    }
}

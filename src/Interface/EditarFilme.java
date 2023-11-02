package Interface;

import Controller.FilmeController;
import Model.Filme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarFilme extends JFrame {
    private final JTextField campoTitulo;
    private final JTextField campoDiretor;
    private final JTextField campoAno;
    private final FilmeController filmeController;
    private final Filme filmeParaEditar;

    public EditarFilme(FilmeController filmeController, Filme filme) {
        this.filmeController = filmeController;
        this.filmeParaEditar = filme;

        setTitle("Editar Filme");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        JLabel rotuloTitulo = new JLabel("Título:");
        campoTitulo = new JTextField(15);

        JLabel rotuloDiretor = new JLabel("Diretor:");
        campoDiretor = new JTextField(15);

        JLabel rotuloAno = new JLabel("Ano de Lançamento:");
        campoAno = new JTextField(15);

        campoTitulo.setText(filme.getTitulo());
        campoDiretor.setText(filme.getDiretor());
        campoAno.setText(String.valueOf(filme.getAnoLancamento()));

        JButton botaoSalvar = new JButton("Salvar");

        c.gridx = 0;
        c.gridy = 0;
        painel.add(rotuloTitulo, c);
        c.gridy = 1;
        painel.add(campoTitulo, c);
        c.gridy = 2;
        painel.add(rotuloDiretor, c);
        c.gridy = 3;
        painel.add(campoDiretor, c);
        c.gridy = 4;
        painel.add(rotuloAno, c);
        c.gridy = 5;
        painel.add(campoAno, c);

        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 6;
        painel.add(botaoSalvar, c);

        add(painel);

        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = campoTitulo.getText();
                String diretor = campoDiretor.getText();
                int ano = Integer.parseInt(campoAno.getText());

                filmeParaEditar.setTitulo(titulo);
                filmeParaEditar.setDiretor(diretor);
                filmeParaEditar.setAnoLancamento(ano);

                // Obtenha o ID do filme que está sendo editado (você precisa implementar essa lógica)
                int idDoFilme = filmeParaEditar.getId();

                // Chame a função atualizarFilme com o ID
                filmeController.atualizarFilme(idDoFilme, titulo, diretor, ano);

                JOptionPane.showMessageDialog(Interface.EditarFilme.this, "Filme atualizado com sucesso!");
                dispose();
            }
        });
    }
}


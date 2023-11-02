package Interface;

import Controller.FilmeController;
import Model.Filme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserirFilme extends JFrame {
    private final JTextField campoTitulo;
    private final JTextField campoDiretor;
    private final JTextField campoAno;
    private final FilmeController filmeController;

    public InserirFilme(FilmeController filmeController) {
        this.filmeController = filmeController;

        setTitle("Cadastro de Filme");
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
        campoAno = new JTextField(5);


        JButton botaoSalvar = new JButton("Salvar filme");

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
                int id = filmeController.proximoId();

                Filme filme = new Filme(id, titulo, diretor, ano);
                filmeController.adicionarFilme(filme);

                JOptionPane.showMessageDialog(InserirFilme.this, "Filme salvo com sucesso!");
                dispose();
            }
        });
    }
}

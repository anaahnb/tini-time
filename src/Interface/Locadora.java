package Interface;

import Controller.FilmeAssistidoController;
import Controller.FilmeController;
import Model.Filme;
import Model.FilmeAssistido;
import Model.Seeder;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Locadora extends JFrame {
    private final FilmeController filmeController = new FilmeController();
    private final FilmeAssistidoController filmeAssistidoController = new FilmeAssistidoController();
    private final DefaultTableModel filmesTableModel = new DefaultTableModel();
    private final DefaultTableModel filmesAssistidosTableModel = new DefaultTableModel();
    private JTable tabelaFilmes;
    private JTable tabelaFilmesAssistidos;

    public Locadora() {
        setTitle("Tini Time - Locadora");
        setBounds(300, 90, 900, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        criarTabelaFilmes();
        criarTabelaFilmesAssistidos();
        adicionarBotoes();

        tabbedPane.addTab("Explorar filmes", new JScrollPane(tabelaFilmes));
        tabbedPane.addTab("Filmes assistidos", new JScrollPane(tabelaFilmesAssistidos));

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void criarTabelaFilmes() {
        filmesTableModel.addColumn("Título");
        filmesTableModel.addColumn("Diretor");
        filmesTableModel.addColumn("Ano");
        filmesTableModel.addColumn("");
        filmesTableModel.addColumn("");


        tabelaFilmes = new JTable(filmesTableModel);

        tabelaFilmes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int colunaClicada = tabelaFilmes.columnAtPoint(e.getPoint());
                int linhaClicada = tabelaFilmes.rowAtPoint(e.getPoint());

                if (colunaClicada == 3) {
                    if (linhaClicada >= 0) {
                        int filmeId = Integer.parseInt(filmesTableModel.getValueAt(linhaClicada, 3).toString());
                        Filme filmeSelecionado = filmeController.buscarFilmePorId(filmeId);
                        editarFilme(filmeSelecionado);
                    }
                }
            }
        });

        tabelaFilmes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int colunaClicada = tabelaFilmes.columnAtPoint(e.getPoint());
                int linhaClicada = tabelaFilmes.rowAtPoint(e.getPoint());

                if (colunaClicada == 4) {
                    if (linhaClicada >= 0) {
                        int filmeId = Integer.parseInt(filmesTableModel.getValueAt(linhaClicada, 3).toString());
                        filmeController.excluirFilme(filmeId); // Chama o método de exclusão
                        List<Filme> filmesCadastrados = filmeController.listarFilmes();
                        atualizarTabelaFilmes(filmesCadastrados);
                    }
                }
            }
        });


        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column == 3) {
                    JButton botaoEditar = new JButton("✎");
                    botaoEditar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int filmeId = Integer.parseInt(e.getActionCommand());
                            Filme filmeSelecionado = filmeController.buscarFilmePorId(filmeId);
                            editarFilme(filmeSelecionado);
                        }
                    });
                    return botaoEditar;
                } else if (column == 4) {
                    JButton botaoExcluir = new JButton("🗑️");
                    botaoExcluir.setActionCommand(String.valueOf(row));
                    botaoExcluir.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int filmeId = Integer.parseInt(e.getActionCommand());
                            filmeController.excluirFilme(filmeId); // Chama o método de exclusão
                        }
                    });
                    return botaoExcluir;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };



        tabelaFilmes.getColumnModel().getColumn(3).setMinWidth(50);
        tabelaFilmes.getColumnModel().getColumn(3).setMaxWidth(50);
        tabelaFilmes.getColumnModel().getColumn(3).setCellRenderer(renderer);
        tabelaFilmes.getColumnModel().getColumn(4).setMinWidth(50);
        tabelaFilmes.getColumnModel().getColumn(4).setMaxWidth(50);
        tabelaFilmes.getColumnModel().getColumn(4).setCellRenderer(renderer);

    }

    private void criarTabelaFilmesAssistidos() {
        filmesAssistidosTableModel.addColumn("Título");
        filmesAssistidosTableModel.addColumn("Avaliação");
        filmesAssistidosTableModel.addColumn("Assistido em");
        filmesAssistidosTableModel.addColumn("");

        tabelaFilmesAssistidos = new JTable(filmesAssistidosTableModel);

        tabelaFilmesAssistidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int colunaClicada = tabelaFilmesAssistidos.columnAtPoint(e.getPoint());
                int linhaClicada = tabelaFilmesAssistidos.rowAtPoint(e.getPoint());

                if (colunaClicada == 3) {
                    if (linhaClicada >= 0) {
                        FilmeAssistido filmeAssistidoSelecionado = filmeAssistidoController.listarFilmesAssistidos().get(linhaClicada);
                        editarFilmeAssistido(filmeAssistidoSelecionado);
                    }
                }
            }
        });

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (column == 3) {
                    JButton botaoEditar = new JButton("✎");
                    botaoEditar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int filmeAssistidoId = Integer.parseInt(e.getActionCommand());
                            FilmeAssistido filmeAssistidoSelecionado = filmeAssistidoController.listarFilmesAssistidos().get(filmeAssistidoId);
                            editarFilmeAssistido(filmeAssistidoSelecionado);
                        }
                    });
                    return botaoEditar;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        tabelaFilmesAssistidos.getColumnModel().getColumn(3).setMinWidth(50);
        tabelaFilmesAssistidos.getColumnModel().getColumn(3).setMaxWidth(50);
        tabelaFilmesAssistidos.getColumnModel().getColumn(3).setCellRenderer(renderer);
    }

    private void adicionarBotoes() {
        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton botaoListarFilmes = new JButton("Listar Filmes Cadastrados");
        JButton botaoListarFilmesAssistidos = new JButton("Listar Filmes Assistidos");
        JButton botaoAdicionarFilme = new JButton("Adicionar Filme");
        JButton botaoAdicionarFilmeAssistido = new JButton("Adicionar Filme Assistido");

        botaoListarFilmes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Filme> filmesCadastrados = filmeController.listarFilmes();
                atualizarTabelaFilmes(filmesCadastrados);
            }
        });

        botaoListarFilmesAssistidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<FilmeAssistido> filmesAssistidos = filmeAssistidoController.listarFilmesAssistidos();
                atualizarTabelaFilmesAssistidos(filmesAssistidos);
            }
        });

        botaoAdicionarFilme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InserirFilme inserirFilme = new InserirFilme(filmeController);
                inserirFilme.setVisible(true);
            }
        });

        botaoAdicionarFilmeAssistido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InserirFilmeAssistido inserirFilmeAssistido = new InserirFilmeAssistido(filmeAssistidoController, filmeController);
                inserirFilmeAssistido.setVisible(true);
            }
        });

        painelBotoes.add(botaoListarFilmes);
        painelBotoes.add(botaoListarFilmesAssistidos);
        painelBotoes.add(botaoAdicionarFilme);
        painelBotoes.add(botaoAdicionarFilmeAssistido);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void atualizarTabelaFilmes(List<Filme> filmes) {
        filmesTableModel.setRowCount(0);

        for (Filme filme : filmes) {
            filmesTableModel.addRow(new Object[]{filme.getTitulo(), filme.getDiretor(), filme.getAnoLancamento(), filme.getId()});
        }
    }

    private void editarFilme(Filme filme) {
        EditarFilme editarFilme = new EditarFilme(filmeController, filme);
        editarFilme.setVisible(true);
    }

    private void editarFilmeAssistido(FilmeAssistido filmeAssistido) {
        EditarFilmeAssistido editarFilmeAssistido = new EditarFilmeAssistido(filmeAssistidoController, filmeController, filmeAssistido);
        editarFilmeAssistido.setVisible(true);
    }

    private void atualizarTabelaFilmesAssistidos(List<FilmeAssistido> filmesAssistidos) {
        filmesAssistidosTableModel.setRowCount(0);
        for (FilmeAssistido filme : filmesAssistidos) {
            filmesAssistidosTableModel.addRow(new Object[]{filme.getTitulo(), filme.getAvaliacaoEmEstrelas(), filme.getDataAssistido()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Locadora app = new Locadora();
            Seeder.carregarFilmes(app.filmeController);
            app.setVisible(true);
        });
    }
}
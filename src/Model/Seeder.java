    package Model;

    import Controller.FilmeController;

    public class Seeder {
        public static void carregarFilmes(FilmeController filmeController) {
            Filme[] filmes = {
                    new Filme(1, "No Hard Feelings", "Gene Stupnitsky", 2023),
                    new Filme(2, "Tudo em Todo o Lugar ao Mesmo Tempo", "Daniel Scheinert, Daniel Kwan", 2022),
                    new Filme(3, "Carros", "john Lasseter", 2006),
                    new Filme(4, "A Ilha dos Cachorros", "Wes Anderson", 2018),
                    new Filme(5, "Aftersun", "Charlotte Wells", 2022),
            };

            for (Filme filme : filmes) {
                filmeController.adicionarFilme(filme);
            }
        }

    }

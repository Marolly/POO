
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Musica {
    private String titulo;
    private String artista;
    private double duracao;

    public Musica(String titulo, String artista, double duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public double getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Artista: " + artista + ", Duração: " + duracao + "min";
    }
}

class Playlist {
    private String nome;
    private List<Musica> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    public void exibirMusicas() {
        System.out.println("Playlist: " + nome);
        for (Musica musica : musicas) {
            System.out.println(musica);
        }
    }

    public String getNome() {
        return nome;
    }
}


class Usuario {
    private String nome;
    private List<Playlist> playlists;

    public Usuario(String nome) {
        this.nome = nome;
        this.playlists = new ArrayList<>();
    }

    public void criarPlaylist(String nome) {
        playlists.add(new Playlist(nome));
        System.out.println("Playlist '" + nome + "' criada com sucesso!");
    }

    public void adicionarMusicaAPlaylist(int indicePlaylist, Musica musica) {
        if (indicePlaylist >= 0 && indicePlaylist < playlists.size()) {
            Playlist playlist = playlists.get(indicePlaylist);
            playlist.adicionarMusica(musica);
            System.out.println("Música adicionada à playlist '" + playlist.getNome() + "'.");
        } else {
            System.out.println("Número de playlist inválido.");
        }
    }

    public void exibirPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist disponível.");
        } else {
            System.out.println("Playlists disponíveis:");
            for (int i = 0; i < playlists.size(); i++) {
                System.out.println((i + 1) + " - " + playlists.get(i).getNome());
            }
        }
    }

    public int numeroDePlaylists() {
        return playlists.size();
    }

    public String getNomePlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice).getNome();
        }
        return null;
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario("Usuário");
        int opcao;

        do {
            System.out.println("\n=== Menu de Opções ===");
            System.out.println("1. Criar Playlist");
            System.out.println("2. Adicionar Música à Playlist");
            System.out.println("3. Exibir Playlists");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome da nova playlist: ");
                    String nomePlaylist = scanner.nextLine();
                    usuario.criarPlaylist(nomePlaylist);
                    break;

                case 2:
                    if (usuario.numeroDePlaylists() == 0) {
                        System.out.println("Nenhuma playlist disponível para adicionar músicas.");
                    } else {
                        usuario.exibirPlaylists();
                        System.out.print("Selecione a playlist que deseja adicionar a música: ");
                        int indicePlaylist = scanner.nextInt() - 1;
                        scanner.nextLine();
                        System.out.print("Digite o título da música: ");
                        String tituloMusica = scanner.nextLine();
                        System.out.print("Digite o artista da música: ");
                        String artistaMusica = scanner.nextLine();
                        System.out.print("Digite a duração da música (em minutos): ");
                        double duracaoMusica = scanner.nextDouble();
                        scanner.nextLine();
                        Musica novaMusica = new Musica(tituloMusica, artistaMusica, duracaoMusica);
                        usuario.adicionarMusicaAPlaylist(indicePlaylist, novaMusica);
                    }
                    break;

                case 3:
                    usuario.exibirPlaylists();
                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}

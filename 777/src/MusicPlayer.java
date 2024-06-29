import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class MusicPlayer {
    private ArrayList<ArrayList<String>> playlists;
    private ArrayList<String> songs;
    private int currentSongIndex;

    public MusicPlayer() {
        this.playlists = new ArrayList<>();
        this.songs = new ArrayList<>();
        this.currentSongIndex = -1;
    }

    public void showMenu() {
        System.out.println("0 - выйти");
        System.out.println("1 - показать список песен");
        System.out.println("2 - создать плейлист");
        System.out.println("3 - включить плейлист");
        System.out.println("4 - сохранить плейлист");
        System.out.println("5 - удалить плейлист");
        System.out.println("6 - добавить песню в плейлист");
        System.out.println("7 - показать весь плейлист");
        System.out.println("8 - убрать песню из плейлиста");
    }

    public void showSongs() {
        if (songs.isEmpty()) {
            System.out.println("Список песен пуст");
        } else {
            System.out.println("Список песен:");
            for (int i = 0; i < songs.size(); i++) {
                System.out.println((i + 1) + ". " + songs.get(i));
            }
        }
    }

    public void createPlaylist() {
        playlists.add(new ArrayList<>());
        System.out.println("Плейлист создан с номером: " + (playlists.size() - 1));
    }

    public void playPlaylist(int playlistIndex) {
        if (playlistIndex >= 0 && playlistIndex < playlists.size()) {
            ArrayList<String> playlist = playlists.get(playlistIndex);
            if (!playlist.isEmpty()) {
                currentSongIndex = 0;
                System.out.println("Воспроизводится плейлист " + playlistIndex);
                System.out.println("Текущая песня: " + playlist.get(currentSongIndex));
            } else {
                System.out.println("Плейлист пуст");
            }
        } else {
            System.out.println("Плейлист с указанным номером не существует");
        }
    }

    public void savePlaylist(int playlistIndex) {
        if (playlistIndex >= 0 && playlistIndex < playlists.size()) {
            ArrayList<String> playlist = playlists.get(playlistIndex);
            try (PrintWriter out = new PrintWriter("playlist_" + playlistIndex + ".txt")) {
                for (String song : playlist) {
                    out.println(song);
                }
                System.out.println("Плейлист сохранен");
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка при сохранении плейлиста");
            }
        } else {
            System.out.println("Плейлист с указанным номером не существует");
        }
    }

    public void deletePlaylist(int playlistIndex) {
        if (playlistIndex >= 0 && playlistIndex < playlists.size()) {
            playlists.remove(playlistIndex);
            System.out.println("Плейлист удален");
        } else {
            System.out.println("Плейлист с указанным номером не существует");
        }
    }

    public void addSongToPlaylist(int playlistIndex, String songName, String artist) {
        if (playlistIndex >= 0 && playlistIndex < playlists.size()) {
            String newSong = songName + " - " + artist;
            ArrayList<String> playlist = playlists.get(playlistIndex);
            playlist.add(newSong);
            System.out.println("Песня добавлена в плейлист " + playlistIndex);
        } else {
            System.out.println("Плейлист с указанным номером не существует");
        }
    }

    public void showPlaylist(int playlistIndex) {
        if (playlistIndex >= 0 && playlistIndex < playlists.size()) {
            ArrayList<String> playlist = playlists.get(playlistIndex);
            if (!playlist.isEmpty()) {
                System.out.println("Плейлист " + playlistIndex + ":");
                for (int i = 0; i < playlist.size(); i++) {
                    System.out.println((i + 1) + ". " + playlist.get(i));
                }
            } else {
                System.out.println("Плейлист пуст");
            }
        } else {
            System.out.println("Плейлист с указанным номером не существует");
        }
    }

    public void removeSongFromPlaylist(int playlistIndex, int songIndex) {
        if (playlistIndex >= 0 && playlistIndex < playlists.size()) {
            ArrayList<String> playlist = playlists.get(playlistIndex);
            if (songIndex >= 1 && songIndex <= playlist.size()) {
                playlist.remove(songIndex - 1);
                System.out.println("Песня удалена из плейлиста " + playlistIndex);
            } else {
                System.out.println("Некорректный номер песни");
            }
        } else {
            System.out.println("Плейлист с указанным номером не существует");
        }
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            System.out.print("Введите номер действия: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("До свидания!");
                    running = false;
                    break;
                case 1:
                    showSongs();
                    break;
                case 2:
                    createPlaylist();
                    break;
                case 3:
                    System.out.print("Введите номер плейлиста: ");
                    int playlistIndex = scanner.nextInt();
                    scanner.nextLine();
                    playPlaylist(playlistIndex);
                    break;
                case 4:
                    System.out.print("Введите номер плейлиста: ");
                    int playlistIndexToSave = scanner.nextInt();
                    scanner.nextLine();
                    savePlaylist(playlistIndexToSave);
                    break;
                case 5:
                    System.out.print("Введите номер плейлиста: ");
                    int playlistIndexToDelete = scanner.nextInt();
                    scanner.nextLine();
                    deletePlaylist(playlistIndexToDelete);
                    break;
                case 6:
                    System.out.print("Введите номер плейлиста: ");
                    int playlistIndexToAddSong = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите название песни: ");
                    String songName = scanner.nextLine();
                    System.out.print("Введите исполнителя: ");
                    String artist = scanner.nextLine();
                    addSongToPlaylist(playlistIndexToAddSong, songName, artist);
                    break;
                case 7:
                    System.out.print("Введите номер плейлиста: ");
                    int playlistIndexToShow = scanner.nextInt();
                    scanner.nextLine();
                    showPlaylist(playlistIndexToShow);
                    break;
                case 8:
                    System.out.print("Введите номер плейлиста: ");
                    int playlistIndexToRemoveSong = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введите номер песни: ");
                    int songIndex = scanner.nextInt();
                    scanner.nextLine();
                    removeSongFromPlaylist(playlistIndexToRemoveSong, songIndex);
                    break;
                default:
                    System.out.println("Некорректное действие");
            }
        }
    }

    public static void main(String[] args) {
        MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.run();
    }
}

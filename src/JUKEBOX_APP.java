import entities.Playlist;
import entities.Song;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JUKEBOX_APP {
    public static void main(String[] args) {
        if (args.length != 1) throw new RuntimeException(); // we expect the input filename
        run(args[0]);
    }

    private static void run(String inputFilePath) {
        Configuration conf = new Configuration();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;

                List<String> tokens = Arrays.asList(line.split(" "));

                // execute services
                switch (tokens.get(0)) {
                    case "ADD_SONG" -> {
                        String songName = tokens.get(1);
                        String artistName = tokens.get(2);
                        String albumName = tokens.get(3);
                        String genre = tokens.get(4);

                        Song song = conf.getSongServices().addSong(songName, artistName, albumName, genre);
                        System.out.println(song);
                    }
                    case "LIST_SONGS" -> {
                        List<Song> songList = conf.getSongServices().listSongs();
                        System.out.print("[");
                        for (int i = 0; i < songList.size(); i++) {
                            if (i == songList.size() - 1) System.out.print(songList.get(i) + "]\n");
                            else System.out.print(songList.get(i) + ", ");
                        }
                    }
                    case "CREATE_PLAYLIST" -> {
                        String playlistName = tokens.get(1);
                        List<Long> songIdList = new ArrayList<>();
                        for (int i = 2; i < tokens.size(); i++) {
                            Long id = Long.parseLong(tokens.get(i));
                            songIdList.add(id);
                        }
                        Playlist playlist = conf.getPlaylistService().createPlaylist(playlistName, songIdList);
                        System.out.println(playlist);
                    }
                    case "LOAD_PLAYLIST" -> {
                        String playlistName = tokens.get(1);
                        conf.getPlaylistService().loadPlaylist(playlistName);
                    }
                    case "PLAY_SONG" -> conf.getPlaylistService().playSong();

                    case "NEXT_SONG" -> conf.getPlaylistService().nextSong();

                    case "PREVIOUS_SONG" -> conf.getPlaylistService().prevSong();
                    case "STOP_SONG" -> conf.getPlaylistService().stopSong();
                    case "DELETE_PLAYLIST" -> {
                        String playlistName = tokens.get(1);
                        conf.getPlaylistService().deletePlaylist(playlistName);
                    }
                    case "ADD_SONG_TO_PLAYLIST" -> {
                        String playlistName = tokens.get(1);
                        Long id = Long.valueOf(tokens.get(2));
                        conf.getPlaylistService().addSongToPlaylist(playlistName, id);
                    }
                    case "DELETE_SONG_FROM_PLAYLIST" -> {
                        String playlistName = tokens.get(1);
                        Long id = Long.valueOf(tokens.get(2));
                        conf.getPlaylistService().deleteSongFromPlaylist(playlistName, id);
                    }
                    default -> throw new RuntimeException("Invalid Command");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

package commands;

import java.util.List;

import entities.Song;
import services.SongServices;

public class AddSongCommand implements ICommand {
    private final SongServices songServices;

    public AddSongCommand(SongServices songServices) {
        this.songServices = songServices;
    }

    @Override
    public void invoke(List<String> tokens) {
        String songName = tokens.get(1);
        String artistName = tokens.get(2);
        String albumName = tokens.get(3);
        String genre = tokens.get(4);

        Song song = songServices.addSong(songName, artistName, albumName, genre);
        System.out.println(song);
    }
}

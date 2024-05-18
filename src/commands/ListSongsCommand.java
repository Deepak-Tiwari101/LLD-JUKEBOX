package commands;

import java.util.List;

import entities.Song;
import services.SongServices;

public class ListSongsCommand implements ICommand {

    private final SongServices songServices;

    public ListSongsCommand(SongServices songServices) {
        this.songServices = songServices;
    }

    @Override
    public void invoke(List<String> tokens) {
        List<Song> songList = songServices.listSongs();
        System.out.print("[");
        for (int i = 0; i < songList.size(); i++) {
            if (i == songList.size() - 1)
                System.out.print(songList.get(i) + "]\n");
            else
                System.out.print(songList.get(i) + ", ");
        }
    }

}

package commands;

import java.util.ArrayList;
import java.util.List;

import entities.Playlist;
import services.PlaylistService;

public class CreatePlaylistCommand implements ICommand {

    private final PlaylistService playlistService;

    public CreatePlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String playlistName = tokens.get(1);
        List<Long> songIdList = new ArrayList<>();
        for (int i = 2; i < tokens.size(); i++) {
            Long id = Long.parseLong(tokens.get(i));
            songIdList.add(id);
        }
        Playlist playlist = playlistService.createPlaylist(playlistName, songIdList);
        System.out.println(playlist);
    }

}

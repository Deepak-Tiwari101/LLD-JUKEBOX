package commands;

import java.util.List;
import services.PlaylistService;

public class AddSongToPlaylistCommand implements ICommand {

    private final PlaylistService playlistService;

    public AddSongToPlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String playlistName = tokens.get(1);
        Long songId = Long.valueOf(tokens.get(2));

        playlistService.addSongToPlaylist(playlistName, songId);
    }

}

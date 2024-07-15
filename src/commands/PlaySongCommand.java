package commands;

import java.util.List;

import services.PlaylistService;

public class PlaySongCommand implements ICommand {

    private final PlaylistService playlistService;

    public PlaySongCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        playlistService.playSong();
    }

}

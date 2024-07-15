package commands;

import java.util.List;

import services.PlaylistService;

public class StopSongCommand implements ICommand {

    private final PlaylistService playlistService;

    public StopSongCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        playlistService.stopSong();
    }

}

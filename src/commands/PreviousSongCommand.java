package commands;

import java.util.List;

import services.PlaylistService;

public class PreviousSongCommand implements ICommand {

    private final PlaylistService playlistService;

    public PreviousSongCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        playlistService.prevSong();
    }

}

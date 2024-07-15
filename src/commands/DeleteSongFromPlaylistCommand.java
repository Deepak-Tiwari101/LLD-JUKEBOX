package commands;

import java.util.List;

import services.PlaylistService;

public class DeleteSongFromPlaylistCommand implements ICommand {
    private final PlaylistService playlistService;

    public DeleteSongFromPlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String playlistName = tokens.get(1);
        Long songId = Long.valueOf(tokens.get(2));

        playlistService.deleteSongFromPlaylist(playlistName, songId);
    }
}

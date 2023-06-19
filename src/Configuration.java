import repositories.IPlaylistRepo;
import repositories.ISongRepo;
import repositories.PlaylistRepo;
import repositories.SongRepo;
import services.PlaylistService;
import services.SongServices;

public class Configuration {
    private final IPlaylistRepo playlistRepo = new PlaylistRepo();
    private final ISongRepo songRepo = new SongRepo();
    private final PlaylistService playlistService = new PlaylistService(playlistRepo, songRepo);
    private final SongServices songServices = new SongServices(songRepo);

    public PlaylistService getPlaylistService() {
        return playlistService;
    }
    public SongServices getSongServices() {
        return songServices;
    }
}

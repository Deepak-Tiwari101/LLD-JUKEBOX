import commands.AddSongCommand;
import commands.AddSongToPlaylistCommand;
import commands.CommandKeyword;
import commands.CommandRegistry;
import commands.CreatePlaylistCommand;
import commands.DeletePlaylistCommand;
import commands.DeleteSongFromPlaylistCommand;
import commands.ListSongsCommand;
import commands.LoadPlaylistCommand;
import commands.NextSongCommand;
import commands.PlaySongCommand;
import commands.PreviousSongCommand;
import commands.StopSongCommand;
import repositories.IPlaylistRepo;
import repositories.ISongRepo;
import repositories.PlaylistRepo;
import repositories.SongRepo;
import services.PlaylistService;
import services.SongServices;

public class Configuration {
    // create an object of Single Configuration Object
    private static Configuration instance = new Configuration();

    // make the constructor private so that this class cannot be instantiated
    private Configuration() {
    }

    // Get the only object available
    public static Configuration getInstance() {
        return instance;
    }

    // initialize repositories
    private ISongRepo songRepository = new SongRepo();
    private IPlaylistRepo playlistRepository = new PlaylistRepo();

    // initialize services
    private SongServices songService = new SongServices(songRepository);
    private PlaylistService playlistService = new PlaylistService(playlistRepository, songRepository);

    // initialize commands
    private AddSongCommand addSongCommand = new AddSongCommand(songService);
    private AddSongToPlaylistCommand addSongToPlaylistCommand = new AddSongToPlaylistCommand(playlistService);
    private CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private DeleteSongFromPlaylistCommand deleteSongFromPlaylistCommand = new DeleteSongFromPlaylistCommand(
            playlistService);
    private ListSongsCommand listSongsCommand = new ListSongsCommand(songService);
    private LoadPlaylistCommand loadPlaylistCommand = new LoadPlaylistCommand(playlistService);
    private NextSongCommand nextSongCommand = new NextSongCommand(playlistService);
    private PlaySongCommand playSongCommand = new PlaySongCommand(playlistService);
    private PreviousSongCommand previousSongCommand = new PreviousSongCommand(playlistService);
    private StopSongCommand stopSongCommand = new StopSongCommand(playlistService);

    // Initialize command registery
    private final CommandRegistry commandRegistry = new CommandRegistry();

    // Register Commands
    private void registerCommands() {
        commandRegistry.registerCommand(CommandKeyword.ADD_SONG_COMMAND.getName(), addSongCommand);
        commandRegistry.registerCommand(CommandKeyword.ADD_SONG_TO_PLAYLIST_COMMAND.getName(),
                addSongToPlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.CREATE_PLAYLIST_COMMAND.getName(), createPlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.DELETE_PLAYLIST_COMMAND.getName(), deletePlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.DELETE_SONG_FROM_PLAYLIST_COMMAND.getName(),
                deleteSongFromPlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.LIST_SONGS_COMMAND.getName(), listSongsCommand);
        commandRegistry.registerCommand(CommandKeyword.LOAD_PLAYLIST_COMMAND.getName(), loadPlaylistCommand);
        commandRegistry.registerCommand(CommandKeyword.NEXT_SONG_COMMAND.getName(), nextSongCommand);
        commandRegistry.registerCommand(CommandKeyword.PLAY_SONG_COMMAND.getName(), playSongCommand);
        commandRegistry.registerCommand(CommandKeyword.PREVIOUS_SONG_COMMAND.getName(), previousSongCommand);
        commandRegistry.registerCommand(CommandKeyword.STOP_SONG_COMMAND.getName(), stopSongCommand);
    }

    public CommandRegistry getCommandRegistry() {
        registerCommands();
        return commandRegistry;
    }
}

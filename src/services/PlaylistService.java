package services;

import entities.Playlist;
import entities.PlaylistStatus;
import entities.Song;
import entities.SongStatus;
import repositories.IPlaylistRepo;
import repositories.ISongRepo;

import java.util.ArrayList;
import java.util.List;

public class PlaylistService {
    private final IPlaylistRepo playlistRepo;
    private final ISongRepo songRepo;

    private int songNavigationIndex;

    public PlaylistService(IPlaylistRepo playlistRepo, ISongRepo songRepo) {
        this.playlistRepo = playlistRepo;
        this.songRepo = songRepo;
    }

    public Playlist createPlaylist(String playlistName, List<Long> song_ids) {
        List<Song> songList = getListOfSongsfromIdList(song_ids);
        Playlist playlist = new Playlist(playlistName, songList, PlaylistStatus.NOT_LOADED);
        return playlistRepo.save(playlist);
    }

    private List<Song> getListOfSongsfromIdList(List<Long> songIds) {
        List<Song> list = new ArrayList<>();
        for (Long id : songIds)
            list.add(songRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Song with id: " + id + " not found!")));
        return list;
    }

    public void addSongToPlaylist(String playlistName, Long songId) {
        Song song = songRepo.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song with id: " + songId + " not found!"));
        Playlist playlist = playlistRepo.findByName(playlistName)
                .orElseThrow(() -> new RuntimeException("Playlist with name: " + playlistName + " not found!"));
        playlist.addSong(song);
    }

    public void deleteSongFromPlaylist(String playlistName, Long songId) {
        Song song = songRepo.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song with id: " + songId + " not found!"));
        Playlist playlist = playlistRepo.findByName(playlistName)
                .orElseThrow(() -> new RuntimeException("Playlist with name: " + playlistName + " not found!"));
        playlist.removeSong(song);
    }

    public void deletePlaylist(String playlistName) {
        Playlist playlist = playlistRepo.findByName(playlistName)
                .orElseThrow(() -> new RuntimeException("Playlist with name: \" + playlistName + \" not found!\""));
        playlistRepo.delete(playlist);
        System.out.println("Playlist " + playlistName + " is deleted!");
    }

    public void loadPlaylist(String playlistName) {
        Playlist playlist = playlistRepo.findByName(playlistName)
                .orElseThrow(() -> new RuntimeException("Playlist with name: \" + playlistName + \" not found!\""));

        if (playlistRepo.getLoadedPlaylist() != null)
            playlist.setState(PlaylistStatus.NOT_LOADED);
        playlist.setState(PlaylistStatus.LOADED);
        playlistRepo.load(playlist);
        songNavigationIndex = 0; // for new playlist the song index resets to 0 (i.e. start of the list)

        System.out.println("Playlist " + playlistName + " is loaded!");
    }

    public void playSong() {
        Playlist playlist = playlistRepo.getLoadedPlaylist();
        List<Song> songList = playlist.getSongsList();
        Song song = songList.get(songNavigationIndex % songList.size());
        if (song.getSongStatus() == SongStatus.PLAYING) {
            song.setSongStatus(SongStatus.PAUSED);
            System.out.println(song + " is paused!");
        } else {
            song.setSongStatus(SongStatus.PLAYING);
            System.out.println(song + " is playing!");
        }
    }

    public void nextSong() {
        Playlist playlist = playlistRepo.getLoadedPlaylist();
        List<Song> songList = playlist.getSongsList();
        songList.get(songNavigationIndex % songList.size()).setSongStatus(SongStatus.STOPPED); // stop the current
                                                                                               // playing
        songNavigationIndex++;
        songList.get(songNavigationIndex % songList.size()).setSongStatus(SongStatus.PLAYING); // start the next song
        System.out.println(songList.get(songNavigationIndex % songList.size()) + " is playing!");
    }

    public void prevSong() {
        Playlist playlist = playlistRepo.getLoadedPlaylist();
        List<Song> songList = playlist.getSongsList();
        songList.get(songNavigationIndex % songList.size()).setSongStatus(SongStatus.STOPPED); // stop the current
                                                                                               // playing
        songNavigationIndex--;
        if (songNavigationIndex < 0)
            songNavigationIndex = songList.size() - 1; // to handle negative index. as soon as the index drops below 0,
                                                       // assign it as the last index of the list
        songList.get(songNavigationIndex % songList.size()).setSongStatus(SongStatus.PLAYING); // start the previous
                                                                                               // song
        System.out.println(songList.get(songNavigationIndex % songList.size()) + " is playing!");
    }

    public void stopSong() {
        Playlist playlist = playlistRepo.getLoadedPlaylist();
        List<Song> songList = playlist.getSongsList();
        songList.get(songNavigationIndex % songList.size()).setSongStatus(SongStatus.STOPPED);
        System.out.println(songList.get(songNavigationIndex % songList.size()) + " is stopped!");
    }
}

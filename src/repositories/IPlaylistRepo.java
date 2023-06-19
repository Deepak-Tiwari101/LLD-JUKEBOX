package repositories;

import entities.Playlist;

import java.util.List;
import java.util.Optional;

public interface IPlaylistRepo {
    Playlist save(Playlist playlist);
    List<Playlist> findAll();
    Optional<Playlist> findById(Long playlistId);
    Optional<Playlist> findByName(String playlistName);
    boolean playlistExists(Playlist playlist);
    Integer count();
    void delete(Playlist playlist);
    void deleteById(Long id);
    void load(Playlist playlist);
    public Playlist getLoadedPlaylist();
}

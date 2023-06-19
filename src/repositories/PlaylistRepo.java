package repositories;

import entities.Playlist;

import java.util.*;

public class PlaylistRepo implements IPlaylistRepo {
    private final Map<Long, Playlist> playlistMap;
    private Long autoIncreament = 1L;
    private Playlist loadedPlaylist;

    public Playlist getLoadedPlaylist() {
        return loadedPlaylist;
    }

    public PlaylistRepo() {
        playlistMap = new HashMap<>();
    }

    @Override
    public Playlist save(Playlist playlist) {
        Playlist p = new Playlist(autoIncreament, playlist.getPlayListName(), playlist.getSongsList(), playlist.getState());
        playlistMap.put(autoIncreament++, p);
        return p;
    }

    @Override
    public List<Playlist> findAll() {
        return new ArrayList<>(playlistMap.values());
    }

    @Override
    public Optional<Playlist> findById(Long playlistId) {
        return Optional.ofNullable(playlistMap.get(playlistId));
    }

    @Override
    public Optional<Playlist> findByName(String playlistName) {
        for (Map.Entry<Long, Playlist> entry : playlistMap.entrySet()) {
            if(entry.getValue().getPlayListName().equalsIgnoreCase(playlistName))
                return Optional.ofNullable(entry.getValue());
        }
        return Optional.empty();
    }

    @Override
    public boolean playlistExists(Playlist playlist) {
        return playlistMap.values().stream().anyMatch(value -> value.equals(playlist));
    }

    @Override
    public Integer count() {
        return playlistMap.size();
    }

    @Override
    public void delete(Playlist playlist) {
        for(Map.Entry<Long, Playlist> entry : playlistMap.entrySet()) {
            if(entry.getValue().equals(playlist)) {
                playlistMap.remove(entry.getKey());
                return;
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        for(Map.Entry<Long, Playlist> entry : playlistMap.entrySet()) {
            if(entry.getKey().equals(id)) {
                playlistMap.remove(id);
                return;
            }
        }
    }

    @Override
    public void load(Playlist playlist) {
        this.loadedPlaylist = playlist;
    }
}

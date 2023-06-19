package repositories;

import entities.Song;

import java.util.*;

public class SongRepo implements ISongRepo{

    private final Map<Long, Song> songMap;
    private Long autoIncreament = 1L;

    public SongRepo() {
        songMap = new HashMap<>();
    }

    @Override
    public Song save(Song song) {
        Song s = new Song(autoIncreament, song.getName(), song.getArtist(), song.getAlbum(), song.getGenre());
        songMap.put(autoIncreament++, s);
        return s;
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songMap.values());
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return Optional.ofNullable(songMap.get(songId));
    }

    @Override
    public Optional<Song> findBySongName(String songName) {
        for(Map.Entry<Long, Song> entry: songMap.entrySet()) {
            if(entry.getValue().getName().equalsIgnoreCase(songName)) return Optional.ofNullable(entry.getValue());
        }
        return Optional.empty(); // if no name for the song in the map matches
    }

    @Override
    public boolean songExists(Song song) {
        return songMap.values().stream().anyMatch(value -> value.equals(song));
    }

    @Override
    public Integer count() {
        return songMap.size();
    }

    @Override
    public void delete(Song song) {
        for (Map.Entry<Long, Song> entry:
             songMap.entrySet()) {
            if (entry.getValue().equals(song)) {
                songMap.remove(entry.getKey());
                return;
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        for (Map.Entry<Long, Song> entry:
                songMap.entrySet()) {
            if (entry.getKey().equals(id)) {
                songMap.remove(entry.getKey());
                return;
            }
        }
    }
}

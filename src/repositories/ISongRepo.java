package repositories;

import entities.Song;

import java.util.List;
import java.util.Optional;

public interface ISongRepo {
    Song save(Song song);
    List<Song> findAll();
    Optional<Song> findById(Long songId);
    Optional<Song> findBySongName(String songName);
    boolean songExists(Song song);
    Integer count();
    void delete(Song song);
    void deleteById(Long id);
}

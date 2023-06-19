package services;

import entities.Song;
import repositories.ISongRepo;

import java.util.List;

public class SongServices {
    private final ISongRepo songRepo;

    public SongServices(ISongRepo songRepo) {
        this.songRepo = songRepo;
    }
    public Song addSong(String songName, String artistName, String albumName, String genre) {
        Song song = new Song(songName, artistName, albumName, genre);
        return songRepo.save(song);
    }
    public List<Song> listSongs() {
        return songRepo.findAll();
    }
}

package entities;

public class Song {
    private final Long id;
    private final String name;
    private final String artist;
    private final String album;
    private final String genre;
    private SongStatus songStatus;

    public Song(String name, String artist, String album, String genre) {
        this.id = null;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.songStatus = SongStatus.NOT_PLAYING;
    }

    public Song(Long id, String name, String artist, String album, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.songStatus = SongStatus.NOT_PLAYING;
    }

    public SongStatus getSongStatus() {
        return songStatus;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Song [" +
                "id=" + id +
                ']';
    }

    public void setSongStatus(SongStatus songStatus) {
        this.songStatus = songStatus;
    }
}

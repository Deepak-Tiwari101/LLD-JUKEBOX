package entities;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private final Long id;
    private final String playListName;
    private final List<Song> songsList;
    private PlaylistStatus state;

//    private static Long id_count;

    public Playlist(String playListName, List<Song> songsList, PlaylistStatus state) {
        this.id = null;  // if there is no ID defined
        this.playListName = playListName;
        this.songsList = songsList;
        this.state = state;
    }

    public Playlist(Long id, String playListName, List<Song> songsList, PlaylistStatus state) {
        this.id = id;
        this.playListName = playListName;
        this.songsList = songsList;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getPlayListName() {
        return playListName;
    }

    public List<Song> getSongsList() {
        return songsList;
    }

    public PlaylistStatus getState() {
        return state;
    }

    public void setState(PlaylistStatus state) {
        this.state = state;
    }

    public void addSong(Song song) {
        songsList.add(song);
        // Print the message after adding the song
        printPlaylistStatus();
    }

    public void removeSong(Song song) {
        songsList.remove(song);
        // Print the message after removing the song
        printPlaylistStatus();
    }

    private void printPlaylistStatus() {
        StringBuilder songIdListStringBuilder = new StringBuilder("Playlist " + this.getPlayListName() + " is revised with [");
        int n = this.getSongsList().size() - 1;
        List<Song> songList = this.getSongsList();
        for (int i = 0; i < n; i++) {
            songIdListStringBuilder.append(songList.get(i).toString()).append(", ");
        }
        songIdListStringBuilder.append(songList.get(n).toString()).append("]");
        System.out.println(songIdListStringBuilder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist playlist)) return false;
        return Objects.equals(getId(), playlist.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Playlist [" +
                "id=" + id +
                ']';
    }
}

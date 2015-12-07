package io.wavymav.topten;

/**
 * @Artist - getters & setters for artist data
 */
public class Song {
    private String name;
    private String artist;
    private String releaseDate;

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Artist: " + getArtist() + "\n" +
                "Release Date: " + getReleaseDate() + "\n";
    }
}

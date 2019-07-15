package se.dsve.top10downloader;
/*
  __
 /\ \
 \_\ \    ____  __  __     __
 /'_` \  /',__\/\ \/\ \  /'__`\
/\ \L\ \/\__, `\ \ \_/ |/\  __/
\ \___,_\/\____/\ \___/ \ \____\
 \/__,_ /\/___/  \/__/   \/____/
       https://dsve.se/
*/

/**
 * @author Lars Strömberg
 * @version 1.0
 * @since 2019-07-06
 * https://github.com/deskavaenkelt/
 */
public class MusicFeedEntry {

    private String name;
    private String artist;
    private String releaseDate;
    private String category;
    private String imageURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "name=" + name + '\n' +
                ", artist=" + artist + '\n' +
                ", releaseDate=" + releaseDate + '\n' +
                ", imageURL=" + imageURL + '\n';
    }
}

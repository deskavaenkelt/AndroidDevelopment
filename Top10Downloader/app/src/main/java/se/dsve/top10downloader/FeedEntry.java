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
 * https://github.com/deskavaenkelt/AndroidDevelopment/tree/master/Top10Downloader
 */

public class FeedEntry {

    private String name;
    private String artist;
    private String releaseDate;
    private String summary;
    private String imageURL;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getArtist() {
        return artist;
    }

    void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    String getSummary() {
        return summary;
    }

    void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageURL() {
        return imageURL;
    }

    void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

//    @Override
//    public String toString() {
//        return "name=" + name + '\n' +
//                ", artist=" + artist + '\n' +
//                ", releaseDate=" + releaseDate + '\n' +
//                ", imageURL=" + imageURL + '\n';
//    }
}

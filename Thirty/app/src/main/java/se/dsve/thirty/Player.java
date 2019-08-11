package se.dsve.thirty;
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
 *
 * @author Lars Strömberg
 * @version 1.0
 * @since 2019-07-05
 * https://github.com/deskavaenkelt/
 */
public class Player {

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int score) {
        this.score = score;
    }
}

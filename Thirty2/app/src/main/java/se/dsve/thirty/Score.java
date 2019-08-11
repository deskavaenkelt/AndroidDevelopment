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
 * @author Lars Strömberg
 * @version 1.0
 * @since 2019-08-10
 * https://github.com/deskavaenkelt/
 */
class Score {
    private int mScoreId;
    private int mScore;
    private boolean mAllowedToChange;

    Score(int scoreId, int score) {
        this.mScoreId = scoreId;
        this.mScore = score;
        this.mAllowedToChange = true;
    }

    int getScoreId() {
        return mScoreId;
    }

    int getScore() {
        return mScore;
    }

    boolean isAllowedToChange() {
        return mAllowedToChange;
    }

    void setAllowedToChange(boolean allowedToChange) {
        mAllowedToChange = allowedToChange;
    }

    void updateScore(int scoreId, int newScore) {
        this.mScoreId = scoreId;
        this.mScore = newScore;
        this.mAllowedToChange = false;
    }
}

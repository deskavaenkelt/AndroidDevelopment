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
 * @since 2019-08-04
 * https://github.com/deskavaenkelt/
 */
class Score {
    private int mScoreId;
    private int mScore;
    private boolean mAllowedToChange;

    public Score(int scoreId, int score) {
        this.mScoreId = scoreId;
        this.mScore = score;
        this.mAllowedToChange = true;
    }

    public int getScoreId() {
        return mScoreId;
    }

    public int getScore() {
        return mScore;
    }

    public boolean isAllowedToChange() {
        return mAllowedToChange;
    }

    public void setAllowedToChange(boolean allowedToChange) {
        mAllowedToChange = allowedToChange;
    }
}

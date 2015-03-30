package at.fhooe.mc.mr.microproject;

/**
 * Created by cFx on 26.03.15.
 */
public class Token {
    int id = Integer.MIN_VALUE; // id from token 0-3
    int teamId = Integer.MIN_VALUE; // teamId
    int path = Integer.MIN_VALUE; // 0 - 43

    public Token(int tokenId, int teamId) {
        this.id = tokenId;
        this.teamId = teamId;
    }

    /**
     * Adds addValue to actual path.
     *
     * @param addValue value to add to path
     * @return true if path available, false otherwise
     */
    public boolean addPathValue(int addValue) {

        if ((this.path + addValue) > 43)
            return false;
        else {
            this.path += addValue;
        }
        return true;
    }

    public void goFurther() {
        if(path > Integer.MIN_VALUE)
         this.path++;
    }

    /**
     * sets token value to Base
     */
    public void setToBase() {
        this.path = Integer.MIN_VALUE;
    }

    /**
     * sets token value to Start
     */
    public void setToStart() {
        this.path = 0;
    }

    /**
     * checks if token is in base
     * @return true if in base
     */
    public boolean isInBase() {
        return (path == Integer.MIN_VALUE ? true : false);
    }

    /**
     * @return actual path value
     */
    public int getPathValue() {
        return this.path;
    }
}

package at.fhooe.mc.mr.microproject;

import android.graphics.Color;

import at.fhooe.mc.mr.microproject.Path;

/**
 * Created by cFx on 26.03.15.
 */
public class Team {

    int id;
    Color color;
    String name;
    int score;
    int baseToken;
    Path path;
    Token[] token;

    public Team(int id) {
        this.id = id;
        path = new Path(id);
    }

}

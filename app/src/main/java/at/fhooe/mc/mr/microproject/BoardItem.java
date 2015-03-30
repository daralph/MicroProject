package at.fhooe.mc.mr.microproject;

import android.graphics.Color;
import android.graphics.Point;

/**
 * Created by cFx on 26.03.15.
 */
public class BoardItem {

    public enum Type {
        LT_BASE,
        LT_START,
        LT_FINISH,
        RT_BASE,
        RT_START,
        RT_FINISH,
        LB_BASE,
        LB_START,
        LB_FINISH,
        RB_BASE,
        RB_START,
        RB_FINISH,
        EMPTY,
        NORMAL
    }

    private Type type;
    private int team;
    private boolean occupied;
    private Token occupiedBy;
    private Point point;

    public BoardItem(int aTeam, Type aType, Point aPoint) {
        this.team = aTeam;
        this.type = aType;
        this.point = aPoint;
    }
}


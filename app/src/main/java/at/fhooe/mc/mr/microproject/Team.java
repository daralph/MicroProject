package at.fhooe.mc.mr.microproject;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by cFx on 26.03.15.
 */
public class Team {

    int id;
    int color;
    String name;
    int score;
    int baseToken;
    Path path;
    Token[] token;

    /**
     * initialises path and sets token to base
     *
     * @param id team-id
     */
    public Team(int id, String aName, int aColor) {
        this.id = id;
        this.name = aName;
        this.color = aColor;
        path = new Path(id);
        token = new Token[4];
        baseToken = 4;
        score = 0;

        token[0] = new Token(0, id);
        token[1] = new Token(1, id);
        token[2] = new Token(2, id);
        token[3] = new Token(3, id);
    }

    public int getColor() {
        return this.color;
    }

    public void drawTokens(Canvas aCanvas, Paint aPaint, float aRadius) {

        switch (baseToken) {
            case 4:
                switch (id) {
                    case 0:
                        aCanvas.drawCircle(aRadius + 2, aRadius + 2, aRadius - 2, aPaint);
                        break;
                    case 1:
                        aCanvas.drawCircle(aRadius * 19 + 2, aRadius + 2, aRadius - 2, aPaint);
                        break;
                    case 2:
                        aCanvas.drawCircle(aRadius * 19 + 2, aRadius * 19 + 2, aRadius - 2, aPaint);
                        break;
                    case 3:
                        aCanvas.drawCircle(aRadius + 2, aRadius * 19 + 2, aRadius - 2, aPaint);
                        break;
                }

            case 3:
                switch (id) {
                    case 0:
                        aCanvas.drawCircle(aRadius + 2, aRadius * 3 + 2, aRadius - 2, aPaint);
                        break;
                    case 1:
                        aCanvas.drawCircle(aRadius * 19 + 2, aRadius * 3 + 2, aRadius - 2, aPaint);
                        break;
                    case 2:
                        aCanvas.drawCircle(aRadius * 21 + 2, aRadius * 19 + 2, aRadius - 2, aPaint);
                        break;
                    case 3:
                        aCanvas.drawCircle(aRadius * 3 + 2, aRadius * 19 + 2, aRadius - 2, aPaint);
                        break;
                }

            case 2:
                switch (id) {
                    case 0:
                        aCanvas.drawCircle(aRadius * 3 + 2, aRadius + 2, aRadius - 2, aPaint);
                        break;
                    case 1:
                        aCanvas.drawCircle(aRadius * 21 + 2, aRadius + 2, aRadius - 2, aPaint);
                        break;
                    case 2:
                        aCanvas.drawCircle(aRadius * 19 + 2, aRadius * 21 + 2, aRadius - 2, aPaint);
                        break;
                    case 3:
                        aCanvas.drawCircle(aRadius + 2, aRadius * 21 + 2, aRadius - 2, aPaint);
                        break;
                }

            case 1:
                switch (id) {
                    case 0:
                        aCanvas.drawCircle(aRadius * 3 + 2, aRadius * 3 + 2, aRadius - 2, aPaint);
                        break;
                    case 1:
                        aCanvas.drawCircle(aRadius * 21 + 2, aRadius * 3 + 2, aRadius - 2, aPaint);
                        break;
                    case 2:
                        aCanvas.drawCircle(aRadius * 21 + 2, aRadius * 21 + 2, aRadius - 2, aPaint);
                        break;
                    case 3:
                        aCanvas.drawCircle(aRadius * 3 + 2, aRadius * 21 + 2, aRadius - 2, aPaint);
                        break;
                }
            case 0:
                for (int i = 0; i < token.length; i++) {
                    int val = token[i].getPathValue();
                    if (val >= 0) {
                        Point p = path.getPathPoint(val);
                        aCanvas.drawCircle(p.x * (aRadius * 2) + aRadius, p.y * (aRadius * 2) + aRadius, aRadius, aPaint);
                    }
                }
                break;
            default:
                Log.e("Team", "Wrong baseToken value ..." + baseToken);
        }

    }

    public boolean setTokenToStart() {
        boolean set = false;
        int i = 0;
        while (!set) {
            if (token[i].getPathValue() == Integer.MIN_VALUE) {
                token[i].setToStart();
                baseToken--;
                set = true;
            }
        }

        return set;
    }
}

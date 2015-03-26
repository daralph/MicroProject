package at.fhooe.mc.mr.microproject;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by cFx on 26.03.15.
 */
public class Path {
    Point[] elements;

    public Path(int teamId) {
        elements = new Point[44];

        switch (teamId) {
            case 1:
                makePathTeam1();
                break;
            case 2:
                makePathTeam2();
                break;
            case 3:
                makePathTeam3();
                break;
            case 4:
                makePathTeam4();
                break;
            default:
                Log.e("PATH", "Unknown TeamID encountered ...");
        }
    }


    private void makePathTeam1() {

        for (int i = 0; i < 44; i++)
            elements[i] = new Point();

        elements[0].set(0, 4);
        elements[1].set(1, 4);
        elements[2].set(2, 4);
        elements[3].set(3, 4);
        elements[4].set(4, 4);
        elements[5].set(4, 3);
        elements[6].set(4, 2);
        elements[7].set(4, 1);
        elements[8].set(4, 0);
        elements[9].set(5, 0);

        elements[10].set(6, 0);
        elements[11].set(6, 1);
        elements[12].set(6, 2);
        elements[13].set(6, 3);

        elements[14].set(6, 4);
        elements[15].set(7, 4);
        elements[16].set(8, 4);
        elements[17].set(9, 4);

        elements[18].set(10, 4);
        elements[19].set(10, 5);
        elements[20].set(10, 6);

        elements[21].set(9, 6);
        elements[22].set(8, 6);
        elements[23].set(7, 6);
        elements[24].set(6, 6);

        elements[25].set(6, 7);
        elements[26].set(6, 8);
        elements[27].set(6, 9);
        elements[28].set(6, 10);

        elements[29].set(5, 10);
        elements[30].set(4, 10);
        elements[31].set(4, 9);
        elements[32].set(4, 8);
        elements[33].set(4, 7);
        elements[34].set(4, 6);

        elements[35].set(3, 6);
        elements[36].set(2, 6);
        elements[37].set(1, 6);
        elements[38].set(0, 6);

        elements[39].set(0, 5);
        elements[40].set(1, 5);
        elements[41].set(2, 5);
        elements[42].set(3, 5);
        elements[43].set(4, 5);


    }

    private void makePathTeam2() {

        for (int i = 0; i < 44; i++)
            elements[i] = new Point();

        elements[0].set(6, 0);
        elements[1].set(6, 1);
        elements[2].set(6, 2);
        elements[3].set(6, 3);
        elements[4].set(6, 4);
        elements[5].set(7, 4);
        elements[6].set(8, 4);
        elements[7].set(9, 4);
        elements[8].set(10, 4);
        elements[9].set(10, 5);
        elements[10].set(10, 6);
        elements[11].set(9, 6);
        elements[12].set(8, 6);
        elements[13].set(7, 6);
        elements[14].set(6, 6);
        elements[15].set(6, 7);
        elements[16].set(6, 8);
        elements[17].set(6, 9);
        elements[18].set(6, 10);
        elements[19].set(5, 10);
        elements[20].set(4, 10);
        elements[21].set(4, 9);
        elements[22].set(4, 8);
        elements[23].set(4, 7);
        elements[24].set(4, 6);
        elements[25].set(3, 6);
        elements[26].set(2, 6);
        elements[27].set(1, 6);
        elements[28].set(0, 6);
        elements[29].set(0, 5);
        elements[30].set(0, 4);
        elements[31].set(1, 4);
        elements[32].set(2, 4);
        elements[33].set(3, 4);
        elements[34].set(4, 4);
        elements[35].set(4, 3);
        elements[36].set(4, 2);
        elements[37].set(4, 1);
        elements[38].set(4, 0);
        elements[39].set(5, 0);
        elements[40].set(5, 1);
        elements[41].set(5, 2);
        elements[42].set(5, 3);
        elements[43].set(5, 4);
    }

    private void makePathTeam3() {

        for (int i = 0; i < 44; i++)
            elements[i] = new Point();

        elements[0].set(10, 6);
        elements[1].set(9, 6);
        elements[2].set(8, 6);
        elements[3].set(7, 6);
        elements[4].set(6, 6);
        elements[5].set(6, 7);
        elements[6].set(6, 8);
        elements[7].set(6, 9);
        elements[8].set(6, 10);
        elements[9].set(5, 10);
        elements[10].set(4, 10);
        elements[11].set(4, 9);
        elements[12].set(4, 8);
        elements[13].set(4, 7);
        elements[14].set(4, 6);
        elements[15].set(3, 6);
        elements[16].set(2, 6);
        elements[17].set(1, 6);
        elements[18].set(0, 6);
        elements[19].set(0, 5);
        elements[20].set(0, 4);
        elements[21].set(1, 4);
        elements[22].set(2, 4);
        elements[23].set(3, 4);
        elements[24].set(4, 4);
        elements[25].set(4, 3);
        elements[26].set(4, 2);
        elements[27].set(4, 1);
        elements[28].set(4, 0);
        elements[29].set(5, 0);
        elements[30].set(6, 0);
        elements[31].set(6, 1);
        elements[32].set(6, 2);
        elements[33].set(6, 3);
        elements[34].set(6, 4);
        elements[35].set(7, 4);
        elements[36].set(8, 4);
        elements[37].set(9, 4);
        elements[38].set(10, 4);
        elements[39].set(10, 5);
        elements[40].set(9, 5);
        elements[41].set(8, 5);
        elements[42].set(7, 5);
        elements[43].set(6, 5);
    }

    private void makePathTeam4() {

        for (int i = 0; i < 44; i++)
            elements[i] = new Point();

        elements[0].set(4, 10);
        elements[1].set(4, 9);
        elements[2].set(4, 8);
        elements[3].set(4, 7);
        elements[4].set(4, 6);
        elements[5].set(3, 6);
        elements[6].set(2, 6);
        elements[7].set(1, 6);
        elements[8].set(0, 6);
        elements[9].set(0, 5);
        elements[10].set(0, 4);
        elements[11].set(1, 4);
        elements[12].set(2, 4);
        elements[13].set(3, 4);
        elements[14].set(4, 4);
        elements[15].set(4, 3);
        elements[16].set(4, 2);
        elements[17].set(4, 1);
        elements[18].set(4, 0);
        elements[19].set(5, 0);
        elements[20].set(6, 0);
        elements[21].set(6, 1);
        elements[22].set(6, 2);
        elements[23].set(6, 3);
        elements[24].set(6, 4);
        elements[25].set(7, 4);
        elements[26].set(8, 4);
        elements[27].set(9, 4);
        elements[28].set(10, 4);
        elements[29].set(10, 5);
        elements[30].set(10, 6);
        elements[31].set(9, 6);
        elements[32].set(8, 6);
        elements[33].set(7, 6);
        elements[34].set(6, 6);
        elements[35].set(6, 7);
        elements[36].set(6, 8);
        elements[37].set(6, 9);
        elements[38].set(6, 10);
        elements[39].set(5, 10);
        elements[40].set(5, 9);
        elements[41].set(5, 8);
        elements[42].set(5, 7);
        elements[43].set(5, 6);
    }
}

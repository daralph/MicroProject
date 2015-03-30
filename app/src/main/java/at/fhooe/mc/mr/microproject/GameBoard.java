package at.fhooe.mc.mr.microproject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import at.fhooe.mc.mr.microproject.util.SystemUiHider;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class GameBoard extends Activity implements SurfaceHolder.Callback, View.OnTouchListener, View.OnClickListener {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    // GAME
    private Team[] teams;
    private SurfaceHolder mHolder;
    private int mBoardHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gameboard);


        Button b = null;
        b = (Button)findViewById(R.id.btnGo);
        b.setOnClickListener(this);

        SurfaceView sv = (SurfaceView) findViewById(R.id.MySurfaceView);
        sv.setOnTouchListener(this);
        SurfaceHolder sh = sv.getHolder();
        sh.addCallback(this);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        // save height
        mBoardHeight = height;

        // make square
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(height, height);
        SurfaceView sv = (SurfaceView) findViewById(R.id.MySurfaceView);
        sv.setLayoutParams(params);



    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       return true;
    }

    public void initGame() throws InterruptedException {
        teams = new Team[4];
        teams[0] = new Team(0, "Team 1", Color.RED);
        teams[1] = new Team(1, "Team 2", Color.BLUE);
        teams[2] = new Team(2, "Team 3", Color.YELLOW);
        teams[3] = new Team(3, "Team 4", Color.GREEN);

        drawBoard();

        Thread.sleep(2500);
        teams[0].setTokenToStart();
        drawBoard();
        int dices = (int) Math.random() * 6 + 1;

        for (int i = 0; i < 9; i++) {

               teams[0].token[0].goFurther();
               Thread.sleep(1000);
            drawBoard();
        } // for
    }

    public void drawBoard() {

        Canvas c = mHolder.lockCanvas();
        c.drawColor(Color.WHITE);
        //set paint
        Paint p = new Paint();
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStyle(Paint.Style.FILL);

        float rad = mBoardHeight / 22;
        float durchm = rad * 2;

        // first draw base and finish of teams
        p.setColor(teams[0].getColor());
        c.drawCircle((durchm * 0) + rad, (durchm * 0) + rad, rad, p);
        c.drawCircle((durchm * 0) + rad, (durchm * 1) + rad, rad, p);
        c.drawCircle((durchm * 1) + rad, (durchm * 0) + rad, rad, p);
        c.drawCircle((durchm * 1) + rad, (durchm * 1) + rad, rad, p);
        //finish
        c.drawCircle((durchm * 1) + rad, (durchm * 5) + rad, rad, p);
        c.drawCircle((durchm * 2) + rad, (durchm * 5) + rad, rad, p);
        c.drawCircle((durchm * 3) + rad, (durchm * 5) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 5) + rad, rad, p);
        //start
        c.drawCircle((durchm * 0) + rad, (durchm * 4) + rad, rad, p);


        p.setColor(teams[1].getColor());
        c.drawCircle((durchm * 9) + rad, (durchm * 0) + rad, rad, p);
        c.drawCircle((durchm * 9) + rad, (durchm * 1) + rad, rad, p);
        c.drawCircle((durchm * 10) + rad, (durchm * 0) + rad, rad, p);
        c.drawCircle((durchm * 10) + rad, (durchm * 1) + rad, rad, p);
        //finish
        c.drawCircle((durchm * 5) + rad, (durchm * 1) + rad, rad, p);
        c.drawCircle((durchm * 5) + rad, (durchm * 2) + rad, rad, p);
        c.drawCircle((durchm * 5) + rad, (durchm * 3) + rad, rad, p);
        c.drawCircle((durchm * 5) + rad, (durchm * 4) + rad, rad, p);
        //start
        c.drawCircle((durchm * 6) + rad, (durchm * 0) + rad, rad, p);


        p.setColor(teams[2].getColor());
        c.drawCircle((durchm * 9) + rad, (durchm * 9) + rad, rad, p);
        c.drawCircle((durchm * 9) + rad, (durchm * 10) + rad, rad, p);
        c.drawCircle((durchm * 10) + rad, (durchm * 9) + rad, rad, p);
        c.drawCircle((durchm * 10) + rad, (durchm * 10) + rad, rad, p);
        //finish
        c.drawCircle((durchm * 6) + rad, (durchm * 5) + rad, rad, p);
        c.drawCircle((durchm * 7) + rad, (durchm * 5) + rad, rad, p);
        c.drawCircle((durchm * 8) + rad, (durchm * 5) + rad, rad, p);
        c.drawCircle((durchm * 9) + rad, (durchm * 5) + rad, rad, p);
        //start
        c.drawCircle((durchm * 10) + rad, (durchm * 6) + rad, rad, p);



        p.setColor(teams[3].getColor());
        c.drawCircle((durchm * 0) + rad, (durchm * 9) + rad, rad, p);
        c.drawCircle((durchm * 0) + rad, (durchm * 10) + rad, rad, p);
        c.drawCircle((durchm * 1) + rad, (durchm * 9) + rad, rad, p);
        c.drawCircle((durchm * 1) + rad, (durchm * 10) + rad, rad, p);
        //finish
        c.drawCircle((durchm * 5) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 5) + rad, (durchm * 7) + rad, rad, p);
        c.drawCircle((durchm * 5) + rad, (durchm * 8) + rad, rad, p);
        c.drawCircle((durchm * 5) + rad, (durchm * 9) + rad, rad, p);
        //start
        c.drawCircle((durchm * 4) + rad, (durchm * 10) + rad, rad, p);


        p.setColor(Color.BLACK);
        //draw normal items - start at first team
        c.drawCircle((durchm * 1) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 2) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 3) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 3) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 2) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 1) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 0) + rad, rad, p);
        c.drawCircle((durchm * 5) + rad, (durchm * 0) + rad, rad, p);

        //draw normal items - start at second team
        c.drawCircle((durchm * 6) + rad, (durchm * 1) + rad, rad, p);
        c.drawCircle((durchm * 6) + rad, (durchm * 2) + rad, rad, p);
        c.drawCircle((durchm * 6) + rad, (durchm * 3) + rad, rad, p);
        c.drawCircle((durchm * 6) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 7) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 8) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 9) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 10) + rad, (durchm * 4) + rad, rad, p);
        c.drawCircle((durchm * 10) + rad, (durchm * 5) + rad, rad, p);

        //draw normal items - start at third team
        c.drawCircle((durchm * 9) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 8) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 7) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 6) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 6) + rad, (durchm * 7) + rad, rad, p);
        c.drawCircle((durchm * 6) + rad, (durchm * 8) + rad, rad, p);
        c.drawCircle((durchm * 6) + rad, (durchm * 9) + rad, rad, p);
        c.drawCircle((durchm * 6) + rad, (durchm * 10) + rad, rad, p);
        c.drawCircle((durchm * 5) + rad, (durchm * 10) + rad, rad, p);

        //draw normal items - start at fourth team
        c.drawCircle((durchm * 4) + rad, (durchm * 9) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 8) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 7) + rad, rad, p);
        c.drawCircle((durchm * 4) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 3) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 2) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 1) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 0) + rad, (durchm * 6) + rad, rad, p);
        c.drawCircle((durchm * 0) + rad, (durchm * 5) + rad, rad, p);

        p.setColor(Color.WHITE);
        //draw normal items - start at first team
        c.drawCircle((durchm * 1) + rad + 1, (durchm * 4) + rad + 1, rad - 3, p);
        c.drawCircle((durchm * 2) + rad + 1, (durchm * 4) + rad + 1, rad - 3, p);
        c.drawCircle((durchm * 3) + rad + 1, (durchm * 4) + rad + 1, rad - 3, p);
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 4) + rad + 1, rad - 3, p);
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 3) + rad + 1, rad - 3, p);
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 2) + rad + 1, rad - 3, p);
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 1) + rad + 1, rad - 3, p);
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 0) + rad + 1, rad - 3, p);
        c.drawCircle((durchm * 5) + rad + 1, (durchm * 0) + rad + 1, rad - 3, p);

        //draw normal items - start at second team
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 1) + rad, rad - 3, p);
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 2) + rad, rad - 3, p);
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 3) + rad, rad - 3, p);
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 4) + rad, rad - 3, p);
        c.drawCircle((durchm * 7) + rad + 1, (durchm * 4) + rad, rad - 3, p);
        c.drawCircle((durchm * 8) + rad + 1, (durchm * 4) + rad, rad - 3, p);
        c.drawCircle((durchm * 9) + rad + 1, (durchm * 4) + rad, rad - 3, p);
        c.drawCircle((durchm * 10) + rad + 1, (durchm * 4) + rad, rad - 3, p);
        c.drawCircle((durchm * 10) + rad + 1, (durchm * 5) + rad, rad - 3, p);

        //draw normal items - start at third team
        c.drawCircle((durchm * 9) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 8) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 7) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 7) + rad, rad - 3, p);
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 8) + rad, rad - 3, p);
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 9) + rad, rad - 3, p);
        c.drawCircle((durchm * 6) + rad + 1, (durchm * 10) + rad, rad - 3, p);
        c.drawCircle((durchm * 5) + rad + 1, (durchm * 10) + rad, rad - 3, p);

        //draw normal items - start at fourth team
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 9) + rad, rad - 3, p);
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 8) + rad, rad - 3, p);
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 7) + rad, rad - 3, p);
        c.drawCircle((durchm * 4) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 3) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 2) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 1) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 0) + rad + 1, (durchm * 6) + rad, rad - 3, p);
        c.drawCircle((durchm * 0) + rad + 1, (durchm * 5) + rad, rad - 3, p);

        //draw tokens
        p.setColor(Color.MAGENTA);
        teams[0].drawTokens(c, p, rad);
        p.setColor(Color.LTGRAY);
        teams[1].drawTokens(c, p, rad);
        p.setColor(Color.CYAN);
        teams[2].drawTokens(c, p, rad);
        p.setColor(Color.argb(100, 33, 22, 11));
        teams[3].drawTokens(c, p, rad);

        // draw
        mHolder.unlockCanvasAndPost(c);
    }


    @Override
    public void onClick(View v) {
        try {
            initGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

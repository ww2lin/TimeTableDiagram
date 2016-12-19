package Interfaces;

/**
 * Created by AlexLin on 12/18/16.
 */
public interface ViewUpdaterCallback {
    void onPause();
    boolean isPause();
    void increaseSpeed();
    void decreaseSpeed();
    void seek(String multiplier);
}

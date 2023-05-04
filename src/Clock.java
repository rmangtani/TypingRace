import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Clock implements ActionListener {
    private GameView window;
    private static final int DELAY_IN_MILLISEC = 1000;
    private long start;
    private long finish;

    public Clock(Game game) {
        Toolkit.getDefaultToolkit().sync();
        Timer clock = new Timer(DELAY_IN_MILLISEC, this);
        clock.start();
        window = new GameView(game);
        start = 0;
        finish = 0;
    }

    public void startTime() {
        start = System.nanoTime();
    }

    public void endTime() {
        finish = System.nanoTime();
    }

    public long getCurrentTime() {
        return (System.nanoTime()-start)/1000000000;
    }

    public long getTimeElapsed() {
        return (finish-start)/1000000000;
    }
    public void actionPerformed(ActionEvent e) {
        window.repaint();
    }
}

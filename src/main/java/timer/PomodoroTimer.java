package timer;

import frame.MainFrame;
import util.Constants;
import util.Foo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimer {
    private int learnDuration;
    private int restDuration;


    private Timer pomodoroTimer;

    public PomodoroTimer(String learnDuration, String restDuration) {
        this.learnDuration = Foo.minutesToSeconds(learnDuration);
        this.restDuration = Foo.minutesToSeconds(restDuration);

        pomodoroTimer = new Timer(10, new ActionListener() {
            int i = getLearnDuration();
            int j = getRestDuration();
            boolean finished = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(finished == false) {
                    i--;
                    MainFrame.pomodoroBtn.setText(Constants.TOMATO + " " + Foo.convertToMinutesAndSeconds(i));

                    if (i == -1) {
                        Foo.playSound(Constants.TIMER_FINISH_SOUND);
                        Foo.updateDataInFile((String) MainFrame.modulcb.getSelectedItem(), Constants.PATH_FOR_MODULE_STATS, Foo.minutesToSeconds(String.valueOf(getLearnDuration())));
                        finished = true;
                    }
                } else {
                    j--;
                    MainFrame.pomodoroBtn.setText(Constants.TOMATO + " " + Foo.convertToMinutesAndSeconds(j));
                    if(j == -1) {
                        MainFrame.pomodoroBtn.setText(Constants.TOMATO);
                        pomodoroTimer.stop();
                    }

                }
            }
        });

    }

    public void startLearn() {
        this.pomodoroTimer.start();
    }

    public void stopLearn() {
        this.pomodoroTimer.stop();
    }

    public int getLearnDuration() {
        return learnDuration;
    }

    public int getRestDuration() {
        return restDuration;
    }
}

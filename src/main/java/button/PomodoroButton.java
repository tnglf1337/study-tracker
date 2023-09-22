package button;

import frame.MainFrame;
import timer.PomodoroTimer;
import util.Constants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.Foo.getLearnDuration;
import static util.Foo.getRestDuration;

public class PomodoroButton extends DesignedButton implements ActionListener {
    private boolean isRecording;
    private PomodoroTimer pt;
    public PomodoroButton(String text, PomodoroTimer pt) {
        super(text);

        this.pt = pt;
        addActionListener(this);
        setForeground(Constants.TOMATO_RED);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isRecording) {
            isRecording = false;
            pt = new PomodoroTimer(getLearnDuration(), getRestDuration());
        } else {
            isRecording = true;
            pt.startLearn();
        }
    }
}

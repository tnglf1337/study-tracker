package button;

import frame.MainFrame;
import timer.RecordTimer;
import util.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.Constants.*;
import static util.Foo.updateDataInFile;

public class RecordButton extends DesignedButton implements ActionListener {
    private boolean isRecording;
    RecordTimer rt;
    public RecordButton(String text, RecordTimer rt) {
        super(text);

        this.rt = rt;
        addActionListener(this);
        setForeground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isRecording) {
            isRecording = false;
            MainFrame.recordBtn.setText(PLAY);
            MainFrame.recordBtn.setForeground(Color.BLACK);


            rt.stop();
        } else {
            isRecording = true;
            MainFrame.recordBtn.setText(STOP);

            rt.start();
        }
    }
}

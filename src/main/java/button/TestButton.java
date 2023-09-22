package button;

import util.Constants;
import util.Foo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestButton extends DesignedButton{
    ActionListener print;
    ActionListener sound;
    public TestButton(String text) {
        super(text);

        print = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        };

        sound = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Foo.playSound(Constants.TIMER_FINISH_SOUND);
            }
        };

        addActionListener(print);
        addActionListener(sound);
    }
}

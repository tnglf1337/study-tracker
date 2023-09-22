package frame;

import javax.swing.*;
public class ErrorFrame {
    public ErrorFrame(int fehlercode) {
        switch (fehlercode) {
            case 1:
                JOptionPane.showMessageDialog(new JFrame(), "You are not allowed to use ('*', '+', '~', '#', '!', '§', '%', ',', '.', '?')", "Invalid characters", JOptionPane.ERROR_MESSAGE);
        }
    }
}
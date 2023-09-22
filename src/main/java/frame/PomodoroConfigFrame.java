package frame;

import static util.Constants.*;
import static util.Foo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroConfigFrame extends JFrame{
    JLabel jl1, jl2;
    JTextField learnTime, restTime;
    public PomodoroConfigFrame() {
        setProperties();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        jl1 = new JLabel("Lernzeit (in min):");
        jl2 = new JLabel("Pause (in min):");

        learnTime = new JTextField(getLearnDuration());
        learnTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLearnDuration(learnTime.getText(), PATH_FOR_POMODOROCONFIG);
            }
        });

        restTime = new JTextField(getRestDuration());
        restTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRestDuartion(restTime.getText(), PATH_FOR_POMODOROCONFIG);
            }
        });

        //Position
        jl1.setBounds(40, 40, 100, 25);
        jl2.setBounds(40, 80, 100, 25);

        learnTime.setBounds(160, 40, 80, 25);
        restTime.setBounds(160, 80, 80, 25);

        //Add
        add(jl1); add(jl2);
        add(learnTime); add(restTime);
    }

    private void setProperties() {
        setTitle("Configure pomodoro timer");
        setSize(APP_WIDTH, APP_HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon(APP_ICON).getImage());
    }
}

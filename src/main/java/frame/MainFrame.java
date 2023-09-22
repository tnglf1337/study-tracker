package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import button.RecordButton;
import timer.PomodoroTimer;
import timer.RecordTimer;

import static util.Constants.*;
import static util.Foo.*;
public class MainFrame extends JFrame{
    private List<String> currentData = getDataAsList(PATH_FOR_MODULE_STATS);
    public static JComboBox< String > modulcb;
    private JLabel chose;
    public static RecordButton recordBtn;
    public static JButton pomodoroBtn;
    private MenuBar menuBar;
    private Timer standardTimer;
    private PomodoroTimer pomodoroTimer;
    private RecordTimer recordTimer;
    private int seconds = 0;
    private boolean isRecording = false;

    public MainFrame() {
        setProperties();
        initComponents();
        setVisible(true);
    }
    private void initComponents() {
        pomodoroTimer = new PomodoroTimer(getLearnDuration(), getRestDuration());
        modulcb = new JComboBox<>(getModuleNames(currentData));
        menuBar = new MenuBar();
        chose = new JLabel("W\u00e4hle Studienfach:");

        recordTimer = new RecordTimer(recordBtn);
        recordBtn = new RecordButton(PLAY, recordTimer);
        //pomodoroBtn = new PomodoroButton(TOMATO, pomodoroTimer);

        addActionListeners();
        setComponentBounds();
        addComponents();
    }
    private void standardTimer() {
        standardTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if(seconds % 2 == 0) {
                    recordBtn.setForeground(TOMATO_RED);
                } else {
                    recordBtn.setForeground(Color.BLACK);
                }
                System.out.println(seconds);
            }
        });
    }

    private void setProperties() {
        setTitle(APP_NAME);
        setSize(APP_WIDTH, APP_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon(APP_ICON).getImage());
    }

    private void setComponentBounds() {
        chose.setBounds(30, 20, 125, 25);
        modulcb.setBounds(155, 20, 125, 25);
        recordBtn.setBounds(100, 70, 100, 30);
    }

    private void addComponents() {
        setJMenuBar(menuBar);
        add(chose);
        add(recordBtn);
        add(modulcb);
    }

    private void addActionListeners() {
        recordBtn.addActionListener(e -> {
            if(isRecording) {
                isRecording = false;
                recordBtn.setText(PLAY);
                recordBtn.setForeground(Color.BLACK);
                updateDataInFile((String) modulcb.getSelectedItem(), PATH_FOR_MODULE_STATS, seconds);
                seconds = 0;

                standardTimer.stop();
            } else {
                isRecording = true;
                recordBtn.setText(STOP);
                standardTimer();

                standardTimer.start();
            }
        });
    }
}

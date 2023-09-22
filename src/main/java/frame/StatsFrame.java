package frame;

import static util.Constants.*;
import static util.Foo.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class StatsFrame extends JFrame {
    JTextArea modules, times;
    List<String> dataFile;
    JLabel total;
    public StatsFrame() {
        setProperties();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        dataFile = getDataAsList(PATH_FOR_MODULE_STATS);
        modules = new JTextArea();
        times = new JTextArea();
        total = new JLabel("Total: " + getTotalTime(dataFile)); // is converted wrong but idk why

        modules.setBackground(new Color(240, 240, 240));
        modules.setBounds(40, 20, 110, 420);
        modules.setFocusable(false);

        times.setBackground(new Color(240, 240, 240));
        times.setBounds(160, 20, 110, 420);
        times.setFocusable(false);

        total.setBounds(40, 450, 100, 20);

        printStats(dataFile);

        add(modules);
        add(times);
        add(total);
    };

    private void printStats(List<String> list) {
        String[] moduleNames = getModuleNames(list);
        String[] moduleTimes = getModuleTimes(list);

        String buildNames = "";
        String buildTimes = "";

        for(int i = 0; i < list.size(); i++) {
            buildNames += moduleNames[i] + "\n" ;
            buildTimes += convertSeconds(Integer.parseInt(moduleTimes[i])) + "\n";
        }

        modules.setText(buildNames);
        times.setText(buildTimes);
    }

    private void setProperties() {
        setTitle("Your statistics");
        setSize(APP_WIDTH, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon(APP_ICON).getImage());
    }
}

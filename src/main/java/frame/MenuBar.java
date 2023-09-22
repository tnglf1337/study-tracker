package frame;

import frame.AddModuleFrame;
import frame.PomodoroConfigFrame;
import frame.StatsFrame;
import util.Constants;
import util.Foo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuBar extends JMenuBar implements ActionListener {
    JMenu optionMenu, removeMenu;
    JMenuItem addModuleItem, printStatsItem, configurePomodoroItem;

    List<String> dataFile;

    public MenuBar() {
        initComponents();
        setVisible(true);

    }

    private void initComponents() {
        initData();

        optionMenu = new JMenu("Options");
        removeMenu = new JMenu("Remove module...");

        addModuleItem = new JMenuItem("Add new module...");
        printStatsItem = new JMenuItem("Print stats...");
        configurePomodoroItem = new JMenuItem("Configure pomodoro timer...");

        optionMenu.add(addModuleItem);
        optionMenu.add(removeMenu);
        optionMenu.add(configurePomodoroItem);
        optionMenu.add(printStatsItem);
        addItemsToList(removeMenu, dataFile);

        configurePomodoroItem.addActionListener(this);
        addModuleItem.addActionListener(this);

        addActionlistenersTo(removeMenu);

        printStatsItem.addActionListener(this);

        add(optionMenu);
    }

    private void addActionlistenersTo(JMenu removeMenu) {
        for (int i = 0; i < dataFile.size(); i++) {
            removeMenu.getItem(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addModuleItem) {
            new AddModuleFrame();
        } else if (e.getSource() == printStatsItem) {
            new StatsFrame();
        } else if (e.getSource() == configurePomodoroItem) {
            new PomodoroConfigFrame();
        }

        for (int i = 0; i < dataFile.size(); i++) {
            if (e.getSource() == removeMenu.getItem(i)) {
                String moduleToRemove = removeMenu.getItem(i).getText();
                Foo.removeModuleFromFile(moduleToRemove, Constants.PATH_FOR_MODULE_STATS);
            }
        }
    }

    private void addItemsToList(JMenu m, List<String> data) {
        String[] s = Foo.getModuleNames(data);
        for (int i = 0; i < data.size(); i++) {
            m.add(new JMenuItem(s[i]));
        }
    }

    private void initData() {
        dataFile = Foo.getDataAsList(Constants.PATH_FOR_MODULE_STATS);
    }
}

package frame;

import util.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static util.Foo.*;
public class AddModuleFrame extends JFrame {
    JLabel info;
    JTextField textField;
    public AddModuleFrame() {
        setProperties();
        initComponents();
        setVisible(true);
    }
    private void initComponents() {
        info = new JLabel("Enter name for module:");
        textField = new JTextField();
        //Position
        info.setBounds(55, 35, 200, 25);
        textField.setBounds(55, 60, 200, 25);
        textField.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String module = textField.getText();
                if(containsProhibited(module) || module.equals("")) {
                    new ErrorFrame(1);
                    System.out.println("error invalid chars in module name");
                    textField.setText("");
                } else {
                    addModuleToFile(module, Constants.PATH_FOR_MODULE_STATS);
                    textField.setText("");
                    dispose();
                }
            }
        });


        add(info);
        add(textField);
    }

    private static boolean containsProhibited(String s) {
        String[] prohibited = {"*", "+", "~", "#", "!", "§", "%", ",", ".", "?"};
        for (int i = 0; i < prohibited.length; i++) {
            if(s.contains(prohibited[i])) {
                return true;
            }
        }
        return false;
    }
    private void setProperties() {
        setTitle("Add module");
        setSize(Constants.APP_WIDTH, Constants.APP_HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setIconImage(new ImageIcon(Constants.APP_ICON).getImage());
    }
}

package org.app.view;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame{
    public JPanel mainPanel;
    private JTextField textField1;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("MainForm");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("MainForm");
        JButton button = new JButton();
        button.setText("Button");
        panel.add(label);
        panel.add(button);
        frame.add(panel);
        frame.setSize(200, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

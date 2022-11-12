package org.app.view;

import javax.swing.*;
import java.awt.*;

public class locationView extends JPanel{
    public JFrame window;
    public Container con;
    public JPanel textPanel,inputPanel,disPlayPanel;
    public JLabel textLabel,displayLabel,displayLabelTemp,displayLabelobservation_time,displayLabelweather_icons,
            displayLabelweather_descriptions,displayLabelwind_speed
            ,displayLabelwind_degree,displayLabelwind_dir,displayLabelpressure,
            displayLabelprecip,displayLabelhumidity,displayLabelcloudcover,displayLabelfeelslike,
            displayLabeluv_index,displayLabelvisibility,displayLabelis_day,displayLabellocation;
    public JTextField jtf;
    public JButton enterB;
    Font normalFont = new Font("Times New Roman",Font.PLAIN,26);
    Font normalFontTemp = new Font("Times New Roman",Font.PLAIN,40);
    public locationView(int x,int y) {

        disPlayPanel = new JPanel();
        disPlayPanel.setBounds(200,200,800,100);
        disPlayPanel.setBackground(Color.BLACK);
        displayLabelTemp = new JLabel("");
        displayLabelTemp.setForeground(Color.white);
        displayLabelTemp.setFont(normalFontTemp);
        //displayLabelTemp.setBounds(1,200,1000,100);
        //displayLabelTemp.setFontSize(30);
        disPlayPanel.add(displayLabelTemp);
        con.add(disPlayPanel);

        inputPanel = new JPanel();
        inputPanel.setBounds(450,1,300,50);
        inputPanel.setBackground(Color.BLACK);
        inputPanel.setLayout(new GridLayout(1,2));
    }
}

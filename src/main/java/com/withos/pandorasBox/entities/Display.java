package com.withos.pandorasBox.entities;

import com.withos.pandorasBox.entities.Person;
import com.withos.pandorasBox.entities.Population;
import com.withos.pandorasBox.entities.Simulation;
import com.withos.pandorasBox.states.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class Display implements ActionListener {

    private static JFrame frame;
    private static JLabel label;
    private BufferedImage image;
    private final int height;
    private final int width;
    private final BufferedImage CLEAR;

    public Display(int width, int height) {
        this.height = height;
        this.width = width;
        frame = null;
        label = null;
        this.image = new BufferedImage(width , height, BufferedImage.TYPE_3BYTE_BGR);
        this.CLEAR = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

    }

    public void displayPopulation(Population population) {
        image.setData(CLEAR.getData());
        Graphics2D g = this.image.createGraphics();
        List<Person> popList = population.getPeople();
        for(Person person: popList) {
            int x = ((person.getX())).intValue();
            int y = ((person.getY())).intValue();
            IState personState = person.getState();
            if(personState instanceof ImmuneState) g.setPaint(Color.CYAN);
            else if(personState instanceof SymptomsState) g.setPaint(Color.RED);
            else if(personState instanceof NoSymptomsState) g.setPaint(Color.ORANGE);
            else if(personState instanceof HealthyState) g.setPaint(Color.GREEN);
            g.fillRect(x, y, 5, 5);
        }
        this.display(this.image);
    }

    private void display(BufferedImage image) {
        if (frame == null) {
            frame = new JFrame();
            frame.setTitle("Simulation");
            frame.setSize(image.getWidth(), image.getHeight());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            label = new JLabel();
            label.setIcon(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.WEST);
            JButton button = new JButton("Save");
            button.addActionListener(this);
            label.setBounds(width, height, 200, 100);
            frame.getContentPane().add(button, BorderLayout.SOUTH);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        } else {
            label.setIcon(new ImageIcon(image));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Simulation.saveSimulation();
    }
}

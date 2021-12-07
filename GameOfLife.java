/*
Minh Le - CSC 171 - Spring 2021
Project 3
Flourishes:
(f) Add a way to interactively configure the board by clicking to swtich cells from alive to dead, and vice versa
(g) Make it look cool. Explore different colors for the dead cells, live cells, and the grid
*/


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
public class GameOfLife extends JFrame implements ActionListener{

    protected JButton button;
    protected JButton button2;
    protected JButton button3;
    protected GameCanvas gcanvas;
    protected JLabel label;

    public GameOfLife(){
        super();
        setTitle("Game of Life");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        
    
        //Buttons creation
        button = new JButton("Randomize");
        button.addActionListener(this);
        
        button2 = new JButton("Start");
        button2.addActionListener(this);

        button3 = new JButton("Interact: OFF");
        button3.addActionListener(this);

        GameCanvas canvas = new GameCanvas();
        gcanvas = canvas;
        add(canvas);

        label = new JLabel("Steps: " + gcanvas.getSteps());

        gcanvas.addLabel(label);

        //Sub Panel creation
        JPanel subpanel =  new JPanel();
        subpanel.add(button2);
        subpanel.add(button);
        subpanel.add(button3);
        subpanel.add(label);
        add(subpanel, BorderLayout.NORTH);

    }

    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();
        if (src == button){
            if (button.getText().equals("Randomize")){
                /*
                gcanvas.removeAll();
                revalidate();
                repaint();
                */
                gcanvas.randomize();
                button.setText("Reset");
            }
            else if (button.getText().equals("Reset")){
                button.setText("Randomize");
                label.setText("Steps: 0");
                gcanvas.removeAll();
                gcanvas.reset();
                revalidate();
                repaint();
            }
        }

        if (src == button2){
            if (button2.getText().equals("Start")){
                button2.setText("Stop");
                gcanvas.simulate();
            }

            else if (button2.getText().equals("Stop")){
                button2.setText("Start");
                gcanvas.stop();
            }
        }
        
        if (src == button3){
            if (button3.getText().equals("Interact: OFF")){
                button3.setText("Interact: ON");
                gcanvas.turnOn();
            }
            else if (button3.getText().equals("Interact: ON")){
                button3.setText("Interact: OFF");
                gcanvas.turnOff();
            }
        }




    }
    public static void main(String[] args){
        new GameOfLife().setVisible(true);
    }
}
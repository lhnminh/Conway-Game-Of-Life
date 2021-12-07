/*
Flourishes:
(g) Make it look cool. Explore different colors for the dead cells, live cells and the grids.
(f) Add a way to interactively configure the board by clicking to switch cells from
alive to dead, and vice versa. You should include a button for entering/exiting
this mode.
*/

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;




import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GameCanvas extends JPanel implements MouseListener{
    
    //Theses ints are keeping track of dimensions of the squares (grid) and the remaining spaces outside the grid  
    protected int heightR;
    protected int widthR;
    protected int heightS;
    protected int widthS;
    
    protected boolean interact = false; //boolean to keep track of interaction
    protected JLabel label; //The label

    protected int[][] status = new int[20][20]; //Keeping track of cell living status, 0 for dead and 1 for alive
    protected int[][] neighbors = new int[20][20]; //Keeping track of neighbors
    
    protected Timer timer; 

    protected int steps = 0; //Steps performed 



    public void addLabel(JLabel label){
        //Adding a label to the canvas
        this.label = label;
    }


    public int getSteps(){
        //Return the steps performed  
        return steps;
    }


    public GameCanvas(){
        addMouseListener(this);
    }

    protected class TimerDoStuff implements ActionListener{
        public void actionPerformed(ActionEvent e){            
            Graphics g = getGraphics();
            steps += 1;
            
            //Seting the steps label
            label.setText("Steps: " + steps);

            //Calculating the neighbors
            for (int i = 0; i < neighbors.length; i++){
                for (int j = 0; j < neighbors.length; j++){
                    //Top left
                    if (i >= 1 && j >= 1){
                        if (status[i-1][j-1] == 1){
                            neighbors[i][j] += 1;
                        }
                    }
    
                    //Top 
                    if(i >= 1){
                        if (status[i-1][j] == 1){
                            neighbors[i][j] += 1;
                        }
                    }
                    
                    //Top right or right top
                    if (i >= 1 && j < status.length - 1){
                        if (status[i-1][j+1] == 1){
                            neighbors[i][j] += 1;
                        }
                    }
    
                    //Right directly
                    if(j < status.length - 1){
                        if(status[i][j+1] == 1){
                            neighbors[i][j] += 1;
                        }
                    }
                    
                    //Right down or down right
                    if(i < status.length - 1 && j < status.length - 1){
                        if(status[i+1][j+1] == 1){
                            neighbors[i][j] += 1;
                        }
                    }
    
                    //Directly down
                    if( i < status.length - 1){
                        if(status[i+1][j] == 1){
                            neighbors[i][j] += 1;
                        }
                    }
                    //Down left or left down
                    if(i < status.length - 1 && j > 0){
                        if(status[i+1][j-1] == 1){
                            neighbors[i][j] += 1;
                        }
                    }
                    //Directly left
                    if(j > 0){
                        if(status[i][j-1] == 1){
                            neighbors[i][j] +=1;
                        }
                    }    
                }
            }
            
            /*
            *****************************************
            TESTING ONLY: Printing the neighbors
            for (int i = 0; i < status.length; i++){
                for (int j = 0; j < status.length; j++){
                    System.out.print(neighbors[i][j] + " ");
                }
                System.out.println();
            }
            ******************************************
            */

            //Redraw and re calculate the living
            for (int i = 0; i < status.length; i++){
                for (int j = 0; j < status.length; j++){
                    if (neighbors[i][j] == 3){
                        g.setColor(Color.MAGENTA);
                        g.fillRect(widthR + j*widthS + 1, heightR + i*heightS + 1, widthS-1, heightS-1);
                        status[i][j] = 1;
                    }
    
                    if (neighbors[i][j] == 2 && status[i][j] == 1){
                        neighbors[i][j] = 0;
                        continue;
                    }
    
                    if (neighbors[i][j] != 3){
                        g.setColor(Color.BLUE);
                        g.fillRect(widthR + j*widthS + 1, heightR + i*heightS + 1, widthS-1, heightS-1);
                        status[i][j] = 0;
                    }

                    //Reseting the neighbors count
                    neighbors[i][j] = 0;
                }
            }

            /*
            **************************************************
            TESTING ONLY
            Printing the status of dead/alive
            System.out.println("Status");
            for (int i = 0; i < status.length; i++){
                for (int j = 0; j < status.length; j++){
                    System.out.print(status[i][j] + " ");
                }
                System.out.println();
            }
            *****************************************************
            */

        }
    }

    public void simulate(){
        timer = new Timer(1000, new TimerDoStuff());
        //Do stuff
        timer.start();
        /*
        TESTING OUT: This is the algorithm for calculating neighbors and living status

        //This is for calculating neighbors
        for (int i = 0; i < neighbors.length; i++){
            for (int j = 0; j < neighbors.length; j++){
                //Top left
                if (i >= 1 && j >= 1){
                    if (status[i-1][j-1] == 1){
                        neighbors[i][j] += 1;
                    }
                }

                //Top 
                if(i >= 1){
                    if (status[i-1][j] == 1){
                        neighbors[i][j] += 1;
                    }
                }
                
                //Top right or right top
                if (i >= 1 && j < status.length - 1){
                    if (status[i-1][j+1] == 1){
                        neighbors[i][j] += 1;
                    }
                }

                //Right directly
                if(j < status.length - 1){
                    if(status[i][j+1] == 1){
                        neighbors[i][j] += 1;
                    }
                }
                
                //Right down or down right
                if(i < status.length - 1 && j < status.length - 1){
                    if(status[i+1][j+1] == 1){
                        neighbors[i][j] += 1;
                    }
                }

                //Directly down
                if( i < status.length - 1){
                    if(status[i+1][j] == 1){
                        neighbors[i][j] += 1;
                    }
                }
                //Down left or left down
                if(i < status.length - 1 && j > 0){
                    if(status[i+1][j-1] == 1){
                        neighbors[i][j] += 1;
                    }
                }
                //Directly left
                if(j > 0){
                    if(status[i][j-1] == 1){
                        neighbors[i][j] +=1;
                    }
                }    
            }
        }
        
        //Redraw and re calculate the living
        for (int i = 0; i < status.length; i++){
            for (int j = 0; j < status.length; j++){
                if (neighbors[i][j] == 3){
                    g.setColor(Color.BLACK);
                    g.fillRect(widthR + j*widthS + 1, heightR + i*heightS + 1, widthS-1, heightS-1);
                    status[i][j] = 1;

                }

                if (neighbors[i][j] == 2 && status[i][j] == 1){
                    continue;
                }

                if (neighbors[i][j] != 3){
                    g.setColor(Color.WHITE);
                    g.fillRect(widthR + j*widthS + 1, heightR + i*heightS + 1, widthS-1, heightS-1);
                    status[i][j] = 0;
                }
            }
        }
        */       
    }
    

    public void stop(){
        timer.stop();
    }



    @Override
    public void paintComponent(Graphics g) {
        //Width and height of all the squares combine
        int width =  (getWidth() * 90)/100;
        int height = (getHeight() * 90)/ 100;
        

        //Height and width of each square
        heightS = height / 20;
        widthS = width / 20;

        //Remaining height (the part outside of the drawing thing)
        int remainHeight = getHeight() - height - heightS;
        int remainWidth = getWidth() - width - widthS;

        heightR = remainHeight;
        widthR = remainWidth;

        
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                //Redrawing the cell if they are alive 
                if(status[i][j] == 1){
                    g.setColor(Color.MAGENTA);
                    g.fillRect(remainWidth + j*widthS, remainHeight + i*heightS, widthS, heightS);    
                }
                g.setColor(Color.BLACK);
                g.drawRect(remainWidth + j*widthS, remainHeight + i*heightS, widthS, heightS);
            }
        }
    }

    public void reset(){
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                status[i][j] = 0;
            }
        }
        steps = 0;
    }

    public void randomize(){
        Graphics g = getGraphics();
        Random randGen = new Random();

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                if(randGen.nextInt(2) == 0){
                    //Each square have a 50/50 chance of being alive or dead
                    g.setColor(Color.MAGENTA);
                    g.fillRect(widthR + j*widthS + 1, heightR + i*heightS + 1, widthS - 1, heightS - 1);
                    status[i][j] = 1;
                }
            }
        }
    }
    
    public void turnOn(){
        interact = true;
    }

    public void turnOff(){
        interact = false;
    }


    @Override
    public void mouseClicked(MouseEvent e){
        if(interact == true){
            int x = e.getX();
            int y = e.getY();
            Graphics g = getGraphics();
            
            //Coordinate of the rect that we are going to fill in
            int xCor = 0;
            int yCor = 0;

            int updateX = 0;
            int updateY = 0;

            for (int i = 0; i < 20; i++){
                for (int j = 0; j < 20; j++){
                    //Checking the x coordinate where the user pressed 
                    xCor = widthR + j*widthS + widthS;
                    if (x <= xCor){
                        updateX = j;
                        break;
                    }
                }
                // Checking the y coordinate where the user pressed
                yCor = heightR + i*heightS + heightS;
                if (y <= yCor){
                    updateY = i;
                    break;
                }
            }

            // Deleting the added square height and width to draw the rectangle
            xCor -= widthS;
            yCor -= heightS;
            
            if(status[updateY][updateX] == 0){
                status[updateY][updateX] = 1;
            } 
            else{
                status[updateY][updateX] = 0;
            }
            
            //System.out.println("update:" + updateX + " " + updateY);
            //System.out.println("Coordinate: " + xCor + " " + yCor);
            
            if (status[updateY][updateX] == 1){
                g.setColor(Color.MAGENTA);
            }
            else{
                g.setColor(Color.BLUE);
            }
            g.fillRect(xCor + 1, yCor + 1, widthS - 1, heightS - 1);
            
        }
        /* 
            for (int i = 0; i < status.length; i++){
                for (int j = 0; j < status.length; j++){
                    System.out.print(neighbors[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            */
    }

    
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}

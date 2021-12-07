# Con-Way-Game-Of-Life
Simulation of Conway Game of Life by Minh

Date (of completion): 4/28/2021

Description:

This program will simulate Conway's Game of Life graphically 


Game of Life.java
- This file contains the JFrame and the creation of the component inside the JFrame such as JButton, JLabel, etc


GameCanvas.java:
- This file creates the canvas that would be added into the JFrame.
- It contains the following methods:
        
        + addLabel(): add a label to the canvas
        + getSteps(): return the steps performed in the simulation
        
        + simulate(): Start the simulation
        + stop(): Stop the simulation
        
        + reset(): Reseting the living status of the cell
        + randomize(): Randomize the living status of cell (Each cell has a 50% chance of being alive)

        + turnOn(): turning on interaction
        + turnOff(): turning off interaction
    
 - Other methods such as paint component and mouseClicked are also present
 - All of my logic are carefully documented in the code as well. 
    
State of the project:
- The project run fine without any known bugs
- There are 2 flourishes
    - Add a way to interactively configure the board by clicking to switch cells from
    alive to dead, and vice versa. You should include a button for entering/exiting
    this mode.
    -  Make it look cool. Explore different colors for the dead cells, live cells, and the
    grid



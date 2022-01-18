# Conway-Game-Of-Life
Simulation of Conway Game of Life by Minh

Date (of completion): 4/28/2021

Description:

This program will simulate Conway's Game of Life graphically 
Read more on Conway's Game of Life here: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

Files included:

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

Here are some demo



https://user-images.githubusercontent.com/70278752/149823896-c06dba4f-4c4d-47c5-9cd3-7c9b6bae6685.mp4






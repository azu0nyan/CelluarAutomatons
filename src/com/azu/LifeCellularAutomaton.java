package com.azu;

import java.awt.*;

public class LifeCellularAutomaton extends CellularAutomaton {

    public LifeCellularAutomaton(int width, int height) {
        super(width, height);
    }

    int getNewCellValue(int x, int y) {
        int oldValue = getCell(x,y);
        //alive
        int neighbours =  getNeighboursCountWithValueIncludingMyself(x,y, 256);
        if(oldValue == 256){
            //2 3 alive neighbours + 1 myself
            return (neighbours >= 3 && neighbours <= 4)?256:255;
        } else if(neighbours == 3){
            return 256;
        } else {
            return oldValue > 0 ? oldValue - 1 : 0;
        }
    }

    @Override
    public Color getColorAt(int x, int y) {
        int value = getCell(x, y);
        if(value == 256){
            return Color.RED;
        } else {
            return new Color(value,value,value);
        }
    }
}

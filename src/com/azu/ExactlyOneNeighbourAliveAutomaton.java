package com.azu;

import java.awt.*;

public class ExactlyOneNeighbourAliveAutomaton extends CellularAutomaton {
    public ExactlyOneNeighbourAliveAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    int getNewCellValue(int x, int y) {
        if(getCell(x,y) == 1 || getNeighboursCountWithValueIncludingMyself(x, y , 1) - getCell(x, y) == 1){
            return  1;
        }
        return 0;
    }
}

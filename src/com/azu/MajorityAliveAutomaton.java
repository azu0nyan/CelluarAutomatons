package com.azu;

import java.awt.*;

public class MajorityAliveAutomaton extends CellularAutomaton {
    public MajorityAliveAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    int getNewCellValue(int x, int y) {
        if(getNeighboursCountWithValueIncludingMyself(x,y, 1) >=5){
            return  1;
        }
        return 0;
    }

    @Override
    public Color getColorAt(int x, int y) {
        if (getCell(x, y) == 1) {
            return new Color(189, 8, 255);
        } else {
            return new Color(10, 6, 7);
        }
    }
}

package com.azu;

import java.awt.*;

public class LichensWithDeathAutomaton extends CellularAutomaton {
    public LichensWithDeathAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    int getNewCellValue(int x, int y) {
        if( getNeighboursCountWithValueIncludingMyself(x, y , 1) - getCell(x, y) == 3
                || getNeighboursCountWithValueIncludingMyself(x, y , 1) - getCell(x, y) == 7
                || getNeighboursCountWithValueIncludingMyself(x, y , 1) - getCell(x, y) == 8){
            return  1;
        }
        if( getNeighboursCountWithValueIncludingMyself(x, y , 1) - getCell(x, y) == 4){
            return 0;
        }

        return getCell(x, y);
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

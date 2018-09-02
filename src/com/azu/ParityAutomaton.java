package com.azu;

import java.awt.*;

public class ParityAutomaton extends CellularAutomaton {
    public ParityAutomaton(int width, int height) {
        super(width, height);
    }

    public void initTestData() {
        fillRect(width / 2 -1, height / 2 - 1, 2,2, 1);
    }
    @Override
    int getNewCellValue(int x, int y) {
        return  (getSNWECNeighboursCountWithValue(x, y, 1)% 2 == 0)?1:0;
    }

    @Override
    public Color getColorAt(int x, int y) {
        if (getCell(x, y) == iteration % 2) {
            return new Color(189, 8, 255);
        } else {
            return new Color(10, 6, 7);
        }
    }
}

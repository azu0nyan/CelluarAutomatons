package com.azu;

import java.awt.*;

public class NeigboursTableAutomaton extends CellularAutomaton {
    int table [] = new int[10];
    public NeigboursTableAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    public void initTestData() {
        table = new int[]{0,0,0,0,1,0,1,1,1,1};
        fillRandom(width*height /2, new int []{1});
    }

    @Override
    int getNewCellValue(int x, int y) {
        return table[getNeighboursCountWithValueIncludingMyself(x,y,1)];
    }

}

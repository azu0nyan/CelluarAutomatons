package com.azu;

import java.awt.*;

public class CyclicGeneticDrift extends CellularAutomaton {
    public CyclicGeneticDrift(int width, int height) {
        super(width, height);
    }

    @Override
    public void initTestData() {
        fillOvalRandom(width / 2 - width / 4, height / 2 - height / 4, width / 2, height / 2, new int[]{0,1,2,3});
        //fillRandom(new int[] {0,1,2,3});
    }



    final int beatsMe [] = {1,2,3,0};
    @Override
    int getNewCellValue(int x, int y) {
        int me = getCell(x, y);
        int beats = beatsMe[me];
        int rand = Utils.fastRandom() % 4;
        switch (rand < 0? rand + 4: rand){
            case 0:return getCell(x - 1 , y) == beats?beats:me;
            case 1:return getCell(x + 1 , y) == beats?beats:me;
            case 2:return getCell(x , y - 1) == beats?beats:me;
            case 3:return getCell(x , y + 1) == beats?beats:me;
        }
        return 0;
    }

    @Override
    public Color getColorAt(int x, int y) {
        switch (getDrawingCell(x, y)) {
            case 0:
                return new Color(255, 94, 114);
            case 1:
                return new Color(145, 226, 255);
            case 2:
                return new Color(139, 194, 121);
            case 3:
                return new Color(186, 107, 255);
            default:
                return new Color(10, 6, 7);

        }
    }

}

package com.azu;

import java.awt.*;

public class GemeticDriftAutomaton extends CellularAutomaton {
    public GemeticDriftAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    public void initTestData() {
        //fillOval(width / 2 - width / 4, height / 2 - height / 4, width / 2, height / 2, 1);
        fillRandom(new int[] {0,1,2,3});
    }

    @Override
    int getNewCellValue(int x, int y) {
        if(x % 100 == 0 || y % 100 == 0){
            return 0;
        }
        switch (r.nextInt(4)){
            case 0:return getCell((x - 1) % 100 == 0?x : x - 1, y);
            case 1:return getCell((x + 1) % 100 == 0?x : x + 1, y);
            case 2:return getCell(x , (y - 1)% 100 == 0? y : y - 1);
            case 3:return getCell(x , (y + 1)%100 == 0?y : y + 1);
        }
        return 0;
    }
    /* @Override
    int getNewCellValue(int x, int y) {
        switch (r.nextInt(4)){
            case 0:return getCell(x - 1, y);
            case 1:return getCell(x + 1, y);
            case 2:return getCell(x , y - 1);
            case 3:return getCell(x , y + 1);
        }
        return 0;
    }*/
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

package com.azu;

import java.awt.*;

public class RandomDiffusionAutomaton extends CellularAutomaton {
    public RandomDiffusionAutomaton(int width, int height) {
        super(width, height);
    }
    @Override
    public void initTestData() {
        //fillOval(width / 2 - width / 4, height / 2 - height / 4, width / 2, height / 2, 1);
        fillRandom(new int[] {0,1,2,3});
    }
    int getNewCellValue(int x, int y) {
        double rand = r.nextDouble();//Math.abs(Utils.fastRandom() / (double)Utils.m);
        if(  rand <= 0.1) {
                return getCell(x - 1, y);
        }
        if(0.1 <=rand &&  rand <= 0.5) {
                return getCell(x + 1, y);
        }
        if(0.5<= rand && rand <= 0.7) {
                return getCell(x, y - 1);
        }
        if(0.7 <= rand ){
            return getCell(x , y + 1);
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

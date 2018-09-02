package com.azu;

import java.awt.*;

public class ParityFlipAutomaton extends CellularAutomaton {
    static final int ready = 0;
    static final int alive = 1;
    static final int refracted = 2;
    static final int readyRefracted = 3;

    public ParityFlipAutomaton(int width, int height) {
        super(width, height);
    }
    public void initTestData() {
        fillRect(width / 2 -1, height / 2 - 1, 2,2, 1);
    }

    @Override
    int getNewCellValue(int x, int y) {
        int res = 0;
        int cell = getCell(x,y);
        if(cell % 2 == 1){
            res += 2;
        }
        res += (getSNWECNeighboursCountInLayer(x,y, 0) % 2)^(cell /2);
        return res;


       // return  ((getSNWECNeighboursCountWithValue(x, y, 1)% 2) ^ getCell(x,y))== 1 ?1:0;
       //return  ((getSNWECNeighboursCountWithValue(x, y, 1)% 2) )== 1 ?1:0;
    }
    @Override
    public Color getColorAt(int x, int y) {
        switch (getCell(x, y)) {
            case ready:
                return new Color(10, 6, 7);
            case alive:
                return new Color(189, 8, 255);
            case refracted:
                return new Color(194, 57, 114);
            case readyRefracted:
                return new Color(249, 255, 253);
            default:
                return new Color(10, 6, 7);

        }
    }
}

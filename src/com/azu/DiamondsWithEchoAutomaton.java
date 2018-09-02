package com.azu;

import java.awt.*;

public class DiamondsWithEchoAutomaton extends CellularAutomaton {
    static final int ready = 0;
    static final int alive = 1;
    static final int refracted = 2;

    public DiamondsWithEchoAutomaton(int width, int height) {
        super(width, height);
    }
    public void initTestData() {
        /*fillRandom((int) (getCellsCount() * 0.03), new int[]{alive});
        fillRandom((int) (getCellsCount() * 0.03), new int[]{refracted});
        */
        fillRandom((int) (getCellsCount() * 0.03), new int[]{alive});
        fillRandom((int) (getCellsCount() * 0.03), new int[]{refracted});
    }
    @Override
    int getNewCellValue(int x, int y) {
        switch (getCell(x, y)) {
            case ready:
                return getSNWENeighboursCountWithValue(x,y,1) > 0?alive:ready;
            case alive:
                return refracted;
            case refracted:
            default:
                return ready;

        }
    }
    @Override
    public Color getColorAt(int x, int y) {
        switch (getCell(x, y)) {
            case ready:
                return new Color(10, 6, 7);
            case alive:
                return new Color(189, 8, 255);
            case refracted:
                return new Color(194, 159, 191);
            default:
                return new Color(10, 6, 7);

        }
    }
}

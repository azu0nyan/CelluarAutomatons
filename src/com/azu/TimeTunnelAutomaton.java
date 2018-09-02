package com.azu;

import java.awt.*;

public class TimeTunnelAutomaton extends CellularAutomaton {
    static final int ready = 0;
    static final int alive = 1;
    static final int refracted = 2;
    static final int readyRefracted = 3;

    public TimeTunnelAutomaton(int width, int height) {
        super(width, height);
    }

    public void initTestData() {
        //fillRect(width / 2 - 128, height / 2 - 128, 256, 256, 3);
        fillRandomRects(2,2,2,2,32, new int[]{3});
        torus = true;
    }

    @Override
    int getNewCellValue(int x, int y) {
        int prev = getCell(x, y);
        int neigbours = getSNWECNeighboursCountInLayer(x, y, 0);
        int res = (neigbours == 0 || neigbours == 5) ? 0 : 1;
        res ^= getCellLayer(x, y, 1);
        res += (prev % 2) << 1;

        return res;
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

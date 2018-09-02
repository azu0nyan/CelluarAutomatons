package com.azu;

import java.awt.*;

public class TableEWSNCAutomaton extends CellularAutomaton {
    int table[] = new int[32];

    public TableEWSNCAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    int getNewCellValue(int x, int y) {
        int e = getCell(x + 1, y) << 4;
        int w = getCell(x - 1, y) << 3;
        int s = getCell(x, y + 1) << 2;
        int n = getCell(x, y - 1) << 1;
        int c = getCell(x, y);
        int res = e + w +s + n + c;
        return table[res];
    }

}

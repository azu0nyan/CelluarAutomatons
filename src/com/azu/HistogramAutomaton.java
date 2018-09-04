package com.azu;

public class HistogramAutomaton extends CellularAutomaton {
    public HistogramAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    public void initTestData() {
        fillRandom(getCellsCount() / 5, new int[]{1});
        fillRect(0, height - 10, width, 1, 2);
    }

    @Override
    int getNewCellValue(int x, int y) {
        int res = setLayer(0, getCellLayer(x, y, 1), 1);//copy is1 1
        int center = getCell(x, y);
        int north = getCell(x, y - 1);
        int south = getCell(x, y+1);

        if (is0(center, 0)) {
            if (is1(north, 0) && is0(center, 1)) {
               res = setLayer(res, 1, 0);
            }
        }
        if(is1(center, 0)){
            if (is0(south ,0) && is0(south, 1)){
                res = setLayer(res, 0, 0);
            } else {
                res = setLayer(res, 1, 0);
            }
        }
        return res;
    }
}

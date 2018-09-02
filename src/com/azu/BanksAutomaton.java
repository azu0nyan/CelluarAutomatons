package com.azu;

public class BanksAutomaton extends CellularAutomaton {
    public BanksAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    public void initTestData() {
        fillRandom(getCellsCount() * 8/ 9, new int[]{1});
       /* fillRect(width / 4, height * 1 / 5, width / 2, 3, 1);
        fillRect(width / 4, height * 2 / 5, width / 2, 3, 1);
        fillRect(width / 4, height * 3 / 5, width / 2, 3, 1);
        fillRect(width / 4, height * 4 / 5, width / 2, 3, 1);*/
    }

    @Override
    int getNewCellValue(int x, int y) {
        if (getCell(x, y) == 0) {
            return (getSNWENeighboursCountWithValue(x, y, 1) >= 3) ? 1 : 0;
        } else {//==1
            if (getSNWENeighboursCountWithValue(x, y, 1) == 2) {
                return (getCell(x - 1, y) == getCell(x + 1, y)) ? 1 : 0;
            } else {
                return 1;
            }
        }
    }
}

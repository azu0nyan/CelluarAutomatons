package com.azu;

public class ErosionAutomaton extends CellularAutomaton {
    public ErosionAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    public void initTestData() {
        fillRect(0,0,width, height,1);
        fillRandom((int) (getCellsCount() * 0.14), new int[]{0});
    }

    @Override
    int getNewCellValue(int x, int y) {
        int w = getCell(x - 1, y - 1) + getCell(x - 1, y) + getCell(x - 1, y + 1);
        int e = getCell(x + 1, y - 1) + getCell(x + 1, y) + getCell(x + 1, y + 1);
        int n = getCell(x - 1, y - 1) + getCell(x, y - 1) + getCell(x + 1, y - 1);
        int s = getCell(x - 1, y + 1) + getCell(x, y + 1) + getCell(x + 1, y + 1);
        boolean stable = w > 0 && e > 0 && n > 0 && s > 0;
        int res =  getCell(x, y) > 0 && stable?1:0;
        return res;
    }

    @Override
    int getRandomStepCellValue(int x, int y) {
        int w = getCell(x - 1, y - 1) + getCell(x - 1, y) + getCell(x - 1, y + 1);
        int e = getCell(x + 1, y - 1) + getCell(x + 1, y) + getCell(x + 1, y + 1);
        int n = getCell(x - 1, y - 1) + getCell(x, y - 1) + getCell(x + 1, y - 1);
        int s = getCell(x - 1, y + 1) + getCell(x, y + 1) + getCell(x + 1, y + 1);
        boolean stable = w > 0 && e > 0 && n > 0 && s > 0;
        boolean safe = getSNWENeighboursCountWithValue(x, y, 1) == 4;
        if(getCell(x, y) == 1){
            if(safe && stable){
                return 0;
            } else {
                return 1;
            }
        }
        return 0;

    }
}

package com.azu;

import java.awt.*;

public class SimpleCellularAutomaton extends CellularAutomaton {


    public SimpleCellularAutomaton(int width, int height) {
        super(width, height);
    }


    public void automatonStep() {

        int[][] newCells = new int[width][height];
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                if (cells[i][j] == 1) {
                    newCells[i][j] = 1;
                    continue;
                }
                int x = 0;
                x += (cells[i - 1][j - 1] == 1) ? 1 : 0;
                x += (cells[i][j - 1] == 1) ? 1 : 0;
                x += (cells[i + 1][j - 1] == 1) ? 1 : 0;
                x += (cells[i - 1][j] == 1) ? 1 : 0;
                x += (cells[i][j] == 1) ? 1 : 0;
                x += (cells[i + 1][j] == 1) ? 1 : 0;
                x += (cells[i - 1][j + 1] == 1) ? 1 : 0;
                x += (cells[i][j + 1] == 1) ? 1 : 0;
                x += (cells[i + 1][j + 1] == 1) ? 1 : 0;
                if (x == 3) {
                    newCells[i][j] = 1;
                }

            }
        }
        cells = newCells;


    }

    @Override
    int getNewCellValue(int x, int y) {
        return 0;
    }



}

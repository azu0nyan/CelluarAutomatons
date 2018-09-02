package com.azu;

import java.awt.*;

public class ColorfulSimpleCellularAutomaton extends  CellularAutomaton{

    public ColorfulSimpleCellularAutomaton(int width, int height) {
        super(width, height);
    }

    public void automatonStep() {

        int[][] newCells = new int[width][height];
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                if (cells[i][j] > 0) {
                    newCells[i][j] = cells[i][j];
                    continue;
                }
                int x = 0;
                x += (cells[i - 1][j - 1] > 0) ? 1 : 0;
                x += (cells[i][j - 1] > 0) ? 1 : 0;
                x += (cells[i + 1][j - 1] > 0) ? 1 : 0;
                x += (cells[i - 1][j] > 0) ? 1 : 0;
                x += (cells[i][j] > 0) ? 1 : 0;
                x += (cells[i + 1][j] > 0) ? 1 : 0;
                x += (cells[i - 1][j + 1] > 0) ? 1 : 0;
                x += (cells[i][j + 1] > 0) ? 1 : 0;
                x += (cells[i + 1][j + 1] > 0) ? 1 : 0;
                if (x == 3) {
                    newCells[i][j] = (int) (Math.sqrt(iteration* 4096));
                }

            }
        }
        cells = newCells;
    }

    @Override
    int getNewCellValue(int x, int y) {
        return 0;
    }


    public Color getColorAt(int x, int y) {
        if (x >= width || x < 0 || y >= height || y < 0) {
            System.out.println("Wrong cords, cant return cell x:" + x + " y:" + y);
            return Color.BLACK;
        }
        int cell = getCell(x, y);
        if (cell == 0) {
            return new Color(10, 6, 7);
        } else {
            int r = cell % 256 * ((cell /256 % 3 == 0)?1 :0);
            int g = cell % 256 * ((cell /256 % 3 == 1)?1 :0);
            int b = cell % 256 * ((cell /256 % 3 == 2)?1 :0);
            return new Color(r, g, b);

        }

    }


}

package com.azu;

import java.awt.*;

public class RandomMovementAutomaton extends CellularAutomaton {
    public RandomMovementAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    public void initTestData() {
        fillRect(0 , 0, width / 3 , height , 0);
        fillRect(width / 3 , 0, width /  3 , height , 2);
        fillRect(width * 2  / 3, 0, width / 3 , height , 3);
        fillOval(width / 4, height / 4, width / 2, height / 2, 1);

    }

    @Override
    int getNewCellValue(int x, int y) {
        return 0;
    }

    @Override
    void randomStep() {

        int x = r.nextInt(width);
        int y = r.nextInt(height);
        swap(x, y, x + (r.nextInt(3) - 1), y + r.nextInt(3) - 1 );
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

package com.azu;

public class TimeTunnelAutomaton extends CellularAutomaton {

    public TimeTunnelAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    int getNewCellValue(int x, int y) {
        return 0;
    }
}

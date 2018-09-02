package com.azu;

import java.awt.*;

public class HGlassAutomaton extends TableEWSNCAutomaton {
    public HGlassAutomaton(int width, int height) {
        super(width, height);
        table = new int[]
               {0, 1, 1, 1, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 0, 0,
                0, 1, 0, 0, 0, 1, 1, 1};

    }


}

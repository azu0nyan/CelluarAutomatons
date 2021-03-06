package com.azu;

import org.jbox2d.pooling.arrays.IntArray;

import java.awt.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WormsAutomaton extends CellularAutomaton {
    public WormsAutomaton(int width, int height) {
        super(width, height);
    }

    @Override
    public void initTestData() {
        //new int[]{0,1,2,3,4,5,6,7,8,8,10,11,12,13,14,15}
        int timerBits = 2;
        int timerValues = 1 << timerBits;
        fillRandom(IntStream.range(0, 4 * timerValues).toArray());
        respawnTime = IntStream.iterate(0, x -> x).limit(timerValues).toArray();
        respawnTime[0] = 1;
        timerProgression = IntStream.concat(IntStream.of(0).limit(1), IntStream.range(0, timerValues - 1)).toArray();
        timerStart = timerValues - 1;
    }


    int neighboursCountAlarm[] = {0, 0, 1, 0, 1, 1, 1, 1, 1};
    int respawnTime [] = {1, 0, 0, 0};
    int timerProgression [] = {0,0,1,2};
    int timerStart = 3;
    @Override
    int getNewCellValue(int x, int y) {
        int value = getCell(x, y);

        int active = value % 2;
        int alarm = value / 2 % 2;
        int timer = value >> 2;

        int newActive = respawnTime[timer];
        int newAlarm = neighboursCountAlarm[getNeighboursCountInLayer(x, y ,0)];
        int newTimer = (alarm == 1 && active == 1)?timerStart:timerProgression[timer];


        int res =  newActive + (newAlarm << 1) + (newTimer << 2);
        return res;
    }

    public final int inActive = 0;
    public final int active = 1;
    public final int alarmed = 2;
    public final int activeAlarmed = 3;
    public Color getColorAt(int x, int y) {
        int value = getDrawingCell(x, y);
        int withoutTimer = value % 4;
        int timer = value >> 2;
        switch (withoutTimer) {
            case inActive:
                return Utils.blend(new Color(10, 6, 7),  new Color(110, 255, 39),  (timer / (double)timerStart));
            case active:
                return new Color(110, 255, 39);
            case alarmed:
                return new Color(248, 233, 72);
            case activeAlarmed:
                return new Color(255, 21, 16);
            default:
                return new Color(10, 6, 7);

        }
    }

}

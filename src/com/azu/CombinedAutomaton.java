package com.azu;

import java.awt.*;
import java.util.stream.IntStream;

public class CombinedAutomaton extends CellularAutomaton {

    public CombinedAutomaton(int width, int height) {
        super(width, height);
    }


    public void initTestData() {
        torus = true;


        int timerBits = 2;
        int timerValues = 1 << timerBits;
        respawnTime = IntStream.iterate(0, x -> x).limit(timerValues).toArray();
        respawnTime[0] = 1;
        timerProgression = IntStream.concat(IntStream.of(0).limit(1), IntStream.range(0, timerValues - 1)).toArray();
        timerStart = timerValues - 1;
        //fillRandom(IntStream.range(0, 4 * timerValues).toArray());
        for(int x = 0; x < width; x++){
            for(int y =0 ; y < height; y++){
                if((Math.signum( Math.sin(x / 10 + y / 10 ))  == -1)){
                    setXY(x, y,r.nextInt(4 * timerValues));
                } else {
                    if(r.nextInt(1000) == 0){
                       // fillRect(x, y, 2, 2,3);
                    }
                }
            }
        }
    }

    @Override
    int getNewCellValue(int x, int y) {
        return (Math.signum( Math.sin(x / 10 + y / 10 ))  == -1) ? rule1(x, y) : rule2(x, y);
    }


    int neighboursCountAlarm[] = {0, 0, 1, 1, 1, 1, 1, 1, 1};
    int respawnTime [] = {1, 0, 0, 0};
    int timerProgression [] = {0,0,1,2};
    int timerStart = 3;
    int rule1(int x, int y) {
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

    int rule2(int x, int y) {
        int prev = getCell(x, y);
        int neigbours = getSNWECNeighboursCountInLayer(x, y, 0);
        int res = (neigbours == 0 || neigbours == 5) ? 0 : 1;
        res ^= getCellLayer(x, y, 1);
        res += (prev % 2) << 1;

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
                return Utils.blend(new Color(10, 6, 7),  new Color(249, 255, 253),  (timer / (double)timerStart));
            case active:
                return new Color(221, 227, 225);
            case alarmed:
                return new Color(148, 8, 198);
            case activeAlarmed:
                return new Color(204, 16, 14);
            default:
                return new Color(10, 6, 7);

        }
    }


}

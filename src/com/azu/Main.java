package com.azu;

import ru.ege.engine.EGEngine;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        EGEngine.instance().dispose();
        EGEngine.instance().setUndecorated(true);
        EGEngine.instance().setVisible(true);
        EGEngine.instance().startDrawingThread();




        CellularAutomaton automaton = new WormsAutomaton(1920   /1 , 1080  /1);


        Random r = new Random();
        int seed = r.nextInt();
        System.out.println("seed:" +  seed);
        automaton.setSeed(seed);

        automaton.initTestData();

       /* CellularAutomaton testData = new TimeTunnelAutomaton(1920/2, 1080 / 2);
        testData.initTestData();
        for(int i = 0; i < 3000; i++){
            testData.step();
        }

       // ((WormsAutomaton)automaton).timerStart = 3;
        int scale = automaton.width / testData.width;
        for(int i = 0; i < automaton.width; i++){
            for(int j = 0; j < automaton.height; j++){
                int v1 = testData.getCell(i / scale, j / scale);
                int v2 = testData.getCell((automaton.width - i) / scale, (automaton.height - j) / scale);
                automaton.setXY(i, j, v1 + (v2 <<2));
            }
        }*/

        AutomatonDrawer drawer = new AutomatonDrawer(automaton);
        EGEngine.i().addDrawableObject(drawer);


        AutomatonUpdateThread automatonUpdateThread = new AutomatonUpdateThread(automaton);
        automatonUpdateThread.waitBeforeStart = 1000;
        automatonUpdateThread.frameLength = 0;
        EGEngine.i().addKeyListener(automatonUpdateThread);
        new Thread(automatonUpdateThread).start();

    }
}

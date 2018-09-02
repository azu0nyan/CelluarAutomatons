package com.azu;

import ru.ege.engine.EGEngine;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        EGEngine.instance().dispose();
        EGEngine.instance().setUndecorated(true);
        EGEngine.instance().setVisible(true);
        EGEngine.instance().startDrawingThread();


        CellularAutomaton automaton = new TimeTunnelAutomaton(1920 /1   , 1080 /1 );

        Random r = new Random();
        int seed = r.nextInt();
        automaton.setSeed(seed);
        System.out.println("seed:" +  seed);

        automaton.initTestData();


        //CellularAutomaton automaton = new ParityAutomaton(512, 512);
       // automaton.setXY(256, 128 ,1);
       // automaton.fillRandom(1920*1800 / 4 /2, new int []{1});
       // automaton.fillRect(automaton.width / 2 - 16,automaton.height / 2 - 16, 32,32, 1);

        AutomatonDrawer drawer = new AutomatonDrawer(automaton);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EGEngine.i().addDrawableObject(drawer);



       new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long desiredLength = 30;
            while (true){
                long start = System.currentTimeMillis();
                automaton.step();
                long length = System.currentTimeMillis() - start;
                try {
                    Thread.sleep(Math.max( desiredLength - length, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }
}

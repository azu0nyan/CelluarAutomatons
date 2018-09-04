package com.azu;

import ru.ege.engine.EGEngine;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        EGEngine.instance().dispose();
        EGEngine.instance().setUndecorated(true);
        EGEngine.instance().setVisible(true);
        EGEngine.instance().startDrawingThread();


        CellularAutomaton automaton = new TimeTunnelWithPlague(1920 /3   , 1080 /3 );
        CellularAutomaton automaton2 = new TimeTunnelWithPlague(1920 /3   , 1080 /3 );
        ((TimeTunnelWithPlague)automaton2).plagueIteration = 300;
        ((TimeTunnelWithPlague)automaton2).doPlague = true;


        Random r = new Random();
        int seed = r.nextInt();
        System.out.println("seed:" +  seed);
        automaton.setSeed(seed);
        automaton2.setSeed(seed);

        automaton.initTestData();
        automaton2.initTestData();


        //CellularAutomaton automaton = new ParityAutomaton(512, 512);
       // automaton.setXY(256, 128 ,1);
       // automaton.fillRandom(1920*1800 / 4 /2, new int []{1});
       // automaton.fillRect(automaton.width / 2 - 16,automaton.height / 2 - 16, 32,32, 1);

        //AutomatonDrawer drawer = new AutomatonDrawer(automaton);
        AutomatonDrawer drawer = new DoubleAutomatonDrawer(automaton, automaton2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EGEngine.i().addDrawableObject(drawer);



      /* new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long desiredLength = 30;
            while (true){
                long start = System.currentTimeMillis();
                automaton.step();
                automaton2.step();
                long length = System.currentTimeMillis() - start;
                try {
                    Thread.sleep(Math.max( desiredLength - length, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();*/

    }
}

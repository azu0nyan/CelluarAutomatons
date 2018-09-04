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
       /* CellularAutomaton automaton2 = new TimeTunnelWithPlague(1920 /3   , 1080 /3 );
        ((TimeTunnelWithPlague)automaton2).plagueIteration = 300;
        ((TimeTunnelWithPlague)automaton2).doPlague = true;*/


        Random r = new Random();
        int seed = r.nextInt();
        System.out.println("seed:" +  seed);
        automaton.setSeed(seed);
       // automaton2.setSeed(seed);

        automaton.initTestData();
       // automaton2.initTestData();


        //CellularAutomaton automaton = new ParityAutomaton(512, 512);
       // automaton.setXY(256, 128 ,1);
       //automaton.fillRandom(1920*1800 / 16 /2, new int []{1});
       // automaton.fillRect(automaton.width / 2 - 16,automaton.height / 2 - 16, 32,32, 1);

        AutomatonDrawer drawer = new AutomatonDrawer(automaton);
        // AutomatonDrawer drawer = new DoubleAutomatonDrawer(automaton, automaton2);

        EGEngine.i().addDrawableObject(drawer);


        AutomatonUpdateThread automatonUpdateThread = new AutomatonUpdateThread(automaton);
        automatonUpdateThread.waitBeforeStart = 1000;
        automatonUpdateThread.frameLength = 30;
        EGEngine.i().addKeyListener(automatonUpdateThread);
       new Thread(automatonUpdateThread).start();

    }
}

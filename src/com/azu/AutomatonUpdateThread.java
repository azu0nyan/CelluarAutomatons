package com.azu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AutomatonUpdateThread implements Runnable, KeyListener {
    List<CellularAutomaton> automatons = new CopyOnWriteArrayList<>();
    long frameLength = 30;
    long waitBeforeStart = 1000;
    boolean pause = false;

    public AutomatonUpdateThread(CellularAutomaton automaton) {
        if (automaton != null) {
            automatons.add(automaton);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waitBeforeStart);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            long start = System.currentTimeMillis();
            if (!pause) {
                for (CellularAutomaton automaton : automatons) {
                    automaton.step();
                }
            }
            long length = System.currentTimeMillis() - start;
            try {
                Thread.sleep(Math.max(frameLength - length, 0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_E:
            case KeyEvent.VK_PLUS:
                frameLength += 10;
                System.out.println("New frame length:" + frameLength);
                break;
            case KeyEvent.VK_Q:
            case KeyEvent.VK_MINUS:
                frameLength = Math.max(0, frameLength - 10);
                System.out.println("New frame length:" + frameLength);
                break;
            case KeyEvent.VK_PAUSE:
            case KeyEvent.VK_P:
            case KeyEvent.VK_SPACE:
                pause = !pause;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

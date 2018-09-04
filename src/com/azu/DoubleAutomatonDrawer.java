package com.azu;

import java.awt.*;

public class DoubleAutomatonDrawer extends AutomatonDrawer {
    private CellularAutomaton secondAutomaton;

    public DoubleAutomatonDrawer(CellularAutomaton automaton, CellularAutomaton anotherAutomaton) {
        super(automaton);
        this.secondAutomaton = anotherAutomaton;
    }

    @Override
    public void drawAndUpdate(Graphics2D g, double v) {
        secondAutomaton.saveFrameForDrawing();
        super.drawAndUpdate(g, v);
        automaton.step();
        secondAutomaton.step();

    }

    static Color differenceColor = new Color(0,255,0);
    static double differenceWeight = 0.9;
    @Override
    protected Color getColorAt(int px, int py) {
        int firstValue = automaton.getCell(px, py);
        int secondValue = secondAutomaton.getCell(px, py);
        if(firstValue == secondValue){
            return automaton.getColorAt(px, py);
        }

        return blend(automaton.getColorAt(px, py), differenceColor);
    }

    public static Color blend(Color main, Color difference) {
        /*double totalAlpha = main.getAlpha() + difference.getAlpha();
        double weight0 = main.getAlpha() / totalAlpha;
        double weight1 = difference.getAlpha() / totalAlpha;*/
        double weight0 = 1 - differenceWeight;
        double weight1 = differenceWeight;

        double r = weight0 * main.getRed() + weight1 * difference.getRed();
        double g = weight0 * main.getGreen() + weight1 * difference.getGreen();
        double b = weight0 * main.getBlue() + weight1 * difference.getBlue();
        double a = Math.max(main.getAlpha(), difference.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);
    }
}

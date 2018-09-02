package com.azu;

import ru.ege.engine.DrawableObject;
import ru.ege.engine.EGEngine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AutomatonDrawer implements DrawableObject {
    CellularAutomaton celluarAutomaton;
    int left = 0;
    int top =0;
    int sizeX = 0;
    int sizeY = 0;

    public AutomatonDrawer(CellularAutomaton celluarAutomaton) {
        this.celluarAutomaton = celluarAutomaton;
        sizeX = EGEngine.i().getSize().width;
        sizeY = EGEngine.i().getSize().height;
    }

    @Override
    public void drawAndUpdate(Graphics2D g, double v) {

        celluarAutomaton.saveFrameForDrawing();
        drawGrid(g, left,top,sizeX,sizeY, celluarAutomaton.getWidth() , celluarAutomaton.getHeight());
        //celluarAutomaton.step();
    }

    private void drawGrid(Graphics2D g, int left, int top, int sizeX, int sizeY, int pointsX, int pointsY) {
        BufferedImage img = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                int px = (int)(x * ((double)pointsX / (double)sizeX));
                int py = (int)(y * ((double)pointsY / (double)sizeY));
                Color c;


               c =  celluarAutomaton.getColorAt(px, py);

                /*g.setColor(c);
                g.drawLine(x, y, x, y);*/
                img.setRGB(x, y, c.getRGB());
            }
        }
        g.drawImage(img, left, top, null);

    }

}

package com.azu;

import ru.ege.engine.DrawableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AutomatonDrawer implements DrawableObject {
    CellularAutomaton celluarAutomaton;

    public AutomatonDrawer(CellularAutomaton celluarAutomaton) {
        this.celluarAutomaton = celluarAutomaton;
    }

    @Override
    public void drawAndUpdate(Graphics2D g, double v) {

        drawGrid(g, 0,0,1920,1080, celluarAutomaton.getWidth() , celluarAutomaton.getHeight());
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


package com.azu;

import java.awt.*;
import java.util.Random;

public abstract class CellularAutomaton {
    int width;
    int height;
    int cells[][];
    int iteration = 0;

    public CellularAutomaton(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new int[width][height];
    }

    public int getWidth() {
        return width;
    }

    public void initTestData(){

    }
    public void step() {
        long t = System.currentTimeMillis();
        automatonStep();
        long workTime = System.currentTimeMillis() - t;
        iteration++;
        System.out.println("Iteration:" + iteration + " time:" + workTime + "ms");
    }

    void automatonStep() {
        int[][] newCells = new int[width][height];
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                newCells[i][j] = getNewCellValue(i, j);
            }
        }
        cells = newCells;
    }

    abstract int getNewCellValue(int x, int y);

    int getSNWECNeighboursCountInLayer(int x, int y, int layer){
        int res = 0;
        res += getCellLayer(x, y - 1, layer);
        res += getCellLayer(x - 1, y, layer);
        res += getCellLayer(x, y, layer);
        res += getCellLayer(x + 1, y, layer);
        res += getCellLayer(x, y + 1, layer);
        return res;
    }

    int getSNWECNeighboursCountWithValue(int x, int y, int value) {
        int res = 0;
        res += getCell(x, y - 1) == value ? 1 : 0;
        res += getCell(x - 1, y) == value ? 1 : 0;
        res += getCell(x, y) == value ? 1 : 0;
        res += getCell(x + 1, y) == value ? 1 : 0;
        res += getCell(x, y + 1) == value ? 1 : 0;
        return res;
    }



    int getSNWENeighboursCountWithValue(int x, int y, int value) {
        int res = 0;
        res += getCell(x, y - 1) == value ? 1 : 0;
        res += getCell(x - 1, y) == value ? 1 : 0;
        res += getCell(x + 1, y) == value ? 1 : 0;
        res += getCell(x, y + 1) == value ? 1 : 0;
        return res;
    }


    int getNeighboursCountWithValueIncludingMyself(int x, int y, int value) {
        int res = 0;
        res += getCell(x - 1, y - 1) == value ? 1 : 0;
        res += getCell(x, y - 1) == value ? 1 : 0;
        res += getCell(x + 1, y - 1) == value ? 1 : 0;
        res += getCell(x - 1, y) == value ? 1 : 0;
        res += getCell(x, y) == value ? 1 : 0;
        res += getCell(x + 1, y) == value ? 1 : 0;
        res += getCell(x - 1, y + 1) == value ? 1 : 0;
        res += getCell(x, y + 1) == value ? 1 : 0;
        res += getCell(x + 1, y + 1) == value ? 1 : 0;
        return res;
    }

    int getNeighboursCountWithValue(int x, int y, int value) {
        int res = 0;
        res += getCell(x - 1, y - 1) == value ? 1 : 0;
        res += getCell(x, y - 1) == value ? 1 : 0;
        res += getCell(x + 1, y - 1) == value ? 1 : 0;
        res += getCell(x - 1, y) == value ? 1 : 0;
        res += getCell(x + 1, y) == value ? 1 : 0;
        res += getCell(x - 1, y + 1) == value ? 1 : 0;
        res += getCell(x, y + 1) == value ? 1 : 0;
        res += getCell(x + 1, y + 1) == value ? 1 : 0;
        return res;
    }

    public int getHeight() {
        return height;
    }

    public int getCell(int x, int y) {
        return cells[x % width][y % height];
    }
    public int getCellLayer(int x, int y, int layer){
        return (getCell(x,y) >> layer) % 2;
    }

    public void setXY(int x, int y, int value) {
        cells[x % width][y % height] = value;
    }

    public void setSeed(int seed) {
        this.seed = seed;
        r = new Random(seed);
    }

    private int seed = 1;

    Random r = new Random(seed);
    public void fillRandom(int count, int[] values) {
        for (int i = 0; i < count; i++) {
            int rx = r.nextInt(width);
            int ry = r.nextInt(height);
            int rv = values[r.nextInt(values.length)];
            cells[rx][ry] = rv;
        }
    }

    public  void fillRect(int x, int y, int w, int h, int value){
        for(int i = x; i <x + w; i++){
            for(int j = y; j < y + h; j++){
                cells[i % width] [j % height] = value;
            }
        }
    }
    public  void fillOval(int x, int y, int w, int h, int value){
        double centerX = x + w /2.0;
        double centerY = y + h / 2.0;
        double rSquare = Math.pow(Math.max( w / 2.0, h / 2.0  ), 2);
        for(int i = x; i <x + w; i++){
            for(int j = y; j < y + h; j++){
                if(w > h && rSquare >= Math.pow((centerX - i), 2) + Math.pow((centerY - j) * w / (double)h, 2)){
                    cells[i % width] [j % height] = value;
                }
                if(w <= h && rSquare >= Math.pow((centerX - i)* h / (double)w, 2)  + Math.pow((centerY - j), 2)){
                    cells[i % width] [j % height] = value;
                }

            }
        }
    }

    public Color getColorAt(int x, int y){
        if (getCell(x, y) == 1) {
            return new Color(189, 8, 255);
        } else {
            return new Color(10, 6, 7);
        }
    }

    public int getCellsCount(){
        return width * height;
    }
}

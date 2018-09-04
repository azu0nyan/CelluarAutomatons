
package com.azu;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class CellularAutomaton {
    int width;
    int height;
    int cells[][];
    int iteration = 0;
    boolean torus = false;

    boolean concurrent = true;
    int threads = 4;

    public CellularAutomaton(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new int[width][height];
        drawingCells = cells;
    }

    public int getWidth() {
        return width;
    }

    public void initTestData() {

    }


    long totalTime = 0;

    public void step() {
        long t = System.currentTimeMillis();
        automatonStep();
        long workTime = System.currentTimeMillis() - t;
        totalTime += workTime;
        iteration++;
        System.out.println("Iteration:" + iteration + " time:" + workTime + "ms. avg:" + (totalTime / iteration) + "ms.");
    }


    void automatonStep() {
        if (concurrent) {
            concurrentStep();
        } else {
            singleThreadStep();
        }
    }

    private void singleThreadStep() {
        int[][] newCells = new int[width][height];
        int stepFromSides = torus ? 0 : 1;
        for (int i = stepFromSides; i < width - stepFromSides; i++) {
            for (int j = stepFromSides; j < height - stepFromSides; j++) {
                newCells[i][j] = getNewCellValue(i, j);
            }
        }
        cells = newCells;
    }

    ExecutorService taskExecutor = null;

    private void concurrentStep() {
        if (taskExecutor == null) {
            taskExecutor = Executors.newFixedThreadPool(threads);
        }
        int[][] newCells = new int[width][height];
        CountDownLatch latch = new CountDownLatch(threads);
        for (int t = 0; t < threads; t++) {
            //int stepFromSides = torus ? 0 : 1; TODO not only torus now
            int x = width * t / threads;
            int xMax = width * (t + 1) / threads;
            int y = 0;
            int yMax = height;
            taskExecutor.execute(() -> {
                        for (int i = x; i < xMax; i++) {
                            for (int j = y; j < yMax; j++) {
                                newCells[i][j] = getNewCellValue(i, j);
                            }
                        }
                        latch.countDown();
                    }
            );
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cells = newCells;
        }
    }

    abstract int getNewCellValue(int x, int y);

    int getSNWECNeighboursCountInLayer(int x, int y, int layer) {
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

    int getNeighboursCountInLayer(int x, int y, int layer) {
        int res = 0;
        res += getCellLayer(x - 1, y - 1, layer);
        res += getCellLayer(x, y - 1, layer);
        res += getCellLayer(x + 1, y - 1, layer);
        res += getCellLayer(x - 1, y , layer);
        res += getCellLayer(x + 1, y , layer);
        res += getCellLayer(x - 1, y + 1, layer);
        res += getCellLayer(x, y + 1, layer);
        res += getCellLayer(x + 1, y + 1, layer);
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
        return cells[(x + width) % width][(y + height) % height];
        //return cells[x % width][y % height];
    }

    public int getCellsCount() {
        return width * height;
    }

    public int getCellLayer(int x, int y, int layer) {
        return (getCell(x, y) >> layer) % 2;
    }

    public void setXY(int x, int y, int value) {
        cells[x % width][y % height] = value;
    }

    //fillings
    private int seed = 1;
    Random r = new Random(seed);

    public void setSeed(int seed) {
        this.seed = seed;
        r = new Random(seed);
    }
    public void fillRandom(int[] values) {
        for(int i = 0; i < width; i++){
            for(int j = 0 ; j < height; j++){
                int rv = values[r.nextInt(values.length)];
                cells[i][j] = rv;
            }
        }
    }
    public void fillRandom(int count, int[] values) {
        for (int i = 0; i < count; i++) {
            int rx = r.nextInt(width);
            int ry = r.nextInt(height);
            int rv = values[r.nextInt(values.length)];
            cells[rx][ry] = rv;
        }
    }

    public void fillRandomRects(int wmin, int wmax, int hmin, int hmax, int count, int[] values) {
        for (int i = 0; i < count; i++) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            int w = (wmax == wmin) ? wmax : r.nextInt(wmax - wmin) + wmin;
            int h = (hmax == hmin) ? hmax : r.nextInt(hmax - hmin) + hmin;
            int value = values[r.nextInt(values.length)];
            fillRect(x, y, w, h, value);
        }
    }

    public void fillRect(int x, int y, int w, int h, int value) {
        for (int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                cells[i % width][j % height] = value;
            }
        }
    }

    public void fillRandomOvals(int wmin, int wmax, int hmin, int hmax, int count, int[] values) {
        for (int i = 0; i < count; i++) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            int w = (wmax == wmin) ? wmax : r.nextInt(wmax - wmin) + wmin;
            int h = (hmax == hmin) ? hmax : r.nextInt(hmax - hmin) + hmin;
            int value = values[r.nextInt(values.length)];
            fillOval(x, y, w, h, value);
        }
    }

    public void fillOval(int x, int y, int w, int h, int value) {
        double centerX = x + w / 2.0;
        double centerY = y + h / 2.0;
        double rSquare = Math.pow(Math.max(w / 2.0, h / 2.0), 2);
        for (int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                if (w > h && rSquare >= Math.pow((centerX - i), 2) + Math.pow((centerY - j) * w / (double) h, 2)) {
                    cells[i % width < 0? i % width + width : i % width][j % height <0? j % height + height : j % height] = value;
                }
                if (w <= h && rSquare >= Math.pow((centerX - i) * h / (double) w, 2) + Math.pow((centerY - j), 2)) {
                    cells[i % width < 0? i % width + width : i % width][j % height <0? j % height + height : j % height] = value;
                }

            }
        }
    }

    int drawingCells[][] = null;

    void saveFrameForDrawing() {
        drawingCells = cells;
    }

    public Color getColorAt(int x, int y) {
        switch (getDrawingCell(x, y)) {
            case 0:
                return new Color(10, 6, 7);
            case 1:
                return new Color(189, 8, 255);
            case 2:
                return new Color(194, 57, 114);
            case 3:
                return new Color(249, 255, 253);
            default:
                return new Color(10, 6, 7);

        }
    }

    public int getDrawingCell(int x, int y) {
        return drawingCells[(x + width) % width][(y + height) % height];
        //return cells[x % width][y % height];
    }

    //unsafe
    public static int setLayer(int prevValue, int value, int layer) {
        return prevValue + value << layer;
    }

    public static int copyLayer(int source, int dest, int layer) {
        return dest + layer(source, layer) << layer;
    }

    public static boolean is1(int value, int layer) {
        return (value >> layer) % 2 == 1;
    }

    public static boolean is0(int value, int layer) {
        return (value >> layer) % 2 == 0;
    }

    public static int layer(int value, int layer) {
        return (value >> layer) % 2;
    }
}

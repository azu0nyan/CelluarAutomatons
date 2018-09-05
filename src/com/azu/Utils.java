package com.azu;

import java.awt.*;

public class Utils {
    public static Color blend(Color first, Color second, double firstWeight) {
        /*double totalAlpha = main.getAlpha() + difference.getAlpha();
        double weight0 = main.getAlpha() / totalAlpha;
        double weight1 = difference.getAlpha() / totalAlpha;*/
        double weight0 = firstWeight;
        double weight1 = 1 - firstWeight;

        double r = weight0 * first.getRed() + weight1 * second.getRed();
        double g = weight0 * first.getGreen() + weight1 * second.getGreen();
        double b = weight0 * first.getBlue() + weight1 * second.getBlue();
        double a = Math.max(first.getAlpha(), second.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);
    }

    public static  int clamp(int min, int max, int v){
        return Math.max(min,Math.min(max, v));
    }
    static int x;
    static final int a = 1103515245;
    static final int c = 12345;
    static final int m = (int) Math.pow(2, 31);
    public static int fastRandom(){
        x =(a * x + c) % m;
        return x;
    }
}

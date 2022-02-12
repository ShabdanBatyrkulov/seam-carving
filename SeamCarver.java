import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    // create a seam carver object based on the given picture
    private Picture pic;
    public SeamCarver(Picture picture) {
        pic = picture;
    }

    // square of x
    private int sqr(int x) {
        return x * x;
    }

    private boolean check(int x, int y) {
        return 0 <= x && x < width() && 0 <= y && y < height();
    }

    // current picture
    public Picture picture() {
        return pic;
    }

    // width of current picture
    public int width() {
        return pic.width();
    }

    // height of current picture
    public int height() {
        return pic.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (!check(x, y)) {
            throw new IllegalArgumentException("Coordinate (x, y) is out of bounds");
        }
        Color left = pic.get((x - 1 + width()) % width(), y);
        Color right = pic.get((x + 1) % width(), y);
        Color up = pic.get(x, (y - 1 + height()) % height());
        Color down = pic.get(x, (y + 1) % height());
        double res = sqr(left.getRed() - right.getRed()) + sqr(left.getGreen() - right.getGreen()) + sqr(left.getBlue() - right.getBlue()) +
                    sqr(down.getRed() - up.getRed()) + sqr(down.getGreen() - up.getGreen()) + sqr(down.getGreen() - up.getGreen());
        return Math.sqrt(res);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] dp = new double[width()][height()];
        int[][] par = new int[width()][height()];
        for (int x = 0; x < width(); x++) {
            for (int y = 0; y < height(); y++) {
                if (x == 0) {
                    dp[x][y] = energy(x, y);
                } else {
                    double res = Double.MAX_VALUE;
                    if (check(x - 1, y - 1) && dp[x - 1][y - 1] < res) {
                        res = dp[x - 1][y - 1];
                        par[x][y] = -1;
                    }
                    if (check(x - 1, y) && dp[x - 1][y] < res) {
                        res = dp[x - 1][y];
                        par[x][y] = 0;
                    }
                    if (check(x - 1, y + 1) && dp[x - 1][y + 1] < res) {
                        res = dp[x - 1][y + 1];
                        par[x][y] = +1;
                    }
                    dp[x][y] = res + energy(x, y);
                }
            }
        }
        int[] seam = new int[width()];
        int start = -1;
        for (int i = 0; i < height(); i++) {
            if (start == -1 || dp[width() - 1][i] < dp[width() - 1][start]) {
                start = i;
            }
        }
        for (int i = width() - 1; i >= 0; i--) {
            seam[i] = start;
            start += par[i][start];
        }
        return seam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] dp = new double[width()][height()];
        int[][] par = new int[width()][height()];
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                if (y == 0) {
                    dp[x][y] = energy(x, y);
                } else {
                    double res = Double.MAX_VALUE;
                    if (check(x - 1, y - 1) && dp[x - 1][y - 1] < res) {
                        res = dp[x - 1][y - 1];
                        par[x][y] = -1;
                    }
                    if (check(x, y - 1) && dp[x][y - 1] < res) {
                        res = dp[x][y - 1];
                        par[x][y] = 0;
                    }
                    if (check(x + 1, y - 1) && dp[x + 1][y - 1] < res) {
                        res = dp[x + 1][y - 1];
                        par[x][y] = +1;
                    }
                    dp[x][y] = res + energy(x, y);
                }
            }
        }
        int[] seam = new int[height()];
        int start = -1;
        for (int i = 0; i < width(); i++) {
            if (start == -1 || dp[i][height() - 1] < dp[start][height() - 1]) {
                start = i;
            }
        }
        for (int i = height() - 1; i >= 0; i--) {
            seam[i] = start;
            start += par[start][i];
        }
        return seam;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        Picture new_pic = new Picture(width(), height() - 1);
        for (int x = 0; x < width(); x++) {
            int deleted = 0;
            for (int y = 0; y < height(); y++) {
                if (y == seam[x]) {
                    deleted = 1;
                }
                new_pic.set(x, y, pic.get(x, y + deleted));
            }
        }
        pic = new_pic;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        Picture new_pic = new Picture(width() - 1, height());
        for (int y = 0; y < height(); y++) {
            int deleted = 0;
            for (int x = 0; x + 1 < width(); x++) {
                if (x == seam[y]) {
                    deleted = 1;
                }
                new_pic.set(x, y, pic.get(x + deleted, y));
            }
        }
        pic = new_pic;
    }

    //  unit testing (required)
    public static void main(String[] args) {}
}

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    // create a seam carver object based on the given picture
    Picture pic;
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
        if (check(x, y) == false) {
            throw new IllegalArgumentException("Coordinate (x, y) is out of bounds");
        }
        Color left = pic.get((x - 1 + width()) % width(), y);
        Color right = pipc.get((x + 1) % width(), y);
        Color up = pic.get(x, (y - 1 + height()) % height());
        Color down = pic.get(x, (y + 1) % height());
        double res = sqr(left.getRed() - right.getRed()) + sqr(left.getGreen() - right.getGreen()) + sqr(left.getBlue() - right.getBlue()) +
                    sqr(down.getRed() - up.getRed()) + sqr(down.getGreen() - up.getGreen()) + sqr(down.getGreen() - up.getGreen());
        return res;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return new int[];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] dp = new double[height()][width()];
        int[][] par = new int[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                if (i == 0) {
                    dp[i][j] = energy(i, j);
                } else {
                    double res = Double.MAX_VALUE;
                    if (check(i - 1, j - 1) && dp[i - 1][j - 1] < res) {
                        dp[i - 1][j - 1];
                        par[i][j] = -1;
                    }
                    if (check(i - 1, j) && dp[i - 1][j] < res) {
                        res = dp[i - 1][j];
                        par[i][j] = 0;
                    }
                    if (check(i - 1, j + 1) && dp[i - 1][j + 1] < res) {
                        res = dp[i - 1][j + 1];
                        par[i][j] = +1;
                    }
                    dp[i][j] = res + energy(i, j);
                }
            }
        }
        int[] seam = new int[height()];
        int start = -1;
        for (int i = 0; i < width(); i++) {
            if (start == -1 || dp[heigth() - 1][i] < dp[height() - 1][start]) {
                start = i;
            }
        }
        for (int i = height() - 1; i >= 0; i--) {
            seam[i] = start;
            start += par[i][start];
        }
        return seam;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {}

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        Picture new_pic = new Picture(height(), width() - 1);
        for (int i = 0; i < height(); i++) {
            int deleted = 0;
            for (int j = 0; j + 1 < width(); j++) {
                if (j == seam[i]) {
                    deleted = 1;
                }
                new_pic.set(i, j, pic.get(i, j + deleted));
            }
        }
        pic = new_pic;
    }

    //  unit testing (required)
    public static void main(String[] args) {}
}
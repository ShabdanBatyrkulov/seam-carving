import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    // create a seam carver object based on the given picture
    Picture pic;
    public SeamCarver(Picture picture) {
        pic = picture;
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
        Color col = pic.get(x, y);
        double res = 0;
        
        return res;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return new int[]{};
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[]{};
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {}

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {}

    //  unit testing (required)
    public static void main(String[] args) {}
}
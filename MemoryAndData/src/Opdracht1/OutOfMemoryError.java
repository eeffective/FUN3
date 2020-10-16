package Opdracht1;

public class OutOfMemoryError {
    public static void main(String[] args) {
        int[] i = new int[100000 * 100000];
        System.out.println(i);
    }
}


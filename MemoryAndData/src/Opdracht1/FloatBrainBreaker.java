package Opdracht1;

public class FloatBrainBreaker {

    public static void main(String[] args) {
        methodA();
        methodB();
    }

    public static void methodA() {
        int i = 42;
        float f = 1.0f / i;
        System.out.println(0.023809524f * 42); // uitkomst wordt een float >> toevallig 1.0f dus is de uitkomst true
        System.out.println(i * f == 1.0f);
    }

    public static void methodB() {
        int i = 41;
        float f = 1.0f / i;
        System.out.println(0.024390243f * 42); // uitkomst wordt een float >> 1.02... daarom is de uitkomst false
        System.out.println(i * f == 1.0f);
    }
}

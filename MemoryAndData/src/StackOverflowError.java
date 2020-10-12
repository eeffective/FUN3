public class StackOverflowError {
    public static void main(String[] args) {
        methodA();
    }

    public static void methodA() {
        methodA();
    }

}



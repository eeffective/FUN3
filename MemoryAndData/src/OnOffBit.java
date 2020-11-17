public class OnOffBit {

    public static void main(String[] args) {

    }

    void methodA(){
        byte bit = 0;

        for (int i = 0; i < 20; i += 2){
            System.out.println("Value of i: " + i + ", value of bit: " + bit);
            bit = (byte) (bit == 0 ? 1 : 0);
        }

    }

    void methodB(){
        byte bit =0;

        for(int i= 0; i< 20; i+= 2) {
            System.out.println("Value of i: "+ i+ ", value of bit: "+ bit);
            switch (bit) {
                case 1 -> bit = 0;
                case 0 -> bit = 1;
            }
        }
    }
}

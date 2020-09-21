package Oefen;

public class oefen4 {
    public static void main(String[] args) {
        int i = 1;
        if (i>4) {
            System.out.println("Hey ;)");
        }   else    {
            System.out.println("Nee.");
        }
        switch (i) {
            case 1 -> System.out.println("Ik ben 1");
            case 2 -> System.out.println("ik ben 2");
            case 3 -> System.out.println("Ik ben 3");
        }
        int k = 4;
        int j = 5;
        if (k > 3 || j++ < 7) {
            System.out.println("we zijn er al");
        } else {
            System.out.println("we zitten er buiten");
        }
        System.out.println(j); // j++ dan doet hij het nog een keer en dan doet hij er een plus bij
                                // j++ is ene postfix increment
                                // ++j is een prefix increment
    }
}

package Oefen;

//Strings
public class oefen6 {

    public static void main(String[] args) {


        StringBuilder s1 = new StringBuilder("Hoi schat"); //Normaal
        String s2 = "hoi schat";                    //Speciale manier

        System.out.println(s1.equals("Hoi schat")); //Kijk of strings gelijk zijn

        System.out.println("Een \" en \n een tab\ttab");

        try {
            System.out.println(s1.charAt(5)); //Telt het karakter op de 6e plek, hij telt vanaf 0
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("lezen buiten bereik van de string");
        }      //Simpele exception handling


        char c1 = 'a';
        for (int i=0;i<1000;i++){s1.append("a");} //in een for loop strings bouwen

        s1 = null; //zorgt ervoor dat deze variabele wordt opgeruimd.

    }
}


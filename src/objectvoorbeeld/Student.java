package objectvoorbeeld;

public class Student {

    String naam; //instance var
    int leeftijd;
    static int minLeeftijd;

    public Student(String naam, int leeftijd) {

    }

    public void setLeeftijd (int lt){
        if (lt>minLeeftijd&&lt<45) {
            leeftijd=lt;
        }
    }

    public String toString(){
        return "Naam "+naam+" is " +leeftijd+" mag bier drinken.";
    }

    public int getLeeftijd() {
        return leeftijd;
    }
}

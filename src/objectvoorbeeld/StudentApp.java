package objectvoorbeeld;

public class StudentApp {

    public static void main(String[] args) {
        Student s1;    //declaratie
        s1 = new Student("Jacob", 20);  //initalisatie/creatie object
        s1.naam = "Luc";
        s1.setLeeftijd(18);

        Student s2 = new Student("Gabe", 19);
        s2.naam = "Gabe";
        s2.setLeeftijd(19);

        System.out.println(s1.naam);

        Student s3 = new Student("Marty", 753);
        s3.naam = "Martijn";
        s3.setLeeftijd(752);

        System.out.println(s1);
    }
}

//package Oefen;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//
//public class oefen7ReadFile {
//
//    private static BufferedReader inFile;
//
//    public static void main(String[] args) {
//        try {
//            readFile(); //Hierin komt een bestandslocatie
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static void readFile(String bestand) throws IOException{
//        String line;
//            inFile = new BufferedReader(new FileReader(bestand));
//
//            while ((line = inFile.readLine()) != null) {
//            System.out.println(line);
//        }
//        inFile.close();
//
//
//    }
//
//}

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //Arraylist created to store the input from file.
        ArrayList<CardType> list = new ArrayList<>();

        //BufferedReader is used to read the input from a file.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        reader.readLine();
        while( (line = reader.readLine()) != null )
        {
            String[] arrOfStr = line.split(" ", 2);
            CardType cardType = new CardType();
            cardType.Part1 = arrOfStr[0];
            cardType.Part2 = arrOfStr[1];
            list.add(cardType);
        }

        //Below commented code can be used for taking input through keyboard using Scanner.
/*
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no. of cards:");
        int count = sc.nextInt();
        System.out.println("Enter card info");

        for(int i=1;i<=count;i++) {
            Scanner sc1 = new Scanner(System.in);
            String str = sc1.nextLine();
            String[] arrOfStr = str.split(" ", 2);
            CardType cardType = new CardType();
            cardType.Part1 = arrOfStr[0];
            cardType.Part2 = arrOfStr[1];
            list.add(cardType);
        }*/

        setOperations so =new setOperations();                       //Object of setOperations class

        System.out.println("Number of possible SETs: "+ setOperations.findSET(list));

        Boolean Result = true;
        int disjointCount=0;
        System.out.println("      SET      ");
        while(Result){
            Result = setOperations.searchSET(list);
            if(Result) disjointCount++;
        }
        System.out.println("\nDisjoint SETs count: "+disjointCount);
        System.out.println("\n");
    }
}

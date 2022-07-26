import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class setOperations {
    static int counter=0; //To keep a count on the number of SETs formed.

    //Function to check symbols attribute
    public static String symbolCheck(String s){
        if(s.charAt(0)=='$'  || s.charAt(0)=='S' || s.charAt(0)=='s'){
            return "S";
        }
        if(s.charAt(0)=='@'  || s.charAt(0)=='A' || s.charAt(0)=='a'){
            return "A";
        }
        if(s.charAt(0)=='#'  || s.charAt(0)=='H' || s.charAt(0)=='h'){
            return "H";
        }
        return null;
    }

    //Function created to check special characters in the string. This function is used for checking Symbols.
    public static boolean casesCheck(String s){
        String regex = "[^a-zA-Z0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        if(m.matches()){
            return true;
        }else{
            return false;
        }

    }

//setCheck() function is created to check card c1, c2 and c3 is a SET or not. These three cards of the class CardType are
//passed a parameter to the function.
    public static boolean setCheck(CardType c1, CardType c2, CardType c3){

        //flag variable is created to keep a count on the number of attributes matched. If the flag count is 4 then its a SET!
        int flag = 0;

        //Check Color- This code block is written to check the COLOR attribute of the card.
        if(((c1.Part1.equals(c2.Part1)) && (c2.Part1.equals(c3.Part1))) ||
                ((!c1.Part1.equals(c2.Part1)) && (!c2.Part1.equals(c3.Part1)) && (!c1.Part1.equals(c3.Part1)))){
            flag++;
        }

        //Check Symbols- This code block is written to check SYMBOL attribute of the card.
        String x1 = symbolCheck(c1.Part2);
        String x2 = symbolCheck(c2.Part2);
        String x3 = symbolCheck(c3.Part2);

        if(((x1.charAt(0)==x2.charAt(0)) && (x2.charAt(0)==x3.charAt(0))) ||
                ((x1.charAt(0)!=x2.charAt(0)) && (x2.charAt(0)!=x3.charAt(0)) && (x1.charAt(0)!=x3.charAt(0)))){
            flag++;
        }

        //Check Cases- This code block is written to check the uppercase, lowercase and symbol-case attribute of the card.
        String p1 = "0";
        String p2 = "0";
        String p3 = "0";

        if(Character.isUpperCase(c1.Part2.charAt(0))){
            p1="U";
        }else if(Character.isLowerCase(c1.Part2.charAt(0))){
            p1="L";
        }else if(casesCheck(c1.Part2)){
            p1="S";
        }

        if(Character.isUpperCase(c2.Part2.charAt(0))){
            p2="U";
        }else if(Character.isLowerCase(c2.Part2.charAt(0))){
            p2="L";
        }else if(casesCheck(c2.Part2)){
            p2="S";
        }

        if(Character.isUpperCase(c3.Part2.charAt(0))){
            p3="U";
        }else if(Character.isLowerCase(c3.Part2.charAt(0))){
            p3="L";
        }else if(casesCheck(c3.Part2)){
            p3="S";
        }

        if(((p1.charAt(0)==p2.charAt(0)) && (p2.charAt(0)==p3.charAt(0))) ||
                ((p1.charAt(0)!=p2.charAt(0)) && (p2.charAt(0)!=p3.charAt(0)) && (p1.charAt(0)!=p3.charAt(0)))){
            flag++;
        }

        //Check number of symbols- This code block is written to count the number of symbols.
        int c1Length = c1.Part2.length();
        int c2Length = c2.Part2.length();
        int c3Length = c3.Part2.length();

        if(((c1Length==c2Length) && (c2Length==c3Length)) ||
                ((c1Length!=c2Length) && (c2Length!=c3Length) && (c1Length!=c3Length))){
            flag++;
        }
   //If all the 4 attributes are matched then the function will return TRUE and this means the three cards form a SET.
        if(flag==4){
            return true;
        }
        return false;

    }

    //This function is used to find a Disjoint SET(of three cards) from the ArrayList of input.
    public static boolean searchSET(ArrayList<CardType> deck){
        for (int i = 0; i < deck.size(); i++) {
            for (int j = i + 1; j < deck.size(); j++) {
                for (int k = j + 1; k < deck.size(); k++) {
                    if (setCheck(deck.get(i), deck.get(j), deck.get(k))) {
                        List<CardType> tempDeck = new ArrayList<CardType>();
                        tempDeck.add(deck.get(i));
                        tempDeck.add(deck.get(j));
                        tempDeck.add(deck.get(k));
                        Iterator<CardType> it = tempDeck.iterator();
                        System.out.println(" ");
                        while (it.hasNext()) {
                            System.out.println(displayCard(it.next()));
                        }
                        deck.removeAll(tempDeck);
                        return true;
                    }
                }
            }
        }
        return false;
    }

//This function is written to find the count of SETs formed from the ArrayList.
    public static int findSET(ArrayList<CardType> deck){

        for (int i = 0; i < deck.size(); i++) {
            for (int j = i + 1; j < deck.size(); j++) {
                for (int k = j + 1; k < deck.size(); k++) {
                    if (setCheck(deck.get(i), deck.get(j), deck.get(k))) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

//This function is used to display cards.
    public static String displayCard(CardType c) {
        return ("" + c.Part1 + " " + c.Part2 + "");
    }
}

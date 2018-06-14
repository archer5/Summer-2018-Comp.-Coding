package Problem_Set_1_Dynamic_Programming;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Boredom {

    private String num; //number of elements in sequence as a String
    private String sequence[] = new String[100000]; //array of sequence as String elements
    private int numInt; //number of elements in sequence as an int
    private int sequenceInts[] = new int[100000]; //array of sequence as int elements
    private HashMap<Integer, Integer> hashMap = new HashMap<>(); //counts number of occurrences of each value in sequence
    private int score; //output score

    public static void main(String[] args) {

        Boredom boredom = new Boredom();
        boredom.input();
        boredom.numConversion();
        boredom.hashMap();
        boredom.processing(boredom.findGreatestValue());
        boredom.output();

    }

    /**
     * Processes user input for game
     */
    private void input() {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Number of Sequence Elements:");
            num = reader.readLine();

            System.out.println("Input Sequence, separated by spaces:");
            String sequenceString = reader.readLine();
            int lastElement = 0;
            int arrayElement = 0;
            for (int i = 0; i < sequenceString.length(); i++) {

                /*
                Adds number to sequence when a space is reached, incrementing arrayElement afterwards
                 */
                if (sequenceString.charAt(i) == ' ') {
                    sequence[arrayElement++] = sequenceString.substring(lastElement, i);
                    lastElement = i + 1;
                }
                else if (i == sequenceString.length() - 1) { //last element
                    sequence[arrayElement++] = sequenceString.substring(lastElement, i + 1);
                    lastElement = i + 1;
                }

            }

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Converts input string to ints for processing
     */
    private void numConversion() {

        try {
            numInt = Integer.parseInt(num);
        } catch(NumberFormatException e) {
            System.out.println("Error in num conversion");
            e.printStackTrace();
        }

        //may want to check num is equal to the number of elements in sequence here

        try {
            for (int i = 0; i < sequence.length && !(sequence[i] == null); i++) { //if sequence element is not empty
                sequenceInts[i] = Integer.parseInt(sequence[i]);
            }
        } catch(NumberFormatException e) {
            System.out.println("Error in sequence conversion");
            e.printStackTrace();
        }

    }

    /**
     * Creates a HashMap storing all the values in the sequence and the number of times they occur
     */
    private void hashMap() {

        for (int i = 0; i < sequence.length && !(sequence[i] == null); i++) {

            /*
            If already have a hash key for value, increment value (number of occurrences) by 1
             */
            if (hashMap.containsKey(sequenceInts[i])) {
                hashMap.replace(sequenceInts[i], hashMap.get(sequenceInts[i]) + 1);
            }
            /*
            Else add value to hashMap
             */
            else {
                hashMap.put(sequenceInts[i], 1);
            }

        }

    }

    /**
     * Finds the greatest value in the sequence from which to work backwards from
     * @return
     *          Returns greatest value
     */
    private int findGreatestValue() {

        int greatestValue = 0; //will cause instant return of processing() with this initial value

        try {

            greatestValue = sequenceInts[0];
            for (int i = 1; i < sequence.length && !(sequence[i] == null); i++) {
                if (sequenceInts[i] > greatestValue) {
                    greatestValue = sequenceInts[i];
                }
            }

        } catch (NullPointerException e) {
            System.out.println("Invalid Sequence Entered");
            e.printStackTrace();
        }

        return greatestValue;

    }

    /**
     * Processes and finds score
     * @param greatestValue
     *          Greatest value in sequence still to be processed
     */
    private void processing(int greatestValue) {

        if (greatestValue == 0) {
            return;
        }

        try {

            /*
            Calculates which would score more points - deleting the greatest value or the ones around it
             */
            if ((greatestValue - 1) * (hashMap.get(greatestValue - 1)) > (greatestValue * hashMap.get(greatestValue))) {
                this.processing(greatestValue - 1);
            }
            else {
                score += greatestValue * hashMap.get(greatestValue);
                hashMap.remove(greatestValue - 1);
                this.processing(greatestValue - 2);
            }

        } catch (NullPointerException e) { //in this case, greatestValue - 1 or greatestValue does not exist in the sequence, so hashMap.get() returns null
            score += greatestValue * hashMap.get(greatestValue);
            this.processing(greatestValue - 1);
        }

    }

    /**
     * Prints out score
     */
    private void output() {
        System.out.println(score);
    }

}
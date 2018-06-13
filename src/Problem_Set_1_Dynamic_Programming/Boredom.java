package Problem_Set_1_Dynamic_Programming;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boredom {

    private String num; //number of elements in sequence as a String
    private String sequence[] = new String[100000]; //array of sequence as String elements
    private int numInt; //number of elements in sequence as an int
    private int sequenceInts[] = new int[100000]; //array of sequence as int elements

    public static void main(String[] args) {

        Boredom boredom = new Boredom();
        boredom.input();
        boredom.numConversion();

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

}
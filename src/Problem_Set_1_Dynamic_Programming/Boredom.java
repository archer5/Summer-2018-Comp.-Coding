package Problem_Set_1_Dynamic_Programming;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Boredom {

    private String num; //number of elements in sequence as a String
    private ArrayList<String> sequence = new ArrayList<>(); //ArrayList of sequence as String elements

    public static void main(String[] args) {

        Boredom boredom = new Boredom();
        boredom.input();

    }

    /*
    Processes user input for game
     */
    private void input() {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Number of Sequence Elements:");
            num = reader.readLine();

            System.out.println("Input Sequence, separated by spaces:");
            String sequenceString = reader.readLine();
            int lastElement = 0;
            for (int i = 0; i < sequenceString.length(); i++) {
                if (sequenceString.charAt(i) == ' ') {
                    sequence.add(sequenceString.substring(lastElement, i));
                    lastElement = i + 1;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

    }

}
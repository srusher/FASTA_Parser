import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DNA {

    public ArrayList <String> seqID = new ArrayList<String>(); //creating seqID arraylist field
    public ArrayList <String> dnaSeq = new ArrayList<String>(); //creating dnaSeq arraylist field
    public String file1 = ""; //creating file1 string field

    public void readFile(){ //this method is a standard file reading method that stores each line of the file into the file1 field

        int x = 0;
        while (x < 1) {
            try {
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Please enter the name of the FASTA file you would like to import: ");
                String input = scanner1.nextLine();
                File myfile = new File(input); // creating myfile object
                Scanner myReader = new Scanner(myfile); //creating scanner object
                while (myReader.hasNextLine()) { //while loop will execute as long as there is another line in the file
                    file1 += myReader.nextLine(); //adding the current line as a string to file1
                }
                myReader.close();
                x++;
            } catch (FileNotFoundException e) { //catching any file not found exceptions
                System.out.println("\nThe file entered does not exist. Please try again.\n");
            }
        }
    }

    public void getSeqIDandDNA(){ //this method will obtain both the seqID and dnaSeq elements from file1 and add them to each array such that seqID[0] is the corresponding ID for dnaSeq[0]

        int start = 0; //start variable for start index of substrings
        int end = 0; //end variable for end index of substrings
        int count = 0; //count variable used when looking at the dna seq portion of the file

        for (int x = 0; x < file1.length();x++){
            if (file1.charAt(x) == '>'){ //checking to see if the '>' is present
                if (count > 0){ // if count is > 0, then this is not the first ID
                    end = x; //establishing end index for dna substring -- because '>' will come directly after the end of a DNA sequence
                    dnaSeq.add(file1.substring(start,end)); //adding the substring of file1 from positions start to end to the dnaSeq arraylist
                    count = 0; //resetting count to 0 for the next dna sequence
                }
                start = x; //start index of seq ID string in file1
                }

            else if (file1.charAt(x) == '|'){ //if charAt(x) == | then this is the end of the seq ID
                end = x; //setting the end index of seq ID substring
                seqID.add(file1.substring(start+1,end)); //adding the substring to the seqID arraylist
                start = 0; //resetting start index variable
                end = 0; //resetting end index variable
            }
            else if (file1.charAt(x) == 'A' || file1.charAt(x) == 'T' || file1.charAt(x) == 'G' || file1.charAt(x) == 'C'){ //if this statement is true then we are looking at a DNA sequence
                if (count == 0){ //count is always equal to 0 for the first nucleotide found in a sequence
                    start = x; //setting start index of this dna seq
                    count++; //incrementing count so this if statement won't execute until the next DNA sequence
                }
                else if (x == file1.length()-1){ //checking to see if this is the last character of the file
                    end = x; //setting end index to x
                    dnaSeq.add(file1.substring(start,end)); //adding the substring to the dnaSeq arraylist
                }
                else{
                    continue; //continuing the loop if none of the above statements execute
                }

            }

        }

    }


}
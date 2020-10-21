import java.util.Scanner;

public class FASTA_Parser {

    public static void main(String[] args){

        DNA seq1 = new DNA(); //instantiating DNA class

        seq1.readFile(); //calling the readfile() method from DNA class

        seq1.getSeqIDandDNA(); //calling get seqIDandDNA() method from DNA clas
        int y = 0;
        while (y == 0){ //creating while loop to keep asking for the user's input
            Scanner scanner1 = new Scanner(System.in); //creating scanner object
            System.out.println("\nPlease enter a Sequence ID (Enter \"Quit\" to exit): "); //prompting user for input
            String input = scanner1.nextLine(); //reading user's input

            if (seq1.seqID.contains(input)){ //checks to see if the input is in the seqID arraylist
                int dnaIndex = seq1.seqID.indexOf(input); //setting the dnaIndex to the index of the input in the seqID arraylist -- each arraylist element for seqID and dnaSeq corresponds to that element in the opposite arraylist
                System.out.println(">"+input);
                int count = 0;
                String dna_string = seq1.dnaSeq.get(dnaIndex);
                for (int x = 0; x < dna_string.length(); x++){ //this for loop will print each at most 50 nucleotides on one line before starting a new one
                    if (count >= 50){
                        System.out.println();
                        count = 0;
                    }
                    System.out.print(dna_string.charAt(x));
                    count++;
                    if (x == dna_string.length()-1){
                        System.out.print("\n");
                    }

                }
            }

            else if (input.toLowerCase().equals("quit")){ //checking to see if input is "quit"
                break; // terminating while loop
            }

            else{
                System.out.println("The Sequence ID: "+input+" is not contained in the file."); //if the input doesn't equal either of the previous statements, this error message is displayed
            }

        }

    }


}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Test
{

    public static void main(String arg[])
    {
        Scanner in = new Scanner(System.in);   
        MyArrayList<String> defaultWord = new MyArrayList();         //Creat a new array list with default word.
        MyArrayList<char []> output = new MyArrayList();             //Create a new array list to store returned list.

        String wordE, wordFF;                                        //Variables to store the word entered and the word from the list.
        File f1=new File("input.txt");    
        int counter = 0;                                             //checks index being read from file
        Random rand = new Random();
        int guessIndex = rand.nextInt(2499);                         //used as index for word to guess  
        boolean valid = false;

        for(int x=0;x<5;x++)    //run 5 time user get 5 guesses.
        {
            System.out.println("Guess the five-letter word.");
            wordE = in.nextLine();
            if(wordE.length() == 5)    //Check if word entered is a 5 letter word.
            {
                try{ //Open the file to read the words in the list.
                    FileReader fr = new FileReader(f1);
                    BufferedReader br = new BufferedReader(fr);
                    while((wordFF=br.readLine())!=null)
                    {
                        if(wordFF.equals(wordE))//check if the word entered is in the list
                        {
                            valid = true;    
                        }
                        
                        if(counter == guessIndex)   //get a random word to guess
                        {
                            int size = defaultWord.size();
                            defaultWord.add(size, wordFF);  //add word to list at available position
                            System.out.println(wordFF);
                        }
                        counter++;                        
                    }
                       
                    if(valid)
                    {
                        output.add(output.size(),defaultWord.checkChar(wordE));    //Call checkChar to check the validity of the chars and store the info.
                    }

                    fr.close();
                }catch (IOException e) 
                {
                    System.out.println("File Read Error");
                } 
            }else valid = false; 
            
            System.out.println("\fWordle [Attempt "+ (x+1) + "]");
            System.out.println("The entered word is a valid 5 letter word: " + valid); 

            if(valid)
            {
                //display the feedback of the entered word
                
                for(int k = 0; k < output.size();k++)
                {
                    String str = "[";
                    char [] result = output.get(k);
                    for(int i = 0; i < 5; i++)
                    {
                        str += result[i];
                        if(i < 4)
                            str += ",";
                    }
                    System.out.printf("%s]\n",str); 
                }

                System.out.println("----------------------------------------------------------------");
                String str = "[";
                for(int i = 0; i < 5; i++)
                {
                    str += wordE.charAt(i);
                    if(i < 4)
                        str += ",";
                }
                System.out.printf("%s]\n",str);
                System.out.println("----------------------------------------------------------------\n■ - valid characters in correct positions\n□ - valid characters, but not in correct position\n▨ - Not a valid character\n----------------------------------------------------------------");

                //display the result of the feedback (Winner, Try again, Looser)
                if(output.checkWin())
                {
                    System.out.println("Winner\n");
                    break;
                }
                else
                if(x < 4)
                {
                    System.out.println("Try again\n");
                }
            }
            valid = false;            
        }
        if(!valid)
            System.out.println("Loser\nThe word was: " + defaultWord.get(defaultWord.size()-1));
            
        output.clear();
    }  

}
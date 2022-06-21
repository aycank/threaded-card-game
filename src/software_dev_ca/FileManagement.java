package software_dev_ca;

import java.io.*;
import java.util.ArrayList;

public class FileManagement
{      
	
	//Reading the file
	public static ArrayList<Card> readFile(String pathOfPack){  
        ArrayList<Card> cardDeck = new ArrayList<>();
        int cardNumber;
        try {
        	FileReader fr = new FileReader(pathOfPack);
        	//BufferedReader is to read a file, it will read the PackPath that the user inputs
        	//Pass fr into BufferedReader
            BufferedReader fileRead = new BufferedReader(fr);
            String line = fileRead.readLine();
            //While the PackPath lines are read
            while(line!= null){
                cardNumber = Integer.parseInt(line);
                if (cardNumber > 0) {
                	Card cd = new Card(cardNumber);
                    cardDeck.add(cd);
                    line = fileRead.readLine();
                 //If the card number in the text file is less than zero, clear the cardDeck and stop the loop
                }else {
                    System.out.println("Integer must be positive.");
                    cardDeck.clear();
                    line = null;
                }
            }
            //Close the file reader
            fileRead.close();
        //If the file doesn't contain an integer
        } catch(NumberFormatException e) {
            System.out.println("Content is not an integer.");
            cardDeck.clear();
        //If the file is an invalid path
        } catch (IOException e) {
        	System.out.println("Invalid file path.");
        	cardDeck.clear();
        }         
        return cardDeck;
    }
	
	//Writing in the file
    public static void writeFile(String text, String pathOfPlayer) {
        try {
            File file = new File(pathOfPlayer);
            //Passed "true" in second argument to FileWriter to turn on append mode
            FileWriter fw = new FileWriter(file, true);
            //Pass fw into BufferedWriter
            BufferedWriter fWrite = new BufferedWriter(fw);
            fWrite.write(text);
            //New line so it does not write on the same line all the time
            fWrite.newLine();
            //Close the file writer
            fWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    //Clearing the file
    public static void clearFile(String filePath){
        File file = new File(filePath);
        file.delete();
    }
    	
}
package main.FileFormat;

import main.CreditCard.*;
import main.Handler.*;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CsvFile extends FileFormat{

    @Override
    public ArrayList<CreditCard> readFromFile(String inputPath) {

        //create concrete handlers
        InvalidCCHandler invalidCCHandler = new InvalidCCHandler(); 
         MasterCCHandler masterCCHandler = new MasterCCHandler();
        VisaCCHandler visaCCHandler = new VisaCCHandler();
        AmExCCHandler amExCCHandler = new AmExCCHandler();
        DiscoverCCHandler discoverCCHandler = new DiscoverCCHandler();

        //set successor
        invalidCCHandler.setSuccessor(masterCCHandler);
        masterCCHandler.setSuccessor(visaCCHandler);
        visaCCHandler.setSuccessor(amExCCHandler);
        amExCCHandler.setSuccessor(discoverCCHandler);

        ArrayList<CreditCard> creditCards = new ArrayList<>();

        System.out.println("Reading from csv file...");
        File csvFile = new File(inputPath);

        String line;
        String csvSplitBy = ",";
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        int i = 1; //which line? the first line need no handler

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            while ((line = br.readLine()) != null){
                if (i > 1){
                    String[] record = line.split(csvSplitBy);
                    String cardNumber = record[0];
                    Date expirationDate = dateFormat.parse("01/02/2022");
                    String nameOfCardHolder = record[2];
                    //for each record, use chainOfResponsibility(start from MasterCCHandler) to determine card type
                    //if it's a valid card, handler will create a credit card object, then add to ArrayList
                    creditCards.add(invalidCCHandler.checkCreditCard(cardNumber, expirationDate, nameOfCardHolder));
                }
                i++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

//        System.out.println("Test read output: holderName, ExpireDate, CardNumber, Type, isValid:");
//        for (CreditCard creditCard : creditCards) {
//            System.out.println(creditCard.getNameOfCardHolder() + " " + creditCard.getExpirationDate() + " " + creditCard.getCardNumber() + " " + creditCard.getType() + " " + creditCard.getValid());
//        }

        return creditCards;
    }

    @Override
    public boolean writeToFile(ArrayList<CreditCard> creditCards, String outputPath) {

        System.out.println("Writing to csv file...");
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPARATOR = "\n";
        File csvFile = new File(outputPath + "output.csv");

        try (PrintWriter printWriter = new PrintWriter(csvFile)) {
            StringBuilder sb = new StringBuilder();
            //first line TAG
            sb.append("CardNumber");
            sb.append(COMMA_DELIMITER);
            sb.append("Type");
            sb.append(NEW_LINE_SEPARATOR);

            //className objectName : arraylist
            for (CreditCard creditCard : creditCards) {
                sb.append(creditCard.getCardNumber());
                sb.append(COMMA_DELIMITER);
                sb.append(creditCard.getType());
                if (!creditCard.getValid()){
                    sb.append(":" + creditCard.getError());
                }
                sb.append(NEW_LINE_SEPARATOR);
            }
            printWriter.write(sb.toString());
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
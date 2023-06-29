package main.Handler;

import main.CreditCard.*;
import java.util.Date;

public class InvalidCCHandler implements Handler{

    private Handler successor = null;

    @Override
    //If it is master card, create new master card object and return, otherwise forward to VisaCC Handler
    public CreditCard checkCreditCard(String cardNumber, Date expirationDate, String nameOfCardHolder) {
        System.out.println("MasterCCHandler got the request...");
        if( cardNumber.isEmpty() || cardNumber.isBlank() || cardNumber.length() == 0){
            return  new MasterCC(cardNumber, expirationDate, nameOfCardHolder, false, "Invalid","empty/null card number");
         }
        
         if(! cardNumber.matches("^[0-9]+$")){
            return  new MasterCC(cardNumber, expirationDate, nameOfCardHolder, false, "Invalid","non numeric characters");
         }

         if(  cardNumber.length() > 19){
            return  new MasterCC(cardNumber, expirationDate, nameOfCardHolder, false, "Invalid"," more than 19 digits");
         }

        else {
            if (successor != null)
                return successor.checkCreditCard(cardNumber, expirationDate, nameOfCardHolder);
        }
        return null;
    }

    @Override
    public void setSuccessor(Handler next) {
        this.successor = next;
    }
}

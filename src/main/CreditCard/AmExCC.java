package main.CreditCard;

import java.util.Date;

public class AmExCC extends CreditCard{
    public AmExCC(String cardNumber, Date expirationDate, String nameOfCardHolder, boolean isValid, String type,String error) {
        super(cardNumber, expirationDate, nameOfCardHolder, isValid, type,error);
    }
}

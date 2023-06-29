package main.CreditCard;

import java.util.Date;

public class DiscoverCC extends CreditCard{
    public DiscoverCC(String cardNumber, Date expirationDate, String nameOfCardHolder, boolean isValid, String type,String error) {
        super(cardNumber, expirationDate, nameOfCardHolder, isValid, type,error);
    }
}

package main.CreditCard;

import java.util.Date;

abstract public class CreditCard {

    private String cardNumber;
    private Date expirationDate;
    private String nameOfCardHolder;
    private boolean isValid;
    private String type;
    private String error;

    public CreditCard (String cardNumber, Date expirationDate, String nameOfCardHolder, boolean isValid, String type,String error){
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.nameOfCardHolder = nameOfCardHolder;
        this.isValid = isValid;
        this.type = type;
        this.error = error;
    }

    public String getCardNumber(){return this.cardNumber;}
    public Date getExpirationDate(){return this.expirationDate;}
    public String getNameOfCardHolder(){return this.nameOfCardHolder;}
    public String getType(){return this.type;}
    public boolean getValid(){return this.isValid;}
    public String getError(){return this.error;}

}

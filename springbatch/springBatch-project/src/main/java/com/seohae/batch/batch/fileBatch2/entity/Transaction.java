package com.seohae.batch.batch.fileBatch2.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Michael Minella
 */
@XmlType(name = "transaction")
@Getter
@Setter
public class Transaction {

    private String accountNumber;
    private Date transactionDate;
    private Double amount;

    private DateFormat formatter =
            new SimpleDateFormat("MM/dd/yyyy");

    public String getDateString() {
        return this.formatter.format(this.transactionDate);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "accountNumber='" + accountNumber + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                '}';
    }
}
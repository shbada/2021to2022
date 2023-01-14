package com.seohae.batch.batch.fileBatch2.job;

import com.seohae.batch.batch.fileBatch2.entity.Transaction;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class TransactionFieldSetMapper implements FieldSetMapper<Transaction> {

    /**
     * 데이터 타입을 읽어와, 타입 변환 커스텀 메서드
     * @param fieldSet
     * @return
     * @throws BindException
     */
    @Override
    public Transaction mapFieldSet(FieldSet fieldSet) throws BindException {
        Transaction trans = new Transaction();
        trans.setAccountNumber(fieldSet.readString("accountNumber"));
        trans.setAmount(fieldSet.readDouble("amount"));
        trans.setTransactionDate(fieldSet.readDate("transactionDate", "yyyy-MM-dd HH:mm:ss"));

        return trans;
    }
}

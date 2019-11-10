package com.example.securevotes;

import java.io.Serializable;
import java.security.PublicKey;

class TransactionOutput implements Serializable {
    private static final long serialVersionUID = 100L;

    public String id;
    public PublicKey reciepient; //also known as the new owner of these coins.
    public String parentTransactionId; //the id of the transaction this output was created inp
    public float value;
    //Constructor
    public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
        this.reciepient = reciepient;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringHelper.applySHA256(StringHelper.getStringFromKey(reciepient)+parentTransactionId);
    }

    //Check if coin belongs to you
    public boolean isMine(PublicKey publicKey) {
        return (publicKey == reciepient);
    }

}

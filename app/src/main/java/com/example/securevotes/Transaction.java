package com.example.securevotes;

import com.example.securevotes.StringHelper;

import java.io.Serializable;
import java.security.*;
import java.util.ArrayList;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 500L;

    public String transactionId; // this is also the hash of the transaction.
    public PublicKey sender; // senders address/public key.
    public PublicKey reciepient; // Recipients address/public key.
    public float value; //Contains the amount we wish to send to the recipient.
    public byte[] signature; // this is to prevent anybody else from spending funds in our wallet.

    private static int sequence = 0; // a rough count of how many transactions have been generated.

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    // Constructor:
    public Transaction(PublicKey from, PublicKey to, float value,  ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.reciepient = to;
        this.inputs = inputs;
        this.value = value;
    }

    // This Calculates the transaction hash (which will be used as its Id)
    private String calulateHash() {
        sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
        return StringHelper.applySHA256(
                StringHelper.getStringFromKey(sender) +
                        StringHelper.getStringFromKey(reciepient)
        );
    }
    public float getOutputsValue() {
        float total = 0;
        for(TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }

    public float getInputsValue() {
        float total = 0;
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue; //if Transaction can't be found skip it, This behavior may not be optimal.
            total += i.UTXO.value;
        }
        return total;
    }

    //Signs all the data we dont wish to be tampered with.
    public void generateSignature(PrivateKey privateKey) {
        String data = StringHelper.getStringFromKey(sender) + StringHelper.getStringFromKey(reciepient);
        signature = StringHelper.applyECDSASig(privateKey, data);
    }

    //Verifies the data we signed hasnt been tampered with
    public boolean verifiySignature() {
        String data = StringHelper.getStringFromKey(sender) + StringHelper.getStringFromKey(reciepient);
        return StringHelper.verifyECDSASig(sender, data, signature);
    }

    public boolean processTransaction() {

        if(verifiySignature() == false) {
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }

        //Gathers transaction inputs (Making sure they are unspent):
        for(TransactionInput i : inputs) {
            i.UTXO = MainActivity.UTXOs.get(i.transactionOutputId);
        }



        //Checks if transaction is valid:
        if(getInputsValue() < MainActivity.minimumTransaction) {
            System.out.println("Transaction Inputs too small: " + getInputsValue());
            System.out.println("Please enter the amount greater than " + MainActivity.minimumTransaction);
            return false;
        }

        //Generate transaction outputs:
        float leftOver = getInputsValue() - value; //get value of inputs then the left over change:
        transactionId = calulateHash();
        outputs.add(new TransactionOutput( this.reciepient, value, transactionId)); //send value to recipient
        outputs.add(new TransactionOutput( this.sender,leftOver, transactionId)); //send the left over 'change' back to sender

        //Add outputs to Unspent list
        for(TransactionOutput o : outputs) {
            MainActivity.UTXOs.put(o.id , o);
        }

        //Remove transaction inputs from UTXO lists as spent:
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue; //if Transaction can't be found skip it
            MainActivity.UTXOs.remove(i.UTXO.id);
        }

        return true;
    }


}

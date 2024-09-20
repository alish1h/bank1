package com.bank1;

import java.io.Serializable;

public class TransferRequest implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	    private String fromUser;
	    private String toUser;
	    private double amount;
	    private boolean accepted;

	    public TransferRequest(String fromUser, String toUser, double amount) {
	        this.fromUser = fromUser;
	        this.toUser = toUser;
	        this.amount = amount;
	        this.accepted = false;
	    }

	    public String getFromUser() 
	    { 
	    	return fromUser; 
	    	
	    }
	    public String getToUser() 
	    { 
	    	return toUser; 
	    	
	    }
	    public double getAmount() 
	    {
	    	return amount;
	    
	    }
	    public boolean isAccepted() 
	    { 
	    	return accepted; 
	    	
	    }
	    
	    public void accept() 
	    {
	    	accepted = true; 
	    	
	    }
	}



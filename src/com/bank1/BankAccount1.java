package com.bank1;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BankAccount1 implements Serializable
{

	private static final long serialVersionUID = 1L;
	private List<TransferRequest> requests = new ArrayList();
	private String mobNumber;
	private String user;
	private double balance;
	private double interestRate;
	
	public BankAccount1(String mob, String user, double balance,double interestRate) {
		super();
		this.mobNumber = mob;
		this.user = user;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	
	public void applyInterest(double inte) {
        double interest = (balance * interestRate) / 100;
        balance += interest+inte;
        System.out.printf("Interest of %.2f Rs applied. New balance: %.2f Rs\n", inte, balance);
    }
	
	public void deposit(double amount)
	{
		if(amount>0)
		{
			DecimalFormat df = new DecimalFormat("#.00");
	        String formattedBalance = df.format(amount);
		     balance=balance+amount;
			System.out.println("Amount deposited..."+formattedBalance+" Rs");
		}
		
		else
		{
			System.out.println("Invalid deposit Amount.");
		}
	}
	
	public void withdraw(double amount)
	{
		if(amount >0)
		{
			if(balance>=amount)
			{
				balance=balance-amount;
				DecimalFormat df = new DecimalFormat("#.00");
		        String formattedBalance = df.format(amount);
				System.out.println("Amount withdrawn :"+formattedBalance +" Rs");
			}
			else
			{
				System.out.println("Insufficiet bucks...");
			}
		}
	else
		{
			System.out.println("Invalid withdrawl amount");
		}
	   	
	}

	public void check()
	{
		DecimalFormat df = new DecimalFormat("#.00");
        String formattedBalance = df.format(balance);
	System.out.println("Account balane for :"+user +" "+formattedBalance+" Rs");	
	}

	public void display()
	{
		DecimalFormat df = new DecimalFormat("#.00");
        String formattedBalance = df.format(balance);
		System.out.println("Mobile number :" +mobNumber);
		System.out.println("User name :"+user);
		
			System.out.println("Amount :"+formattedBalance +"Rs");

			
			}
	
	//=============================================================================
	
	public void sendTransferRequest(BankAccount1 toAccount, double amount) {
        if (amount > 0 && balance >= amount) {
            TransferRequest request = new TransferRequest(this.mobNumber, toAccount.mobNumber, amount);
            
            toAccount.receiveTransferRequest(request,this.user,toAccount.user);
            System.out.println("Transfer request sent to " + toAccount.user +" from "+this.user);
        } else {
            System.out.println("Insufficet bucks..Requested amount is greater than balance of "+toAccount.user);
        }
    }

    public void receiveTransferRequest(TransferRequest request,String from, String to) {
        requests.add(request);
        System.out.println(to+" You have received a transfer request of " + request.getAmount() + " from " + from);
    }
    
    public void respondToTransferRequest(int index, boolean accept) {
        if (index >= 0 && index < requests.size()) {
            TransferRequest request = requests.get(index);
            if (accept) {
                withdraw(request.getAmount());
                System.out.println("Transfer of " + request.getAmount() + " accepted from "+request.getToUser());
                request.accept();
            } else {
                System.out.println("Transfer of " + request.getAmount() + " rejected.");
            }
            requests.remove(index);
        } else {
            System.out.println("Invalid request index.");
        }
    }

    public void displayRequests() {
        for (int i = 0; i < requests.size(); i++) {
            TransferRequest request = requests.get(i);
            System.out.println("Request " + (i + 1) + ": " + request.getAmount() + " from " + request.getFromUser());
        }
    }
    
    public int getRequestCount() {
        int a= requests.size(); 
        
        return a;
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    //All changes done

}

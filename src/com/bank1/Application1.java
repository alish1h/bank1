package com.bank1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.bank1.Application1;
import com.bank1.BankAccount1;

public class Application1 implements Serializable
{
	private static final long serialVersionUID = 1L;
	Map<String, BankAccount1> m=new HashMap<>();
	static String data="NewApp.txt";

	public void openAccount(Scanner sc) {
		String mob=getInput(sc);
		
		if(m.containsKey(mob))
		{
			System.out.println("Account with this number is already available.");
			
		}
		else
		{
			String name="";
			while(true)
			{
				System.out.println("Enter name:");
				 name=sc.nextLine();
				 if(name.matches("[a-zA-Z]+") || name.matches("[a-zA-Z]+([ '_][a-zA-Z]+)"))
				 {
					 break;
				 }
				 else
				 {
					 System.out.println("Invalid name");
				 }
			}
		double b=getDInput(sc);
        double interestRate=getDInput(sc);
        BankAccount1 newAccount=new BankAccount1(mob, name, b,interestRate);
		m.put(mob, newAccount);
		System.out.println("Account created Suuccessfully...");
		
		}
	}
	
	public void checkBalance(Scanner sc)
	{
		String s=getInput(sc);
		BankAccount1 b=m.get(s);
		if(b !=null)
		{
			b.check();
		}
		else
		{
			System.out.println("Account linked to mobile not found..");
		}
		
	}
	
	public void withdraw(Scanner sc)
	{
		String mob=getInput(sc);
		BankAccount1 b=m.get(mob);
		if(b !=null)
		{
			System.out.println("Amount to withdraw :");
			double inr=getDInput(sc);
			b.withdraw(inr);
		}
		else
	{
		System.out.println("Account not found.");
	}
	
	}

public void deposit(Scanner sc)
{
	String mob=getInput(sc);
	BankAccount1 b=m.get(mob);
	if(b !=null)
	{
		System.out.println("Amount to deposit :");
		double inr=getDInput(sc);
		b.deposit(inr);
	}
	else
	{
		System.out.println("Account not found.");
	}
	
}

public void display()
{
	if(m.isEmpty())
	{
		System.out.println("No accounts Found.");
	}
	else
	{
		for(BankAccount1 b :m.values())
		{
			b.display();
			System.out.println("====================");
		}
	}
}

public static void main(String[] args) {
	Application1 a=new Application1();
	System.out.println("Welcome to Tcs Bank.");
	Scanner sc=new Scanner(System.in);
	a.loadData();
	
	
	
	while(true)
	{
		
		System.out.println("1. Open Account");
		System.out.println("2. Check Balance");
		System.out.println("3. Withdraw Money");
		System.out.println("4. Deposit Money");
		System.out.println("5. Display All Accounts");
		System.out.println("6. To request money");
		System.out.println("7. Response");
		System.out.println("8. Apply Interest to All Accounts");
		System.out.println("9. Exit");
		int a1=sc.nextInt();
		switch (a1) {
		case 1: a.openAccount(sc);
		break;
		
		case 2: a.checkBalance(sc);
		break;
		
		case 3: a.withdraw(sc);
		break;
		
		case 4: a.deposit(sc);
		break;
		
		case 5:a.display();
		break;
		
		case 6: 
			a.sendMoneyRequest(sc); 
			break;
			
		case 7: 
			 a.respondToRequest(sc);
			break;
			
		case 8:
			a.applyInterestToAllAccounts(sc);
			break;
			
		case 9:
			a.saveData();
			sc.close();
			System.out.println("Thank you...");
			System.exit(0);
			break;
			
		default: System.out.println("Invalid input.");
}

}
	
	
}

public  void saveData() 
{
	try
	{
		FileOutputStream fos=new FileOutputStream(data);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(m);
	}
	catch(Exception e)
	{
		System.out.println("Error :"+e.getMessage());
	}
}

public  void loadData()
{
	try 
	{
		FileInputStream fis=new FileInputStream(data);
		ObjectInputStream ois=new ObjectInputStream(fis);
		m=(Map<String, BankAccount1>)ois.readObject();
		
	}
	catch (FileNotFoundException e)
	{
		System.out.println("No data at start..");
	}
	catch(Exception e)
	{
		System.out.println("Error loading data :"+e.getMessage());
	}
}

public String getInput(Scanner sc)
{
	sc.nextLine();
	String inp="";
	while(true)
	{
		System.out.println("Enter Mobile Number :");
		try {
			inp=sc.nextLine();
			if(inp.matches("\\d{10}"))
			{
				break;
			}
			else
			{
				System.out.println("Invalid mobile number..");
			}
			
		} catch (Exception e) {
			System.out.println("Invalid mobile number...");
		}

	}
	return inp;
}



public double getDInput(Scanner sc)
{
	double inr;
	while(true)
	{
		System.out.println("Enter Amount :");
		try {
			String a=sc.nextLine();
			if(a.matches("\\d+\\.?\\d{0,2}"))
			{
				inr=Double.parseDouble(a);
				if(inr<=0)
				{
					System.out.println("Invalid amount..Amount should be greater than 0");
					continue;
				}
				break;
			}
			else
			{
				System.out.println("Invalid amount...");
			}
			
		} catch (Exception e) {
			System.out.println("Invalid amount..");
		}
	}
	return inr;
}

//============================================================================
public void sendMoneyRequest(Scanner sc) {
	System.out.println("Requesting Money.");
    String fromMob = getInput(sc);
    BankAccount1 fromAccount = m.get(fromMob);
    if (fromAccount != null) {
    	System.out.println("Mobile number To request from.");
        String toMob = getInput(sc);
        BankAccount1 toAccount = m.get(toMob);

        if (toAccount != null) {
            System.out.println("Amount to request:");
            double amount = getDInput(sc);
            fromAccount.sendTransferRequest(toAccount, amount);
        } else {
            System.out.println("Recipient account not found.");
        }
    } else {
        System.out.println("Your account not found.");
    }
}

public void respondToRequest(Scanner sc) {
    String mob = getInput(sc);
    BankAccount1 account = m.get(mob);

    if (account != null) {
        account.displayRequests();
        int a=account.getRequestCount();
        if(a==0)
        {
        	System.out.println("No Request Pending..");
        }
        else
        {
        	int index;
        	while(true)
        	{
        		System.out.println("Enter the request index to proceed:");
                 index = sc.nextInt() - 1; 
                sc.nextLine();
                if (index < 0 || index >= account.getRequestCount()) {
                    System.out.println("Invalid request index.");
                    continue; 
                }
                else
                {
                	break;
                }
                
        	}
        

        System.out.println("Do you want to accept the request? (Yes/No)");
        String a1;
        boolean accept = false;
        while (true) {
            a1 = sc.nextLine().trim();
            if (a1.equalsIgnoreCase("Yes")) {
                accept = true;
                break;
            } else if (a1.equalsIgnoreCase("No")) {
                accept = false;
                break;
            } else {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
            }
        }

        account.respondToTransferRequest(index, accept);
        }
    } else {
        System.out.println("Account not found.");
    }
}

public void applyInterestToAllAccounts(Scanner sc) {
	System.out.println("Amount to apply interest");
	sc.nextLine();
	double inte=getDInput(sc);
    for (BankAccount1 account : m.values()) {
        account.applyInterest(inte);
    }
}


}

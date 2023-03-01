package com.myproject.codingexcercise;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.myproject.codingexcercise.domain.TicketTypeRequest;
import com.myproject.codingexcercise.domain.TicketTypeRequest.Type;

public class TicketBookingMainService {

	private static Long accountId;

	// static Scanner scanner ;
	public static void main(String[] args) {

	//	Scanner scanner = new Scanner(System.in);
		int totalTicketsCount=0;
		 int noOfAdulttickets =0;
		 int noOfChildtickets =0;
		 int noOfInfantstickets =0;
		System.out.print("Please enter your account ID:");

		//	accountId = scanner.nextLong();
			String ticketType=null;
			Type type = null;
			//for (int i = 1; i <= Type.values().length; i++) {
			

	
		    Scanner input = new Scanner(System.in);
		    
		    try {
		    	
		    	accountId=input.nextLong();
		    }catch(InputMismatchException e) {
				
				System.out.println("Invalid number format,try again");
			}
		  
		   
		   if (accountId > 0) {
			   
		   
			System.out.println(" HELLO "+accountId+" ,WELCOME TO TICKET BOOKING SYSTEM!!!");
			System.out.println("Ticket prices :: Adult=£20 , Child=£10, infant=£0 " );
			System.out.println("Please note that maximum of 20 tickets only can be purchased at a time" );
			System.out.println("Please note that seats will not be allocated for infants" );
			input.nextLine();
		    while (true) {
			
		    	System.out.println( "Please enter a ticket Type (ADULT, CHILD, or INFANT)or type 'proceed' to book tickets or 'exit' to quit the application");
						ticketType = input.nextLine();
				    if ("proceed".equalsIgnoreCase(ticketType) || "exit".equalsIgnoreCase(ticketType)) {
			            break;
		        }
			
					try {
						type = Type.valueOf(ticketType.toUpperCase());
						System.out.println("You chose " + type);
						
						System.out.print("Enter number of tickets ");
						 
						 
						try {
						 if("ADULT".equalsIgnoreCase(ticketType)) {
							 noOfAdulttickets =noOfAdulttickets+ input.nextInt();
						 }
						 if("CHILD".equalsIgnoreCase(ticketType)) {
							 noOfChildtickets =noOfChildtickets+ input.nextInt();
						 }
						 if("INFANT".equalsIgnoreCase(ticketType)) {
							 noOfInfantstickets =noOfInfantstickets+ input.nextInt();
						 }
						}catch(InputMismatchException e) {
							
							System.out.println("Invalid number format,try again");
						}
						 input.nextLine();
				    	
					} catch (IllegalArgumentException e) {
						System.out.println("Invalid type ,try again");
						
					}
		    }
   
		    if(noOfAdulttickets >0 ||noOfChildtickets >0 ||noOfInfantstickets >0) {
			
			TicketTypeRequest adultTicketTypeObject = new TicketTypeRequest(Type.ADULT, noOfAdulttickets);
			TicketTypeRequest childTicketTypeObject = new TicketTypeRequest(Type.CHILD, noOfChildtickets);
			TicketTypeRequest infantTicketTypeObject = new TicketTypeRequest(Type.INFANT, noOfInfantstickets);
			
			TicketServiceImpl ticketService = new TicketServiceImpl();
			ticketService.purchaseTickets(accountId,adultTicketTypeObject,childTicketTypeObject,infantTicketTypeObject);
			}else {
				
				System.out.println("User exited");
			}

		   }else {
			   System.out.println("Please enter valid account number ,try again");
		   }
		    
		 
		  input.close();
	}
	
	


}

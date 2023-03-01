package com.myproject.codingexcercise;
import com.myproject.codingexcercise.domain.TicketTypeRequest;
import com.myproject.codingexcercise.domain.TicketTypeRequest.Type;
import com.myproject.codingexcercise.exception.InvalidPurchaseException;
import com.myproject.codingexcercise.thirdparty.paymentgateway.TicketPaymentService;
import com.myproject.codingexcercise.thirdparty.paymentgateway.TicketPaymentServiceImpl;
import com.myproject.codingexcercise.thirdparty.seatbooking.SeatReservationService;
import com.myproject.codingexcercise.thirdparty.seatbooking.SeatReservationServiceImpl;

/**
 * @author viji
 *
 */
public   class TicketServiceImpl implements TicketService {
	/**
	 * Should only have private methods other than the one below.
	 */
	private final int ADULTTICKETPRICE = 20, CHILDTICKETPRICE = 10,INFANTTICKETPRICE = 0;
	private static int sumOfticketAmount = 0,sumOfTicketCount=0;

	
	public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
		
		 int childTotalTicketCount = 0,childTotalTicketAmount = 0,adultTotalTicketCount = 0,adultTotalTicketAmount = 0,infantTotalTicketCount = 0,infantTotalTicketAmount=0;

		for (TicketTypeRequest o : ticketTypeRequests) {
            if (o instanceof TicketTypeRequest) {
            	TicketTypeRequest p = (TicketTypeRequest) o;
                int noofTickets  = p.getNoOfTickets();
                Type type  = p.getTicketType();
                
                if(type==Type.ADULT) {
                  adultTotalTicketCount=noofTickets;
                  adultTotalTicketAmount=adultTotalTicketCount*ADULTTICKETPRICE;
                 System.out.println("For ticket type  "+type + " You have booked  " + adultTotalTicketCount + " Tickets");
                }else if(type==Type.CHILD) {
                	  childTotalTicketCount=noofTickets;
                	  childTotalTicketAmount=childTotalTicketCount*CHILDTICKETPRICE;
                	  System.out.println("For ticket type  "+type + " You have booked  " + childTotalTicketCount + " Tickets");
                }else if(type==Type.INFANT) {
                	 infantTotalTicketCount=noofTickets;
                	 infantTotalTicketAmount=infantTotalTicketCount*INFANTTICKETPRICE;
                	  System.out.println("For ticket type  "+type + " You have booked  " + infantTotalTicketCount + " Tickets");
                }
               
            }
            
           
		}
		
		sumOfTicketCount=adultTotalTicketCount+childTotalTicketCount+infantTotalTicketCount;
		sumOfticketAmount=adultTotalTicketAmount+childTotalTicketAmount+infantTotalTicketAmount;
        
         
         if(adultTotalTicketCount > 0 ) {
	
        	 	if(sumOfTicketCount <=20) {
        	 		
        	 		makePayment(accountId,sumOfticketAmount);
        	 		reserveSeats(accountId,(sumOfTicketCount-infantTotalTicketCount));
        	 		
        	 		
   
        	 	}else {
        	 
        	 		throw new  InvalidPurchaseException ("Sorry you can't book  more than 20 tickets at a time your count is:: " + sumOfTicketCount);
        	 		}
		
         	}else {
         		
         		throw new  InvalidPurchaseException ("Sorry you can't book  tickets without adding atleast one adult ticket");
         	}
		
	}



	/**
	 * @param accountId
	 * @param sumOfTicketCount
	 */
	private void reserveSeats(Long accountId, int sumOfTicketCount) {
		
		SeatReservationService seatReservationService =new SeatReservationServiceImpl();
		
		seatReservationService.reserveSeat(accountId, sumOfTicketCount);
		System.out.println(sumOfTicketCount+" Seats  are reserved successfully for the account "+accountId);
		
	}



	/**
	 * @param accountId
	 * @param sumOfticketAmount
	 */
	private void makePayment(Long accountId, int sumOfticketAmount) {
		TicketPaymentService ticketPaymentService =new TicketPaymentServiceImpl();
		ticketPaymentService.makePayment(accountId, sumOfticketAmount);
		System.out.println("Payment made successfully for the account "+accountId+" for the amount "+sumOfticketAmount);
		
	}


	






}

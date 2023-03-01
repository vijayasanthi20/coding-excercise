package com.myproject.codingexcercise;

import com.myproject.codingexcercise.domain.TicketTypeRequest;
import com.myproject.codingexcercise.exception.InvalidPurchaseException;

public interface TicketService {
	
	

    void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException;
   
   
}

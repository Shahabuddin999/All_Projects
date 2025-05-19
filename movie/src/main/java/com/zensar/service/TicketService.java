package com.zensar.service;

import java.util.List;

import com.zensar.dto.Ticket;

public interface TicketService {
	public Ticket bookTicket(Ticket dto);
	public Ticket getTicket(int id);
	public List<Ticket> getTicketsByMovieId(int id);
}

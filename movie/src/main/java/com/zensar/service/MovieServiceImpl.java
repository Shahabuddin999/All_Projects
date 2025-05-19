package com.zensar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.dto.Movie;
import com.zensar.dto.Ticket;
import com.zensar.entity.MovieEntity;
import com.zensar.entity.TicketEntity;
import com.zensar.exception.InvalidMovieIdException;
import com.zensar.exception.InvalidTicketIdException;
import com.zensar.repository.MovieRepo;
import com.zensar.repository.TicketRepo;

@Service
public class MovieServiceImpl implements MovieService,TicketService {
	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private TicketRepo ticketRepo;

	@Override
	public Movie createMovie(Movie dto) {
		if (dto.getTitle() == null || dto.getDirector() == null || dto.getYear() <= 0)
			throw new IllegalArgumentException("Missing movie data");

		MovieEntity movie = new MovieEntity();
		BeanUtils.copyProperties(dto, movie);
		Optional<MovieEntity> check = movieRepo.findByTitle(dto.getTitle());
		if(check.isPresent()) {
			throw new IllegalArgumentException(dto.getTitle()+ ", movie is already present...");
		}
		MovieEntity savedMovie = movieRepo.save(movie);
		Movie movieDTO = new Movie();
		BeanUtils.copyProperties(savedMovie, movieDTO);
		return movieDTO;
	}

	@Override
	public Movie getMovieById(int id) {
		MovieEntity entity = movieRepo.findById(id)
				.orElseThrow(() -> new InvalidMovieIdException("Invalid movie ID"));
		Movie movieDTO = new Movie();
		BeanUtils.copyProperties(entity, movieDTO);
		return movieDTO;
	}

	@Override
	public Ticket bookTicket(Ticket dto) {
		if (dto.getTitle() == null || dto.getType() == null)
			throw new IllegalArgumentException("Missing ticket data");

		MovieEntity movie = movieRepo.findByTitle(dto.getTitle())
				.orElseThrow(() -> new InvalidMovieIdException("Movie not found"));

		TicketEntity ticket = new TicketEntity();
		ticket.setType(dto.getType());
		ticket.setUnitPrice(dto.getUnitPrice());
		ticket.setNoOfTickets(dto.getNoOfTickets());
		ticket.setPrice(dto.getUnitPrice() * dto.getNoOfTickets());
		ticket.setMovie(movie);
		TicketEntity savedTicket = ticketRepo.save(ticket);
		Ticket ticketDto = new Ticket();
		ticketDto.setTitle(dto.getTitle());
		BeanUtils.copyProperties(savedTicket, ticketDto);
		return ticketDto;
	}
	@Override
	public Ticket getTicket(int id) {
		TicketEntity ticketEntity = ticketRepo.findById(id)
				.orElseThrow(() -> new InvalidTicketIdException("Invalid ticket ID :" + id));
		Ticket ticketDto = new Ticket();
		BeanUtils.copyProperties(ticketEntity, ticketDto);
		ticketDto.setTitle(ticketEntity.getMovie().getTitle());
		return ticketDto;
	}

	@Override
	public List<Ticket> getTicketsByMovieId(int id) {
		MovieEntity entity = movieRepo.findById(id)
				.orElseThrow(() -> new InvalidMovieIdException("Invalid movie ID : " + id));
		List<TicketEntity> list = entity.getTickets();
		List<Ticket> tickets = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Ticket ticket = new Ticket();
			BeanUtils.copyProperties(list.get(i), ticket);
			ticket.setTitle(list.get(i).getMovie().getTitle());
			tickets.add(ticket);
		}
		return tickets;
	}
}

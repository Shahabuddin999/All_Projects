package com.zensar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Movie;
import com.zensar.dto.Ticket;
import com.zensar.entity.MovieEntity;
import com.zensar.entity.TicketEntity;
import com.zensar.service.MovieService;
import com.zensar.service.MovieServiceImpl;
import com.zensar.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/theater/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private TicketService ticketService;

	@PostMapping
	public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie dto) {
		Movie movie = movieService.createMovie(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(movie);
	}

	@GetMapping("/{movieId}")
	public ResponseEntity<Movie> getMovieById(@PathVariable int movieId) {
		return ResponseEntity.ok(movieService.getMovieById(movieId));
	}

	@PostMapping("/ticket/booking")
	public ResponseEntity<Ticket> bookTicket(@Valid @RequestBody Ticket dto) {
		Ticket ticket = ticketService.bookTicket(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
	}

	@GetMapping("/ticket/booking/{ticketId}")
	public ResponseEntity<Ticket> getTicket(@PathVariable int ticketId) {
		return ResponseEntity.ok(ticketService.getTicket(ticketId));
	}

	@GetMapping("/{movieId}/ticket")
	public ResponseEntity<List<Ticket>> getTicketsByMovieId(@PathVariable int movieId) {
		return ResponseEntity.ok(ticketService.getTicketsByMovieId(movieId));
	}
}

package com.zensar.service;

import com.zensar.dto.Movie;

public interface MovieService {

	public Movie createMovie(Movie movie);
	public Movie getMovieById(int movieId);
}
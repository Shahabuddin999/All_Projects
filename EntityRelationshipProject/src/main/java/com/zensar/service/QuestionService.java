package com.zensar.service;

import java.util.List;

import com.zensar.json.Answer;
import com.zensar.json.Question;

public interface QuestionService {
	public Question createQuestion(Question question);
	public List<Question> getAllQuestions();
}

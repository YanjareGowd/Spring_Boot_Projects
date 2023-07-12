package com.yg.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.quizapp.entity.Question;
import com.yg.quizapp.repository.QuestionDao;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}

	public List<Question> getQuestionsByCategory(String category) {
		return questionDao.findByCategory(category);
	}

	public String addQuestion(Question question) {
		questionDao.save(question);
		return "sucess";
		
	}

	public String deleteQuestionByid(int id) {
		
		Optional<Question> existingQuestion = questionDao.findById(id);
		if(existingQuestion.isPresent())
		{
		questionDao.deleteById(id);
		return "sucessfully deleted question";
		}
		else 
		{
			return "question not found";
		}
		
	}

	public String updateQuestionById(int id, Question question) {
		//check if the question is present or not in database
		 Optional<Question> optionalQuestion = questionDao.findById(id);
		if(optionalQuestion.isPresent())
		{
			Question existingQuestion = optionalQuestion.get();
			existingQuestion.setQuestion(question.getQuestion());
			existingQuestion.setDifficultylevel(question.getDifficultylevel());
			existingQuestion.setOption1(question.getOption1());
			existingQuestion.setOption2(question.getOption2());
			existingQuestion.setOption3(question.getOption3());
			existingQuestion.setOption4(question.getOption4());
			existingQuestion.setCategory(question.getCategory());
			existingQuestion.setAnswer(question.getAnswer());
			
			questionDao.save(existingQuestion);
			
			return "question updated";
			
		}
		else {
			return "question not found";
		}
	}

	
}
 
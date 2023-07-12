package com.yg.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yg.quizapp.entity.Question;
import com.yg.quizapp.entity.QuestionWrapper;
import com.yg.quizapp.entity.Quiz;
import com.yg.quizapp.entity.Response;
import com.yg.quizapp.repository.QuestionDao;
import com.yg.quizapp.repository.QuizDao;

@Service
public class QuizService {
	 
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionByCategory(category,numQ);
        
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        
        return new ResponseEntity<>("Sucess",HttpStatus.CREATED);
	}

//	public ResponseEntity<List<Question>> getQuizQuestions(int id) {
//		Optional<Quiz> quiz=quizDao.findById(id);
//		List<Question> quetionsFromDBList = quiz.get().getQuestions();
//		List<QuestionWrapper> questionForUser = new ArrayList<>();
//		
//		
//		return new ResponseEntity<>(questionForUser,HttpStatus.CREATED);
//	}
	 public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) 
	 {
	        Optional<Quiz> quiz = quizDao.findById(id);
	        List<Question> questionsFromDB = quiz.get().getQuestions();
	        List<QuestionWrapper> questionsForUser = new ArrayList<>();
	        for(Question q : questionsFromDB){
	            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(),q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
	            questionsForUser.add(qw);
	        }

	        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

	    }

	
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		
		int correct=0;
		int i=0;
		for(Response response : responses)
		{
			if(response.getResponse().equals(questions.get(i).getAnswer()))
		//  if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				correct++;
		
			
			i++;
		}
		 return new ResponseEntity<>(correct,HttpStatus.OK);
	}

	

}

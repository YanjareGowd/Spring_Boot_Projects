package com.yg.quizapp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yg.quizapp.entity.Question;
import com.yg.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	//REST API for to get all question from database
	@GetMapping("/allquestions")
	public List<Question> getAllQuestion()
	{
		return questionService.getAllQuestions();
	}
	//REST API to get a question by category
	@GetMapping("category/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category)
	{
		return questionService.getQuestionsByCategory(category);
	}
	
	//REST API to add an question to Database
	@PostMapping("add")
	public String addQuestion(@RequestBody Question question)
	{
		return questionService.addQuestion(question);
	}
	
	//REST API to delete question by id
	@DeleteMapping("delete/{id}")
	public String deleteQuestionId(@PathVariable int id)
	{
		return questionService.deleteQuestionByid(id);
	}
	
	//REST API to update question by id
	@PutMapping("update/{id}")
	public String updateQuestionById(@PathVariable int id,@RequestBody Question question)
	{
		return questionService.updateQuestionById(id , question);
	}

}

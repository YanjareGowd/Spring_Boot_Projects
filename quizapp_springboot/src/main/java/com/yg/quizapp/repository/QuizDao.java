package com.yg.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yg.quizapp.entity.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}

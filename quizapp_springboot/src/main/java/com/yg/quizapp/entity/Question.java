package com.yg.quizapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="quizsetquestion")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	private String question;
	private String difficultylevel;
	private String category;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;

}

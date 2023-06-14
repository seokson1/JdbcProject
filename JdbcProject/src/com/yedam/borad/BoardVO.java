package com.yedam.borad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	
	private int number;
	private String title;
	private String writer;
	private String content;
	private String date;
	private int cnt;
	
}

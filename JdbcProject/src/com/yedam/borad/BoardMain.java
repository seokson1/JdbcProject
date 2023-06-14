package com.yedam.borad;

import java.util.List;
import java.util.Scanner;

public class BoardMain {
	public static void main(String[] args) {
		int cnt = 0;
		BoardDao dao = new BoardDao();

		Scanner scn = new Scanner(System.in);
		int menu = 0;

		while (true) {
			System.out.println("1.글등록 | 2.삭제 | 3.글내용수정 | 4.글목록보기 | 5. 상세보기 | 6. 종료");
			System.out.print("선택> ");

			menu = scn.nextInt();
			scn.nextLine();

			if (menu == 1) {

				System.out.print("제목> ");
				String title = scn.nextLine();
				System.out.print("작성자> ");
				String writer = scn.nextLine();
				System.out.print("내용> ");
				String content = scn.nextLine();

				BoardVO board = new BoardVO();
				board.setTitle(title);
				board.setWriter(writer);
				board.setContent(content);

				if (dao.registration(board)) {
					System.out.println("등록 성공");
				} else {
					System.out.println("등록 실패");
				}

			} else if (menu == 2) {
				System.out.print("번호> ");
				int no = scn.nextInt();

				if (dao.remove(no)) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
			} else if (menu == 3) {
				System.out.print("번호> ");
				int no = scn.nextInt();
				scn.nextLine();
				System.out.print("제목> ");
				String title = scn.nextLine();
				System.out.print("작성자> ");
				String writer = scn.nextLine();
				System.out.print("내용> ");
				String content = scn.nextLine();

				BoardVO board = new BoardVO();
				board.setNumber(no);
				board.setTitle(title);
				board.setWriter(writer);
				board.setContent(content);

				if (dao.modify(board)) {
					System.out.println("수정 완료");
				} else {
					System.out.println("수정 실패");
				}
			} else if (menu == 4) {
				List<BoardVO> list = dao.list();
				if (list.size() == 0) {
					System.out.println("조회 결과 없음");
				} else {
					for (BoardVO board : list) {
						System.out.println(board.toString());
					}

				}
			} else if (menu == 5) {
				
				System.out.print("번호> ");
				int no = scn.nextInt();
				scn.nextLine();		
				dao.number(no);
		
				
				BoardVO search = dao.search(no);
				if (search == null) {
					System.out.println("글 번호 없음.");
				} else {
					System.out.println(search.toString());
					
					
				}
			} else if (menu == 6) {
				break;
			}

		}
		System.out.println("end of prog.");
	}
}
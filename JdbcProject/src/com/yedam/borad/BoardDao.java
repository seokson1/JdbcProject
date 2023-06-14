package com.yedam.borad;
//1. 글등록 2. 삭제 3 글내용수정 4 글목록 보기 5 상세보기

// 항목: 글번호, 제목, 작성자, 내용, 작성이시, 조회 tbl_board> brd_no, brd_title brd_writer, create_date click_cnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;

public class BoardDao {

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 글 등록
	public boolean registration(BoardVO board) {
		sql = "insert into tbl_board (brd_no ,brd_title, brd_writer,brd_content)" + " values(board_seq.nextval ,?,?,?)";
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getWriter());
			psmt.setString(3, board.getContent());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return false;
	}

	// 삭제
	public boolean remove(int no) {
		sql = "delete from tbl_board where brd_no= ? ";
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// 글내용 수정
	public boolean modify(BoardVO board) {
		sql = "update tbl_board " + " set brd_title = nvl(?, brd_title), brd_writer=nvl(?,brd_writer),"
				+ " brd_content = nvl(?,brd_content)" + " where brd_no = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getWriter());
			psmt.setString(3, board.getContent());
			psmt.setInt(4, board.getNumber());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// 글 목록 보기
	public List<BoardVO> list() {
		List<BoardVO> list = new ArrayList<>();

		sql = "select * from tbl_board";
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				BoardVO board = new BoardVO();
				board.setNumber(rs.getInt("brd_no"));
				board.setTitle(rs.getString("brd_title"));
				board.setWriter(rs.getString("brd_writer"));
				board.setContent(rs.getString("brd_content"));
				board.setDate(rs.getString("cretae_date"));
				board.setCnt(rs.getInt("click_cnt"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public BoardVO search(int no) {
		sql = "select * from tbl_board where brd_no = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();

			if (rs.next()) {
				BoardVO board = new BoardVO();
				board.setNumber(rs.getInt("brd_no"));
				board.setTitle(rs.getString("brd_title"));
				board.setWriter(rs.getString("brd_writer"));
				board.setContent(rs.getString("brd_content"));
				board.setDate(rs.getString("cretae_date"));
				board.setCnt(rs.getInt("click_cnt"));
				return board;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return null;
	}

	public boolean number(int no) {

		sql = " update tbl_board " + " set click_cnt = click_cnt + 1 " + " where brd_no = ?";
		conn = Dao.getConnect();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			int r = psmt.executeUpdate();

			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			
		} finally {
			close();
		}

		return false;
	}
}

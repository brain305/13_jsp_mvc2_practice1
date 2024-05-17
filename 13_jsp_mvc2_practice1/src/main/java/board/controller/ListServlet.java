package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.DAO.BoardDAO;

@WebServlet("/bList")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 게시글 목록을 보여주는 로직
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// db에서 갖고 오기
		request.setAttribute("boardList", BoardDAO.getInstance().getBoardList());
		
		RequestDispatcher dis = request.getRequestDispatcher("board/bList.jsp");
		dis.forward(request, response);
		
	}


}

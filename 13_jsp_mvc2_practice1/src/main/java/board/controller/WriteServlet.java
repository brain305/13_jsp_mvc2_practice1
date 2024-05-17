package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.DAO.BoardDAO;
import board.DTO.BoardDTO;

@WebServlet("/bWrite")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	// 신호를 받으면 게시글 작성 화면 보여주는 로직(완)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("board/bWrite.jsp");
		dis.forward(request, response);
	}

	// 게시글 작성 데이터를 보내는 로직
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setWriter(request.getParameter("writer"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setEmail(request.getParameter("email"));
		boardDTO.setPassword(request.getParameter("password"));
		boardDTO.setContent(request.getParameter("content"));
		
		BoardDAO.getInstance().insertBoard(boardDTO);
		
		// 다음 페이지로 넘어가는 부분 만들기
		
		// alert 기능이 없어도 되면 단순이동은 sendRedirect("bList");로도 가능
		// spring 에서는 redirect:/bList
		response.sendRedirect("bList");
		
		// 원래 버전
	/*
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		String jsScript = """
					<script>
						alert("등록되었습니다.");
						location.href='bList';
					</script>
				""";
		
		pw.print(jsScript);
	*/
		
		
	}

}

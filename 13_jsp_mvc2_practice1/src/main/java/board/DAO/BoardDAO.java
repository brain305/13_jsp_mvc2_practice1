package board.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.DTO.BoardDTO;

public class BoardDAO {
	
	// 싱글톤 패턴 사용
	private BoardDAO(){}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 데이터베이스 연동 객체 생성
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private void getConnection() {		// private을 쓰는 이유 : 보안에 더 유용함
		
		try {
			Context initctx = new InitialContext();						  // 데이터베이스와 연결하기 위한 init객체 생성
			Context envctx = (Context) initctx.lookup("java:comp/env");  // lookup 메서드를 통해 context.xml 파일에 접근하여 자바환경 코드를 검색     
			DataSource ds = (DataSource) envctx.lookup("jdbc/db"); 	 // <Context>태그안의 <Resource> 환경설정의 name이 jdbc/db인 것을 검색	  
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	private void getClose() {		
		
		if(rs != null)	  try {rs.close();}		catch (SQLException e) {e.printStackTrace();}
		
		if(pstmt != null) try {pstmt.close();} 	catch (SQLException e) {e.printStackTrace();}
		
		if(conn != null)  try {conn.close();}	catch (SQLException e) {e.printStackTrace();}
		
	}
	
	// 게시판에서 갖고온 데이터를 db에 넣기
	public void insertBoard(BoardDTO boardDTO){
		
		try {
			
			getConnection();		// DB에 연결
			
			String sql = """
						INSERT INTO BOARD	(WRITER,EMAIL,SUBJECT,PASSWORD,CONTENT,READ_CNT,ENROLL_DT)
						VALUES				(?,?,?,?,?,0,NOW())
					""";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getWriter());
			pstmt.setString(2, boardDTO.getEmail());
			pstmt.setString(3, boardDTO.getSubject());
			pstmt.setString(4, boardDTO.getPassword());
			pstmt.setString(5, boardDTO.getContent());
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();				// DB 연결 해제
		}
		
		
		
	}
	
	
	
	
}

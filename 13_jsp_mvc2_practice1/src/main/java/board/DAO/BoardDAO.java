package board.DAO;

public class BoardDAO {
	
	// 싱글톤 패턴 사용
	private BoardDAO(){}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	
	
}

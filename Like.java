package exam.day3.board;

public class Like {
	
	int id; // 좋아요 식별 데이터
	int articleId; // 좋아요가 체크된 게시물 번호
	int memberId; // 좋아요를 체크한 회원 번호
	String regDate; // 등록 날짜
	
	public Like(int id, int articleId, int memberId, String regDate) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.memberId = memberId;
		this.regDate = regDate;
	}
	
}

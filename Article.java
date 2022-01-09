package exam.day3.board;

// 유사한 데이터를 모아두는 것
public class Article {
	public int id; // 번호
	public String title; // 제목
	public String body; // 내용
	public int memberId; // 작성자
	public String regDate; // 등록날짜
	public int hit; // 조회수
	
	public Article(int id, String title, String body, int memberId, String regDate, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.regDate = regDate;
		this.hit = hit;
	}
}

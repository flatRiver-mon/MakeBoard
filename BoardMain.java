package exam.day3.board;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardMain {

	static Scanner scan = new Scanner(System.in);

//	static ArrayList<Integer> ids = new ArrayList<Integer>(); // 번호 저장소
//	static ArrayList<String> titles = new ArrayList<String>(); // 제목 저장소
//	static ArrayList<String> bodies = new ArrayList<String>(); // 내용 저장소

	static ArrayList<Article> articles = new ArrayList<>();
	static ArrayList<Member> members = new ArrayList<>();
	static int lastArticleId = 4; // 가장 마지막에 만들어진 게시물 번호
	static int lastMemberId = 4; // 가장 마지막에 만들어진 회원 번호
	
	static Member loginedMember = null;
	
	public static void main(String[] args) {
		
		makeTestData();
		
		while (true) {
			if(loginedMember == null) {
				System.out.print("명령어를 입력해주세요 : ");				
			} else {
				System.out.print("명령어를 입력해주세요 [" + loginedMember.loginId + "(" + loginedMember.nickname + ")]: ");
			}
			
			String cmd = scan.nextLine();

			if (cmd.equals("list")) {

				list();

			} else if (cmd.equals("delete")) {
				
				delete();

			} else if (cmd.equals("update")) {

				update();

			} else if (cmd.equals("add")) {

				add();
				
			} else if (cmd.equals("help")) {

				printHelp();

			} else if (cmd.equals("search")) {

				search();

			} else if (cmd.equals("read")) {
				
				read();
				
			} else if (cmd.equals("signup")) {
				
				signup();
				
			} else if (cmd.equals("signin")) {
				
				signin();
				
			} else if (cmd.equals("logout")) {
				
				logout();
				
			}

			else {
				System.out.println("알 수 없는 명령어입니다.");
			}
		}

	}
	
	private static void logout() {
		loginedMember = null;
		System.out.println("로그아웃 되셨습니다.");
		
	}

	private static void makeTestData() {
		Article a1 = new Article(1, "안녕하세요", "내용1", "익명", "2022.01.02", 0);
		Article a2 = new Article(2, "반갑습니다", "내용2", "익명", "2022.01.02", 0);
		Article a3 = new Article(3, "안녕2", "내용3", "익명", "2022.01.02", 0);

		Member m1 = new Member(1, "hong123", "h1234", "홍길동");
		Member m2 = new Member(1, "lee123", "1234", "이순신");
		
		
		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
		
		members.add(m1);
		members.add(m2);		
	}

	private static void signin() {
		System.out.print("아이디 : ");
		String loginId = scan.nextLine();
		System.out.print("비밀번호 : ");
		String loginPw = scan.nextLine();
		
		for(int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			if(member.loginId.equals(loginId)) {
				if(member.loginPw.equals(loginPw)) {
					System.out.println(member.nickname + "님 반갑습니다!");
					loginedMember = member;
				}
			}
		}
		
		if(loginedMember == null) {
			System.out.println("잘못된 회원정보입니다.");			
		}
	}

	private static void signup() {
		// 회원가입 메서드
		System.out.println("==== 회원 가입을 진행합니다 ====");
		System.out.print("아이디를 입력해주세요.");
		String loginId = scan.nextLine();
		System.out.print("비밀번호를 입력해주세요.");
		String loginPw = scan.nextLine();
		System.out.print("닉네임을 입력해주세요.");
		String nickname = scan.nextLine();
		System.out.println("==== 회원가입이 완료되었습니다. ====");

		Member member = new Member(lastMemberId,loginId, loginPw, nickname);
		members.add(member);
		lastMemberId++;
		
		printMembers(members);
		
	}
	
	private static void printMembers(ArrayList<Member> members) {
		// 가입된 회원 목록 조회 메서드
		System.out.println("==== 현재 가입된 회원 목록 ====");
		for(int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			
			System.out.println("아이디 : " + member.loginId);
			System.out.println("비밀번호 : " + member.loginPw);
			System.out.println("이름 : " + member.nickname);
		}
	}

	private static void read() {
		// 게시물 상세 보기 메서드
		System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
		int id = Integer.parseInt(scan.nextLine());
		int targetIndex = findIndexByArticleId(id);

		if (targetIndex == -1) {
			System.out.println("없는 게시물입니다.");
		} else {
			Article article = articles.get(targetIndex);
			article.hit++;
			printArticle(article);
		}
	}

	private static void printArticle(Article article) {
		// 게시물 내용 조회
		System.out.println("==== " + article.id + "번 게시물====");
		System.out.println("번호 : " + article.id);
		System.out.println("제목 : " + article.title);
		System.out.println("-------------------");
		System.out.println("내용 : " + article.body);
		System.out.println("-------------------");
		System.out.println("작성자 : " + "익명");
		System.out.println("등록날짜 : " + article.regDate);
		System.out.println("조회수 : " + article.hit);
		System.out.println("===================");
	}

	private static void printArticles(ArrayList<Article> targetList) {
		// targetList로 전체 게시물 목록 조회
		for (int i = 0; i < targetList.size(); i++) {
			Article article = targetList.get(i);
			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("작성자 : " + "익명");
			System.out.println("등록날짜 : " + article.regDate);
			System.out.println("조회수 : " + article.hit);
			System.out.println("=====================");
		}
	}

	private static void search() {
		// 제목 키워드로 String.contains를 이용한 검색 메서드
		System.out.print("검색 키워드를 입력해주세요 :");
		String keyword = scan.nextLine();

		ArrayList<Article> searchedList = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);

			if (article.title.contains(keyword)) {
				searchedList.add(article);
			}
		}

		printArticles(searchedList);

	}

	private static void delete() {
		// ArrayList의 remove기능을 이용한 게시물 삭제 메서드
		System.out.print("삭제할 게시물 번호 : ");
		int targetId = Integer.parseInt(scan.nextLine());

		int index = findIndexByArticleId(targetId);

		if (index == -1) {
			System.out.println("없는 게시물 번호입니다.");
		} else {

			articles.remove(index);
			System.out.println("삭제가 완료되었습니다.");
			list();
		}

	}

	private static void printHelp() {
		// 기능 도움말 메서드
		System.out.println("add  : 게시물 등록");
		System.out.println("list : 게시물 목록 조회");
		System.out.println("update : 게시물 수정");
		System.out.println("delete : 게시물 삭제");
		System.out.println("search : 게시물 검색");
		System.out.println("read : 게시물 내용 조회");
		System.out.println("signup : 회원가입");
		System.out.println("signin : 로그인");
		System.out.println("logout : 로그아웃");
	}

	private static void add() {

		// 게시물 추가 메서드
		System.out.print("제목을 입력해주세요 : ");
		String title = scan.nextLine();
		System.out.print("내용을 입력해주세요 : ");
		String body = scan.nextLine();

		// 오늘 날짜 구하기
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String formatedNow = now.format(formatter);

		Article a1 = new Article(lastArticleId, title, body, "익명", formatedNow, 0);
		articles.add(a1);

		System.out.println("게시물이 저장되었습니다.");
		lastArticleId++;

	}

	private static void update() {

		// ArrayList의 set 메서드를 이용한 게시물 수정 메서드
		System.out.print("수정할 게시물 번호 : ");
		int targetId = Integer.parseInt(scan.nextLine());

		int index = findIndexByArticleId(targetId);

		if (index == -1) {
			System.out.println("없는 게시물 번호입니다.");
		} else {
			System.out.print("제목 : ");
			String title = scan.nextLine();
			System.out.print("내용 : ");
			String body = scan.nextLine();
			Article article = articles.get(index);

			articles.set(index, new Article(targetId, title, body, "익명", article.regDate, article.hit));

			System.out.println("게시물이 수정이 완료되었습니다.");

			list();
		}

	}

	private static void list() {
		// 게시물 목록 조회 메서드
		printArticles(articles);
	}

	public static int findIndexByArticleId(int targetId) {
		// 인덱스 값이 -1이면 없는 게시물로 반환, 있다면 올바른 게시물 번호로 반환하는 메서드
		int index = -1;

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (targetId == article.id) {
				index = i;
				break;
			}
		}

		return index;
	}

}

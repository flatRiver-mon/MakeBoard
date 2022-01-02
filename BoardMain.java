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
	static int lastArticleId = 4; // 가장 마지막에 만들어진 게시물 번호
	
	public static void main(String[] args) {
		
		Article a1 = new Article(1, "안녕하세요", "내용1","익명", "2022.01.02", 0);
		Article a2 = new Article(2, "반갑습니다", "내용2","익명", "2022.01.02", 0);
		Article a3 = new Article(3, "안녕2", "내용3","익명", "2022.01.02", 0);
		
		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
		
		while(true) {
			System.out.print("명령어를 입력해주세요 : ");
			String cmd = scan.nextLine();
			
			if(cmd.equals("list")) {
				
				list();
				
			}
			else if(cmd.equals("delete")) {
				delete();
				
			}
			else if(cmd.equals("update")) {
				
				update();
				
			}
			else if(cmd.equals("add")) {
						
				add();
			}
			else if(cmd.equals("help")) {
				
				printHelp();
				
			}
			else if(cmd.equals("search")) {
				
				search();
				
			}
			else if(cmd.equals("read")) {
				read();
			}
			else {
				System.out.println("알 수 없는 명령어입니다.");
			}
		}
		
	}
	
	private static void read() {
		// 게시물 상세 보기 메서드
		System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
		int id = Integer.parseInt(scan.nextLine());
		int targetIndex = findIndexByArticleId(id);
		
		if(targetIndex == -1) {
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
		for(int i = 0; i < targetList.size(); i++) {
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
		
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			
			if(article.title.contains(keyword)) {
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
		
		if(index == -1) {
			System.out.println("없는 게시물 번호입니다.");
		}else {
	
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
		
		if(index == -1) {
			System.out.println("없는 게시물 번호입니다.");
		} else {
			System.out.print("제목 : ");
			String title = scan.nextLine();
			System.out.print("내용 : ");
			String body = scan.nextLine();
			Article article = articles.get(index);
			
			articles.set(index, new Article(targetId, title, body,"익명", article.regDate, article.hit));

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
		
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i); 
			if(targetId == article.id) {
				index = i;
				break;
			}
		}
		
		return index;
	} 

}

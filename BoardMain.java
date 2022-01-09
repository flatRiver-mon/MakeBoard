package exam.day3.board;

import java.util.Scanner;

public class BoardMain {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		ArticleController articleController = new ArticleController();
		MemberController memberController = new MemberController();
		
		while (true) {
			if(memberController.loginedMember == null) {
				System.out.print("명령어를 입력해주세요 : ");				
			} else {
				System.out.print("명령어를 입력해주세요 [" + memberController.loginedMember.loginId + "(" + memberController.loginedMember.nickname + ")]: ");
			}
			
			String cmd = scan.nextLine();
			String[] cmdBits = cmd.split(" ");
			
			if(cmdBits.length < 2) {
				System.out.println("잘못된 명령입니다.");
				continue;
			}
			
			String module = cmdBits[0];
			String func = cmdBits[1];
			
			if(module.equals("article")) {
				articleController.doCommand(func);
			} else if(module.equals("member")) {
				memberController.doCommand(func);
			} else {
				System.out.println("잘못된 명령입니다.");
			}
			
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
}

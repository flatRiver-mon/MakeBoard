package exam.day3.board;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberController {
	
	Scanner scan = new Scanner(System.in);
	ArrayList<Member> members = new ArrayList<>();
	int lastMemberId = 4; // 가장 마지막에 만들어진 회원 번호
	static Member loginedMember = null; // static 으로 들어있는 회원의 정보를 전체 공유 자원으로 만듦
	
	public MemberController() {
		
		makeTestData();
		loginedMember = members.get(0);
		
	}
	
	public void doCommand(String cmd) {
		if (cmd.equals("signup")) {
			
			signup();
			
		} else if (cmd.equals("signin")) {
			
			signin();
			
		} else if (cmd.equals("logout")) {
			
			logout();
			
		}
	}
	
	private void makeTestData() {

		Member m1 = new Member(1, "hong123", "h1234", "홍길동");
		Member m2 = new Member(1, "lee123", "1234", "이순신");
		
		members.add(m1);
		members.add(m2);		
	}
	
	private void signup() {
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
	
	private void signin() {
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
	
	private void logout() {
		loginedMember = null;
		System.out.println("로그아웃 되셨습니다.");
		
	}
	
	private void printMembers(ArrayList<Member> members) {
		// 가입된 회원 목록 조회 메서드
		System.out.println("==== 현재 가입된 회원 목록 ====");
		for(int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			
			System.out.println("아이디 : " + member.loginId);
			System.out.println("비밀번호 : " + member.loginPw);
			System.out.println("이름 : " + member.nickname);
		}
		
	}

	// 변수명 -> 명사
	// 함수명 -> 동사
	
	public String getNicknameByMemberId(int memberId) {
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).id == memberId) {
				return members.get(i).nickname;
			}			
		}
		return null;
	}
	 	
}

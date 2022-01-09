package exam.day3.board;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadController {
	
	Scanner scan = new Scanner(System.in);
	int lastReplyId = 1;
	ArrayList<Reply> replies = new ArrayList<>(); 
	
	public void doCommand(String cmd) {
		if(cmd.equals("reply")) {
			reply();
		} else if(cmd.equals("like")) {
			like();
		} else if(cmd.equals("update")) {
			update();
		} else if(cmd.equals("delete")) {
			delete();
		}
	}

	private void delete() {
		System.out.println("[삭제 기능]");		
	}

	private void update() {
		System.out.println("[수정 기능]");
	}

	private void like() {
		System.out.println("[좋아요 기능]");	
	}

	private void reply() {
		System.out.print("댓글 내용을 입력해주세요. : ");
		String body = scan.nextLine();
		int memberId = MemberController.loginedMember.id;
		String currentDate = MyUtil.getCurrentDate();
		
		Reply reply = new Reply(lastReplyId, body, memberId, currentDate);
		replies.add(reply);
		lastReplyId++;
		System.out.println("댓글이 등록되었습니다.");
		
	}
}

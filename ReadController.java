package exam.day3.board;

public class ReadController {
	
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
		System.out.println("[댓글 기능]");
	}
}

package exam.day3.board;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadController {
	
	Scanner scan = new Scanner(System.in);
	int lastReplyId = 1;
	int lastLikeId = 1;
	ArrayList<Reply> replies = new ArrayList<>();
	ArrayList<Like> likes = new ArrayList<>();
	
	public void doCommand(String cmd, Article article) {
		if(cmd.equals("reply")) {
			reply(article);
		} else if(cmd.equals("like")) {
			like(article);
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

	private void like(Article article) {
		
		int articleId = article.id; // 좋아요 체크할 게시물 번호
		int memberId = MemberController.loginedMember.id; // 좋아요 체크할(로그인한) 회원 번호
		String currentDate = MyUtil.getCurrentDate();
		
		// articleId, memberId로 좋아요 검색.
		Like searchedLike = getLikeByArticleIdAndMemberId(articleId, memberId);
		
		if(searchedLike == null) {
			// 저장을 하는 순간 ==> 좋아요 체크
			Like like = new Like(lastLikeId, articleId, memberId, currentDate);
			likes.add(like);
			System.out.println("해당 게시물을 좋아합니다.");
		} else {
			likes.remove(searchedLike);
			System.out.println("해당 게시물의 좋아요를 해제합니다.");			
		}
	}

	public Like getLikeByArticleIdAndMemberId(int articleId, int memberId) {
		
		for(int i = 0; i < likes.size(); i++) {
			if(likes.get(i).articleId == articleId && likes.get(i).memberId == memberId) {
				return likes.get(i);
			}
		}
		
		return null;
	}
	
	public int getLikeCountOfArticle(int articleId) {
		
		int count = 0;
		
		for(int i = 0; i < likes.size(); i++) {
			if(likes.get(i).articleId == articleId) {
				count++;
			}
		}
		
		return count;
	}

	private void reply(Article article) {
		System.out.print("댓글 내용을 입력해주세요. : ");
		String body = scan.nextLine();
		int memberId = MemberController.loginedMember.id;
		String currentDate = MyUtil.getCurrentDate();
		
		Reply reply = new Reply(lastReplyId, article.id, body, memberId, currentDate);
		replies.add(reply);
		lastReplyId++;
		System.out.println("댓글이 등록되었습니다.");
		
	}
	
}

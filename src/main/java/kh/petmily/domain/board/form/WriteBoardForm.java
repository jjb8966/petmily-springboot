package kh.petmily.domain.board.form;

import java.util.Map;

public class WriteBoardForm {
	
	private int mNumber;
	private String kindOfBoard;
	private String title;
	private String content;
	private String checkPublic;
	
	public WriteBoardForm(int mNumber, String kindOfBoard, String title, String content, String checkPublic) {
		this.mNumber = mNumber;
		this.kindOfBoard = kindOfBoard;
		this.title = title;
		this.content = content;
		this.checkPublic = checkPublic;
	}

	public int getmNumber() {
		return mNumber;
	}

	public String getKindOfBoard() {
		return kindOfBoard;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public String getCheckPublic() {
		return checkPublic;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
	
}

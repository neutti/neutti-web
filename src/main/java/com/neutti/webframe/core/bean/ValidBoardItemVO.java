package com.neutti.webframe.core.bean;




@SuppressWarnings("serial")
public class ValidBoardItemVO   {
	private String title;
	private String contents;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public String[] getAttachfile() {
		return attachfile;
	}
	public void setAttachfile(String[] attachfile) {
		this.attachfile = attachfile;
	}

	public String[] getAttachfileCaption() {
		return attachfileCaption;
	}
	public void setAttachfileCaption(String[] attachfileCaption) {
		this.attachfileCaption = attachfileCaption;
	}

	private String[] attachfile = {};
	private String[] attachfileCaption = {};

}

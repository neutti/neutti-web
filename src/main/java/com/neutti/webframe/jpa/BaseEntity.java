package com.neutti.webframe.jpa;



import com.neutti.webframe.core.bean.BoardVO;
import com.neutti.webframe.core.bean.PagingVO;
import com.neutti.webframe.core.storage.OperationType;

import java.io.Serializable;


public abstract class BaseEntity extends PagingVO implements Serializable {
	protected static final long serialVersionUID = 1L;
	private Long selectId;
	/**
	 * 입력(insert), 수정(update), 삭제(delete)
	 */
	private OperationType operation = OperationType.SELECT;
	/**
	 * 연산호출
	 */
	private OperationType call;
	private BoardVO board;
	/**
	 * 비밀번호확인
	 */
	private String confirmPassword;
	/**
	 * 메시지
	 */
	private String message;
	private String[] attachfile = {};
	private String[] attachfileCaption = {};
	private boolean random = false;


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
	public BoardVO getBoard() {
		return board;
	}
	public void setBoard(BoardVO board) {
		this.board = board;
	}
	public String getOperation() {
		if(operation == null){
			return OperationType.SELECT.name();
		}
		return operation.name();
	}
	public void setOperation(String operation) {
		OperationType val = OperationType.find(operation);
		this.operation = val;
	}
	public String getCall() {
		if(call == null){
			return null;
		}
		return call.name();
	}
	public void setCall(String call) {
		OperationType val = OperationType.find(call);
		this.call = val;
	}
	public Long getSelectId() {
		return selectId;
	}
	public void setSelectId(Long selectId) {
		this.selectId = selectId;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isRandom() {
		return random;
	}
	public void setRandom(boolean random) {
		this.random = random;
	}
}

package com.trisse.spacerouge.action;

public class ActionResult {

	public static final ActionResult SUCCESS = new ActionResult(true);
	public static final ActionResult FAILURE = new ActionResult(false);

	private Action alternative;
	
	private String message;

	private boolean success;

	public boolean success() {
		return success;
	}

	public Action alternative() {
		return alternative;
	}

	public ActionResult(Action alternative) {
		success = true;
		this.alternative = alternative;
	}
	
	private ActionResult(String message, boolean success) {
		this.message = message;
		this.success = success;
	}
	
	public static ActionResult failure(String message) {
		ActionResult result = new ActionResult(message, false);
		return result;
	}
	
	public static ActionResult success(String message) {
		ActionResult result = new ActionResult(message, true);
		return result;
	}

	public ActionResult(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}

}

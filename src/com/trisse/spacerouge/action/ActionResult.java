package com.trisse.spacerouge.action;

public class ActionResult {

	public static final ActionResult SUCCESS = new ActionResult(true);
	public static final ActionResult FAILURE = new ActionResult(false);

	private Action alternative;

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

	public ActionResult(boolean success) {
		this.success = success;
	}

}

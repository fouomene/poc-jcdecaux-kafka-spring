package xyz.afrinnov.jcdecaux.kafkac.websocket;

public class JcdecauxMessage {

	private String message;

	public JcdecauxMessage() {
	}

	public JcdecauxMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

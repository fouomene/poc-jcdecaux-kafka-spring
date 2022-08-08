package xyz.afrinnov.jcdecaux.kafka.websocket;

public class StationMessage {

	private String content;

	public StationMessage() {
	}

	public StationMessage(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}

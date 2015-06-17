package group.engine;

public class EventHandler {
	private static EventHandler eventHandler = null;
	public static EventHandler getObject() {
		if ( eventHandler == null ) {
			eventHandler = new EventHandler();
		}
		return eventHandler;
	}
	
	public EventHandler() {
		
	}
	
	public void sendEvent(Event e) {
		
	}
}

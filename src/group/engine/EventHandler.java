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
	
	public static void sendEvent(Event e) {
		switch ( e.getType() ) {
			case Event.BUILD_TOWER:
				Game.getObject().createTower(e.tower_type, e.tower_x, e.tower_y);
				break;
			case Event.ATTACK_EVENT:
				Game.getObject().demageEnemy(e.enemy_id, e.demage);
				break;
			case Event.ENEMY_CREAT:
				Game.getObject().createEnemy(e.enemy_id, e.location, e.enemy_type, e.enemyPath);
				break;
		}
	}
}

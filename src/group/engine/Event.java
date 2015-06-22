package group.engine;

import android.util.Log;

public class Event {
	public static final int BUILD_TOWER = 0;
	public static final int ATTACK_EVENT = 1;
	public static final int ENEMY_CREAT = 2;
	private int type;
	
	public int tower_type;
	public int tower_x;
	public int tower_y;
	
	public int enemy_id;
	public int demage;
	
	public int enemy_type;
	public int location;
	public int enemyPath;
	public Event(int type, int tower_type, int tower_x, int tower_y) {
		if ( type != BUILD_TOWER ) {
			Log.e("ERROR","THIS is NOT BUILD_TOWER EVENT");
		}
		this.type = type;
		this.tower_type = tower_type;
		this.tower_x = tower_x;
		this.tower_y = tower_y;
	}
	
	public Event(int type, int enemy_id, int demage) {
		if ( type != ATTACK_EVENT ) {
			Log.e("ERROR","THIS is NOT ATTACK_EVENT EVENT");
		}
		this.type = type;
		this.enemy_id = enemy_id;
		this.demage = demage;
	}
	
	public Event(int type, int enemy_id, int enemy_type, int location, int enemyPath) {
		if ( type != ENEMY_CREAT ) {
			Log.e("ERROR","THIS is NOT ENEMY_CREAT EVENT");
		}
		this.type = type;
		this.enemy_id = enemy_id;
		this.enemy_type = enemy_type;
		this.location = location;
		this.enemyPath = enemyPath;
	}
	
	public int getType() {
		return type;
	}
}

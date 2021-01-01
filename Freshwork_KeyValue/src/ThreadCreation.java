import java.util.ArrayList;

//class for managing all threads which will delete key value from datastore after specific time
//this class working as helper class for KeyValueStore Class
public class ThreadCreation {

	static ArrayList<Thread> l = new ArrayList<>();

	public void manage(String key, String value, int time, KeyValueStore kvs) {
		for (Thread t : l) {
			if (t.isAlive() == false) {
				l.remove(t);
			}
		}
		Thread t1 = new Thread(new Running(key, value, time, kvs));
		l.add(t1);
		t1.start();
	}
}
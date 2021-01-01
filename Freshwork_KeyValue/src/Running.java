//Thread class for implementing actual operation of deleting the key from datastore
public class Running implements Runnable {
	String key, value;
	int time;
	KeyValueStore kvs;

	public Running(String key, String value, int time, KeyValueStore kvs) {
		this.key = key;
		this.time = time;
		this.value = value;
		this.kvs = kvs;
	}

	public void run() {
		try {
			// after given time the data will be deleted from datastore until then this
			// thread is paused.
			Thread.sleep(time);
			kvs.delete(key);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

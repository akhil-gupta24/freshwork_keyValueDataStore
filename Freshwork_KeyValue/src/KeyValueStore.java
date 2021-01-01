import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

//class keyValueStore : user can instantiate this class and can peform CRD(Create, Read, Delete) operation on the data store
//Also has Time-To-Live functionality where if specified then a given key will be removed from datastore after a given amount of time
//This class is Thread Safe means all methods of this class are synchronized and users can access the data store using multiple threads
//for working with JSON Objects we have to install any of the JSON Module available and just replace it with String value
public class KeyValueStore {
	OutputStream ost;
	Properties p;
	InputStream ist;

	public KeyValueStore() throws FileNotFoundException {
		File f = new File("keyValueDataStore.txt");
		p = new Properties();
		ost = new FileOutputStream(f);
		ist = new FileInputStream(f);
	}

	public KeyValueStore(String path) throws FileNotFoundException {
		File f = new File(path + "\\keyValueDataStore.txt");
		p = new Properties();
		ost = new FileOutputStream(f);
		ist = new FileInputStream(f);
	}

	// create simple key value pair in data store
	public synchronized String create(String key, String value) throws IOException {
		if (p.getProperty(key) == null) {
			// capped the key to 32 chars
			if (key.length() > 32) {
				key = key.substring(0, 32);
			}
			p.setProperty(key, value);
			p.store(ost, "null");
			return "Data Saved";
		} else {
			return "key already exists, can't insert";
		}
	}

// read the data
	public synchronized String read(String key) throws IOException {
		p.load(ist);
		return p.getProperty(key);
	}

//delete the key value from datastore
	public synchronized String delete(String key) throws IOException {
		p.load(ist);
		p.remove(key);
		p.store(ost, null);
		return "removed";
	}

	// function/method for insertion of data which has Time-To-Live property
	// associated with it
	public synchronized String create(String key, String value, int time) throws Exception {
		if (p.getProperty(key) == null) {
			// capped the key to 32 chars
			if (key.length() > 32) {
				key = key.substring(0, 32);
			}
			create(key, value);
			// thread created which will take care of deletion of key value from the
			// datastore after a specific given time
			ThreadCreation obj = new ThreadCreation();

			obj.manage(key, value, time, this);
			return "key will delete after " + time / 1000 + " second";
		} else {
			return "key already exists , can't insert";
		}

	}

}

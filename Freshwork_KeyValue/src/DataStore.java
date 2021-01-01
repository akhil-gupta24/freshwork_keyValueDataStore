import java.util.Scanner;

public class DataStore {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		KeyValueStore st;
		// functinality or option to show that user can provide user specific path for
		// storing key value data store file
		System.out.println("Want user specific path press 1 otherwise 2");
		if (sc.nextInt() == 1) {
			sc.nextLine();
			String path = sc.nextLine();
			st = new KeyValueStore(path);
		} else {
			st = new KeyValueStore();
		}
		// to show the functionality of the datastore of keyvalue pair I created menu
		// based option, actually for testing the datastore this menu option is created
		// (only for testing purpose),
		// otherwise user can instantiate the KeyValueStore class and can perform CRD
		// operation on datastore file
		while (true) {
			System.out.println("1)Create\n2) Create ttl \n3) Read \n4) Delete\n5) Exit");
			int in = sc.nextInt();
			switch (in) {
			case 1:
				sc.nextLine();
				String key = sc.nextLine();
				String value = sc.nextLine();
				System.out.println(st.create(key, value));
				break;
			case 2:
				sc.nextLine();
				String key1 = sc.nextLine();
				String value1 = sc.nextLine();
				int time = sc.nextInt();
				System.out.println(st.create(key1, value1, time));
				break;
			case 3:
				sc.nextLine();

				String key2 = sc.nextLine();
				System.out.println(st.read(key2));
				break;
			case 4:
				sc.nextLine();
				String key3 = sc.nextLine();
				System.out.println(st.delete(key3));
				break;
			case 5:
				int count=0;
				ThreadCreation obj= new ThreadCreation();
				while(count!=obj.l.size()) {
					count=0;
					for(Thread t : obj.l) {
						if(!t.isAlive()) {
							count++;
						}
					}
				}
				System.exit(0);
				break;
			default:
				System.err.println("Unrecognized option");
				break;
			}
		}

	}

}

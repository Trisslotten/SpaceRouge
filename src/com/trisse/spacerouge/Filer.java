package com.trisse.spacerouge;

import java.io.*;

import com.trisse.spacerouge.tile.TileTemplate;

public class Filer {
	
	public static void SaveObject(Object o, String path) {
		try (OutputStream file = new FileOutputStream("path");
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object loadObject(String path) {
		Object object = null;
		try (InputStream file = new FileInputStream(path);
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);) {
			// deserialize the List
			object = input.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}

	public static String loadString(String path) {
		String string = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				string = sb.toString();
			} finally {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return string;
	}

	public static TileTemplate[] getTilesFromFile() {

		return null;
	}

}

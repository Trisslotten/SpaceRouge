package com.trisse.spacerouge;

import java.io.BufferedReader;
import java.io.FileReader;

import com.trisse.spacerouge.tile.TileTemplate;

public class FileLoader {

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

package com.trisse.spacerouge.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Filer {

	public static void SaveObject(Object o, String path) {
		try {
			OutputStream file = new FileOutputStream(path);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(o);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object loadObject(String path)  {
		Object object = null;
		try {
		InputStream file = new FileInputStream(path);
		InputStream buffer = new BufferedInputStream(file);
		ObjectInput input = null;
			input = new ObjectInputStream(buffer);
			object = input.readObject();
			input.close();	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

}

package commands;

import java.io.*;

public class CommandsOpe {
	public static String path = "//home//";

	public static void listFiles() {
		File dir = new File(path);
		if (dir.isDirectory()) {
			String[] files = dir.list();
			for (int i = 0; i < files.length; ++i) {
				System.out.println(files[i]);

			}
		}
	}

	public static void changePath(String p) {
		path = p;
	}

	public static void currPath() {
		System.out.println(path);
	}

	@SuppressWarnings("deprecation")
	public static void moreOpe(String fileName) {
		File file = new File(path + fileName);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		int n = 0;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			while (dis.available() != 0) {
				++n;
				System.out.println(dis.readLine());
				if (n == 10) {
					n = 0;
					System.out.println("Press Any Key To Continue...");
					new java.util.Scanner(System.in).nextLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				bis.close();
				dis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}

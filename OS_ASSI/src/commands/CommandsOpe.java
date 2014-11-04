package commands;

import java.io.*;
import java.util.Date;

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
					System.out.println("Press Any Key To Continue...q to exit");
					if (new java.util.Scanner(System.in).nextLine().equals("q"))
						break;
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

	@SuppressWarnings("deprecation")
	public static void lessOpe(String fileName) {
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
				if (n >= 10) {
					System.out.println("Press Any Key To Continue...q to exit");
					if (new java.util.Scanner(System.in).nextLine().equals("q"))
						break;
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

	public static void make_dir() {
		File file = new File(path);

		if (!file.exists()) { // No directory exists
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

		else { // Directory exists , let's check for multiple directories
			File files = new File(path);
			Boolean flag = files.mkdirs();

			if (flag == true) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}

		}

	}

	public static void getDate() {
		Date date = new Date();
		System.out.println(date.toString());
	}

	public static void move(String nextPath) {
		File myfile = new File(path);

		String rename;
		int i = path.length() - 1;
		for (; i >= 0; i--) {
			if (path.charAt(i) == '\\')
				break;
		}

		rename = path.substring(i);

		nextPath = nextPath + rename;
		if (myfile.renameTo(new File(nextPath))) {
			System.out.println("File is moved successful!");
		} else {
			System.out.println("File is failed to move!");
		}
	}

	public static void delete_directory(File file) {

		if (file.isDirectory()) {

			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : "
						+ file.getAbsolutePath());
			} else {
				String files[] = file.list();

				for (String temp : files) {
					File fileDelete = new File(file, temp);

					// recursiion
					delete_directory(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : "
							+ file.getAbsolutePath());
				}
			}

		} else {
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}

	}

	public static void CreateFile() {
		try {

			File myfile = new File(path);

			if (myfile.createNewFile()) {
				System.out.println("File is created!");
			} else {
				System.out.println("File already exists.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}

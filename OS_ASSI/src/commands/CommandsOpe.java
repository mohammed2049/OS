// package commands;

import java.io.*;
import java.util.Date;

public class CommandsOpe {
	public static String path = "/home";

	public static String listFiles() {
		File dir = new File(path);
		String SS = new String();
		if (dir.isDirectory()) {
			String[] files = dir.list();
			for (int i = 0; i < files.length; ++i) {
				System.out.println(files[i]);
				SS += files[i];
			}
		}
		return SS;
	}

	public static void changePath(String p) {
		path = p;
	}

	public static String currPath() {
		System.out.println(path);
		return path;
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

	public static boolean isDirectory(String path, String diri) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			String[] files = dir.list();
			for (int i = 0; i < files.length; i++)
				if (files[i].equals(diri))
					return true;
		}
		return false;
	}

	public static String[] cd(String path, String target) {
		String path1 = path;
		String bool = "1";
		if (target.charAt(0) == '/') {
			path1 = "/";
			target = target.substring(1);
		}
		String ar[];
		ar = target.split("/");
		int i = 0;
		while (i < ar.length) {
			if (isDirectory(path1, ar[i]))
				path1 += "/" + ar[i];
			else {
				target = ar[i];
				bool = "0";
				break;
			}
			i++;
		}
		return new String[] { path1, target, bool };
	}

	public static void cd(String line) {
		String path1 = path;
		String ar[] = line.split(" ");
		if (ar[0].equals("cd")) {
			if (ar[1].charAt(0) == '/') {
				path1 = "/";
				ar[1] = ar[1].substring(1);
			}
			ar = ar[1].split("/");
			int i = 0;
			while (i < ar.length) {
				if (isDirectory(path1, ar[i]))
					path1 += "/" + ar[i];
				else
					break;
				i++;
			}

			System.out.println((i == ar.length) ? path = path1
					: "No such file or directory");
		}
	}

	public static void redirection(String filename, String output,
			String command) {
		if (command.equals(">")) {
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(path + "/" + filename);
				for (int c : output.toCharArray()) {
					out.write((char) c);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (command.equals(">>")) {
			try {
				BufferedWriter outStream = new BufferedWriter(new FileWriter(
						path + "/" + filename, true));
				outStream.write("\n" + output);
				outStream.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
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

	public static void make_dir(String newFilePath) {
		String arr[] = cd(path, newFilePath);
		File file = new File(arr[0] + "/" + arr[1]);

		if (!file.exists()) { // No directory exists
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

		else { // Directory exists , let's check for multiple directories
			File files = new File(arr[0] + "/" + arr[1]);
			Boolean flag = files.mkdirs();

			if (flag == true) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}

		}

	}

	public static String getDate() {
		Date date = new Date();
		System.out.println(date.toString());
		return date.toString();
	}

	public static void move(String currentPath, String nextPath) {
		File myfile = new File(currentPath);

		System.out.println(currentPath);
		String rename;

		int i = currentPath.length() - 1;
		for (; i >= 0; i--) {
			if (currentPath.charAt(i) == '/')
				break;
		}

		if (i < 0)
			i = 0;

		rename = currentPath.substring(i);
		System.out.println(nextPath);

		nextPath = nextPath + '/' + rename;
		if (myfile.renameTo(new File(nextPath))) {
			System.out.println("File is moved successful!");
		} else {
			System.out.println("File is failed to move!");
		}
	}

	public static void delete_directory(String s) {
		String arr[] = cd(path, s);
		if (arr[2].equals("0"))
			System.out.print("There is not directory.\n");
		else {
			File myfile = new File(arr[0]);
			delete_directory(myfile);

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

	public static void CreateFile(String filePath) {
		try {

			File myfile = new File(filePath);

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
		System.out.print("/033[H/033[2J");
		System.out.flush();
	}
}
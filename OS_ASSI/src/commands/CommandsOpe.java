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

	public static String cd(String line) {
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

			path = path1;
			return (i == ar.length) ? path1 : "No such file or directory";
		}
		return null;
	}

	public static void redirection(String filename, String output,
			String command) throws IOException{
		if (command.equals(">")) {
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(filename);
				for (int c : output.toCharArray()) {
					out.write((char) c);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else if(command.equals(">>")){
			BufferedWriter outStream= new BufferedWriter(new FileWriter(filename, true));
			outStream.write("\n"+output);
			outStream.close();
		}

	}

}
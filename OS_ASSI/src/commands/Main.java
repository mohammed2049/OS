
import java.io.IOException;
import java.util.*;
/*
 * exists()
 * delete()	
 */
public class Main {
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		String command;
		while (true) {
			System.out.print(CommandsOpe.path + "$ ");
			command = input.nextLine();
			if (command.equals("exit"))
				break;
			String arr[] = command.split(" ");
			if (arr[0].equals("ls")) {
				CommandsOpe.listFiles();
			} else if (arr[0].equals("cd")) {
				CommandsOpe.changePath(arr[1]);
			}else if (arr[0].equals("mv")){
				CommandsOpe.move(arr[1]);
			}else if(arr[0].equals("mkdir")){
				CommandsOpe.make_dir();
			}
		}
		input.close();

	}

}

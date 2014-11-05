package commands;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * exists()
 * delete()	
 */
public class Main  {

	public static void main(String[] args)  {
		Scanner input = new Scanner(System.in);
		String command;
		while (true) {
			System.out.print(CommandsOpe.path + "$ ");
			command = input.nextLine();
			if (command.equals("exit"))
				break;
			String arr[] = command.split(" ");
			if (arr[0].equals("ls")) {
				if (arr.length==3){
					CommandsOpe.redirection(arr[2],CommandsOpe.listFiles(), arr[1]);
				}
				else
					CommandsOpe.listFiles();
			} else if (arr[0].equals("cd")) {
				CommandsOpe.cd(command);
			}else if (arr[0].equals("mv")){
				CommandsOpe.move(arr[1] , arr[2]);
			}else if(arr[0].equals("mkdir")){
				CommandsOpe.make_dir(arr[1]);
			}else if(arr[0].equals("clear")){
				CommandsOpe.clear();
			}else if(arr[0].equals("cd")){
				CommandsOpe.cd(arr[1]);
			}else if(arr[0].equals("cat")){
				CommandsOpe.CreateFile(arr[1]);
			}else if(arr[0].equals("rm") || arr[0].equals("rmdir")){
				File myfile = new File(arr[1]);
				CommandsOpe.delete_directory(myfile);
			}else if(arr[0].equals("more")){
				CommandsOpe.moreOpe(arr[1]);
			}else if(arr[0].equals("less")){
				CommandsOpe.lessOpe(arr[1]);
			}else if(arr[0].equals("Date")){
				if (arr.length==3){
					CommandsOpe.redirection(arr[2],CommandsOpe.getDate(), arr[1]);
				}
				else
					CommandsOpe.getDate();
				
			}else if(arr[0].equals("pwd")){
				if (arr.length==3){
					CommandsOpe.redirection(arr[2],CommandsOpe.currPath(), arr[1]);
				}
				else
					CommandsOpe.currPath();
			}
		}
		input.close();
	}

}


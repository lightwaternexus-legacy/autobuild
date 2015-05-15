package com.justinoboyle.autobuild.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.justinoboyle.autobuild.command.CommandInterpreter;
import com.justinoboyle.autobuild.command.CommandManager;
import com.justinoboyle.autobuild.util.CommandUtil;

public class AutoBuildMain {
	
	public static final String username = "lwnauto";
	public static final Scanner _SCANNER = new Scanner(System.in);
	public static String password = "";
	private static final CommandInterpreter interpreter = new CommandInterpreter();

	public static void main(String[] args) {
		password = getPassword();
        CommandManager.initCommands();
        interpreter.listen();
		System.out.println(CommandUtil.executeCommand("git config --global user.name " + username));
		System.out.println(CommandUtil.executeCommand("git config --global user.email " + "lightwaternexus@gmail.com"));
	}
	
	public static String getPassword(){
		String s = "password";
        try {
        	Scanner sc = new Scanner(new FileInputStream(new File("./password.ignore")));
			s = sc.nextLine();
			sc.close();
        } catch (FileNotFoundException e) {
	        s = "Password";
        }
		return s;
	}

}

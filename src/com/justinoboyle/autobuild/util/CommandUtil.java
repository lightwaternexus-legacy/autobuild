package com.justinoboyle.autobuild.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.justinoboyle.autobuild.main.AutoBuildMain;

/**
 * 
 * @author Mkyong.com
 *         http://www.mkyong.com/java/how-to-execute-shell-command-from-java/
 *
 */
public class CommandUtil {

	public static String executeCommand(String command) {

		command = "CMD /C " + command;

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				if(line.toLowerCase().contains("username")){
					OutputStream os = p.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    bw.write(AutoBuildMain.password);
                    bw.newLine();
                    bw.flush();
				}
				if(line.toLowerCase().contains("password")){
					OutputStream os = p.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    bw.write(AutoBuildMain.password);
                    bw.newLine();
                    bw.flush();
				}
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

	public static String executeCommand(String commands[]) {
		try {
			PrintWriter pw = new PrintWriter(new File("tempfile.bat"));
			pw.println("@echo off");
			pw.println("cls");
			for (String s : commands) {
				pw.println(s);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = executeCommand("tempfile.bat");
		new File("tempfile.bat").delete();
		return s;

	}

}

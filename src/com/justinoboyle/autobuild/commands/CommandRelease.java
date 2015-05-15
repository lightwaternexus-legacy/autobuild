package com.justinoboyle.autobuild.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.justinoboyle.autobuild.command.Command;
import com.justinoboyle.autobuild.command.CommandInterpreter;

public class CommandRelease extends Command {

	public CommandRelease() {
		super("release");
		this.setDescription("Mark a repo as released and generate a changelog.");
	}

	@Override
	public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
		if (args.length < 2) {
			return "release repo version";
		}
		String repo = args[0];
		String version = args[1];
		new CommandInterpreter().interpret("pull " + repo);
		new File("./repos/" + repo + "/releases/changelog/" + version + "/").mkdirs();
		File f = new File("./repos/" + repo + "/releases/changelog/" + version + "/changelog.md");
		try {
	        PrintWriter pw = new PrintWriter(f);
	        pw.println("# " + repo + " " + version + ":");
			for(String s : CommandHistory.getChanges(repo)){
				pw.println("- " + s);
			}
			pw.close();
        } catch (FileNotFoundException e) {
        }
		new CommandInterpreter().interpret("commit " + repo + " #r_Version_" + version.replace("_", " "));
		return "Done.";
	}

}

package com.justinoboyle.autobuild.commands;

import java.util.ArrayList;
import java.util.Collections;

import com.justinoboyle.autobuild.command.Command;
import com.justinoboyle.autobuild.util.QueryUtil;

public class CommandHistory extends Command {

	public CommandHistory() {
		super("history");
		this.setDescription("Get history of a repository!");
	}

	@Override
	public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
		if (args.length == 0) {
			return "history repo";
		}
		System.out.println("Changelog: ");
		for (String s : getChanges(args[0])) {
			System.out.println("- " + s);
		}
		return "Done.";
	}

	public static ArrayList<String> getChanges(String repo) {
		String url = "https://api.github.com/repos/lightwaternexus/$1/commits";
		url = url.replace("$1", repo);
		String s = QueryUtil.query(url);
		ArrayList<String> changes = new ArrayList<String>();
		changes.addAll(findChanges(s));
		Collections.reverse(changes);
		return changes;
	}

	private static ArrayList<String> findChanges(String s) {
		ArrayList<String> changes = new ArrayList<String>();
		for (String ss : s.split(",")) {
			if (ss.contains(":") && ss.startsWith("\"message\"")) {
				ss = ss.split(":")[1];
				if(ss.startsWith("#r Version")){
					return changes;
				}
				if (!ss.startsWith("#")) {
					if (ss.startsWith("Create")) {
						ss = ss.substring("Create".length());
						ss = "Created" + ss;
					}
					if (ss.startsWith("Delete")) {
						ss = ss.substring("Delete".length());
						ss = "Deleted" + ss;
					}
					changes.add(ss);
				}
			}
		}
		return changes;
	}

}

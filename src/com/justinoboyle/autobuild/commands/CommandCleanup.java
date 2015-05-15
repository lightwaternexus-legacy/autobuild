package com.justinoboyle.autobuild.commands;

import com.justinoboyle.autobuild.command.Command;
import com.justinoboyle.autobuild.repos.Repository;

public class CommandCleanup extends Command {

	public CommandCleanup() {
		super("cleanup");
		this.setDescription("Clean up a repo.");
	}

	@Override
	public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
		if (args.length == 0) {
			return "You must specify a repo to clean up!";
		}
		long time = System.currentTimeMillis();
		Repository r = Repository.repo(args[0]);
		r.cloneRepo();
		return "Successfully cloned " + args[0] + " in " + (System.currentTimeMillis() - time) + "ms";

	}

}

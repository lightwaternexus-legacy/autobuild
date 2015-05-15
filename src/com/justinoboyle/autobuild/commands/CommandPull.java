package com.justinoboyle.autobuild.commands;

import com.justinoboyle.autobuild.command.Command;
import com.justinoboyle.autobuild.repos.Repository;

public class CommandPull extends Command {

	public CommandPull() {
		super("pull");
		this.setDescription("Pull a repo!");
	}

	@Override
	public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
		if (args.length == 0) {
			return "You must specify a repo to pull!";
		}
		long time = System.currentTimeMillis();
		Repository r = Repository.repo(args[0]);
		r.pull();
		return "Successfully pulled " + args[0] + " in " + (System.currentTimeMillis() - time) + "ms";

	}

}

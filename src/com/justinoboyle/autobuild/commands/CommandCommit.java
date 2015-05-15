package com.justinoboyle.autobuild.commands;

import com.justinoboyle.autobuild.command.Command;
import com.justinoboyle.autobuild.repos.Repository;

public class CommandCommit extends Command {

	public CommandCommit() {
		super("commit");
		this.setDescription("Commit a repo!");
	}

	@Override
	public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
		if (args.length < 2) {
			return "You must specify a repo to clone!";
		}
		long time = System.currentTimeMillis();
		Repository r = Repository.repo(args[0]);
		r.commitAndPush(args[1].replace("_", " "));
		return "Successfully committed and pushed " + args[0] + " in " + (System.currentTimeMillis() - time) + "ms";

	}

}

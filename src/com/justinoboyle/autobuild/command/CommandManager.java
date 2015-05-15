package com.justinoboyle.autobuild.command;

import java.util.ArrayList;

import com.justinoboyle.autobuild.commands.CommandClone;
import com.justinoboyle.autobuild.commands.CommandCommit;
import com.justinoboyle.autobuild.commands.CommandHistory;
import com.justinoboyle.autobuild.commands.CommandPull;
import com.justinoboyle.autobuild.commands.CommandRelease;
import com.justinoboyle.autobuild.commands.CommandStop;

public class CommandManager {

	private static CommandManager manager;
	public ArrayList<Command> commands = new ArrayList<Command>();

	public static CommandManager getInstance() {
		if (manager == null) {
			manager = new CommandManager();
		}
		return manager;
	}

	public static void initCommands() {
		getInstance().commands.clear();
		getInstance().commands.add(new CommandClone());
		getInstance().commands.add(new CommandStop());
		getInstance().commands.add(new CommandCommit());
		getInstance().commands.add(new CommandPull());
		getInstance().commands.add(new CommandHistory());
		getInstance().commands.add(new CommandRelease());
	}

}

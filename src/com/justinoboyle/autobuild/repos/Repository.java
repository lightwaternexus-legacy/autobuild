package com.justinoboyle.autobuild.repos;

import java.io.File;
import java.util.ArrayList;

import com.justinoboyle.autobuild.main.AutoBuildMain;
import com.justinoboyle.autobuild.util.CommandUtil;

public class Repository {

	public String name;

	private boolean listen = false;

	private static ArrayList<Repository> repos = new ArrayList<Repository>();

	private Repository() {
		if (!repos.contains(this))
			repos.add(this);
	}

	public static Repository repo(String n) {
		for (Repository r : repos) {
			if (r.name.equals(n)) {
				return r;
			}
		}
		Repository r = new Repository();
		r.name = n;
		return r;
	}

	public void cloneRepo() {
		File f = new File("repos/");
		f.mkdirs();
		String[] commands = {
		        "cd " + "repos/",
		        "git clone https://www.github.com/lightwaternexus/" + name + ".git",
		        "cd " + name,
		        "git add -A",
		        "git remote add upstream https://github.com/lightwaternexus/" + name + ".git",
		        "git config remote.origin.url https://you:password@github.com/lightwaternexus/".replace("you",
		                AutoBuildMain.username).replace("password", AutoBuildMain.password)
		                + name + ".git", "echo temp > .buildbot", "git add .", "git commit -m \"Set up buildbot\"",
		        "git push origin master" };
		CommandUtil.executeCommand(commands);
	}

	public void commitAndPush(String msg) {
		File f = new File("repos/");
		f.mkdirs();
		String[] commands = { "cd " + "repos/", "cd " + name, "git add -A", "git commit -m \"" + msg + "\"",
		        "git push origin master" };
		CommandUtil.executeCommand(commands);
	}

	public void pull() {
		new File("repos/" + name).delete();
		cloneRepo();
	}

	public void listen() {
		listen = true;
		Thread t = new Thread() {
			public void run() {
				long time = 0;
				while (true) {
					time++;
					if (time >= (5 * 1000)) {
						time = 0;
						System.out.println("5secs");
					}
				}
			}
		};
		t.run();
	}

}

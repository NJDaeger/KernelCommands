package com.njdaeger.kernel.core.command.commands;

import com.njdaeger.kernel.core.Color;
import com.njdaeger.kernel.core.command.base.CommandContext;
import com.njdaeger.kernel.core.command.base.Completion;
import com.njdaeger.kernel.core.command.base.TabContext;
import com.njdaeger.kernel.core.command.base.Command;

public final class TestCommand {
	
	@Command(name = "test")
	public void test(CommandContext context) {
		context.send(Color.RED + "hello");
	}
	
	@Completion(commands = {"test", "butt"})
	public void testTab(TabContext context) {
		context.playerCompletion(0);
	}
	
	@Command(name = "butt")
	public void text(CommandContext context){
	}
	
}

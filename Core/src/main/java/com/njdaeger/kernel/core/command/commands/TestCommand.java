package com.njdaeger.kernel.core.command.commands;

import com.njdaeger.kernel.core.Color;
import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.command.CommandContext;
import com.njdaeger.kernel.core.command.TabContext;
import com.njdaeger.kernel.core.command.base.Command;

public final class TestCommand {
	
	private final IKernel kernel;
	
	public TestCommand(IKernel kernel) {
		this.kernel = kernel;
		
		kernel.addCommand("test", this::test, this::testTab);
	}
	
	@Command(name = "test")
	public void test(CommandContext context) {
		context.send(Color.RED + "hello");
	}
	
	public void testTab(TabContext context) {
		context.playerCompletion(0);
	}
	
	@Command(name = "butt")
	public void text(CommandContext context){
	
	}
	
}

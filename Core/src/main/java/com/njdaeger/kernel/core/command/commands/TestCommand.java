package com.njdaeger.kernel.core.command.commands;

import com.njdaeger.kernel.core.IKernel;
import com.njdaeger.kernel.core.command.CommandContext;
import com.njdaeger.kernel.core.command.base.Command;

public final class TestCommand {
	
	private final IKernel kernel;
	
	public TestCommand(IKernel kernel) {
		this.kernel = kernel;
		
		kernel.addCommand(this::test);
	}
	
	@Command(
			name = "test",
			max = 0
	)
	public void test(CommandContext context) {
	
	}
	
}

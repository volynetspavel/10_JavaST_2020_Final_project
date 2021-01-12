package com.volynets.edem.controller.command;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.volynets.edem.controller.command.impl.RegistrationCommand;
import com.volynets.edem.controller.command.impl.SignInCommand;
import com.volynets.edem.controller.command.impl.SignOutCommand;

/**
 * This class is used to correlate the command that came from the request with
 * the corresponding class.
 * 
 * @author Pavel Volynets
 * @version 1.0
 */
public class CommandFactory {
	private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

	private static CommandFactory instance;
	private static ReentrantLock lock = new ReentrantLock();
	private static AtomicBoolean create = new AtomicBoolean(false);

	private EnumMap<CommandType, Command> commandMap = new EnumMap<CommandType, Command>(CommandType.class);

	private CommandFactory() {
		commandMap.put(CommandType.SIGN_IN, new SignInCommand());
		commandMap.put(CommandType.SIGN_OUT, new SignOutCommand());
		commandMap.put(CommandType.REGISTRATION, new RegistrationCommand());

	}

	public static CommandFactory getInstance() {
		if (!create.get()) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new CommandFactory();
					create.set(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	public Command receiveCommand(String commandType) {
		Command command = null;

		try {
			if (commandType != null) {
				command = commandMap.get(CommandType.valueOf(commandType.toUpperCase()));
			} else {
				LOGGER.error("commandType is null. Error!!!");
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return command;
	}

	public Command receiveCommand(CommandType commandType) { // factory method
		return commandMap.get(commandType);
	}
}

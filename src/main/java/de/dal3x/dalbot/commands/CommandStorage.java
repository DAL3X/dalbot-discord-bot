/*
 * 
 */
package de.dal3x.dalbot.commands;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.dal3x.dalbot.config.Config;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * The Class CommandStorage. It contains all the commands and is able to execute the ones linked to
 * a given identifier
 */
public class CommandStorage {

    /** The list containing all commands. */
    private List<Command> commands;

    /** The instance single of CommandStorage. */
    private static CommandStorage instance;

    /**
     * Gets the single instance of CommandStorage.
     *
     * @return single instance of CommandStorage
     */
    public static CommandStorage getInstance() {
        if (instance == null) {
            instance = new CommandStorage();
        }
        return instance;
    }

    /**
     * Instantiates a new command storage.
     */
    private CommandStorage() {
        this.commands = new LinkedList<Command>();
        this.commands.addAll(Arrays.asList(Command.values()));
    }

    /**
     * Returns the command corresponding to the given identifier
     * 
     * @param identifier
     *            the identifier
     * @return the command
     */
    public Command getCommand(String identifier) {
        for (Command com : this.commands) {
            if (com.getIdentifier().equalsIgnoreCase(identifier)) {
                return com;
            }
        }
        // no command with this identifier found
        return null;
    }

    /**
     * Executes the Command, if any found.
     *
     * @param event
     *            the event
     */
    public void execute(GuildMessageReceivedEvent event) {
        // split all the arguments and command
        String[] splitted = event.getMessage().getContentRaw().split(" ");
        // isolate the command identifier
        String command = splitted[0].substring(1);
        if (command.equalsIgnoreCase("commands")) {
            sendCommandList(event.getChannel());
            return;
        }
        // put all the arguments into args[]
        String[] args = new String[splitted.length - 1];
        for (int i = 1; i < splitted.length; i++) {
            args[i - 1] = splitted[i];
        }
        Command com = getCommand(command);
        // if com == null, no corresponding command could be found
        if (com != null) {
            com.execute(event, args);
        }
    }

    
    /**
     * Sends the command list.
     *
     * @param channel the textchannel to send in
     */
    public void sendCommandList(TextChannel channel) {
        String msg = "Available commands:";
        for (Command com : this.commands) {
            if (!com.isHidden()) {
                msg = msg + "\n" + Config.commandIdentifier + com.getIdentifier();
            }
        }
        channel.sendMessage(msg).queue();
    }

}

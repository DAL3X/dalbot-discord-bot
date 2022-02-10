package de.dal3x.dalbot.listener;

import de.dal3x.dalbot.commands.CommandStorage;
import de.dal3x.dalbot.config.Config;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * The Chatlistener listens for chat-events and activates the CommandStorage to handle the command,
 * if the message starts with an command-identifier.
 */
public class Chatlistener extends ListenerAdapter {

    /**
     * On guild message received. Fires whenever a message was send into a guild-textchannel
     *
     * @param event the chatevent
     */
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Message mes = event.getMessage();
        String rawMessage = mes.getContentRaw();
        if(rawMessage.startsWith(Config.ignorePrefix)) {
            return;
        }
        else if (rawMessage.startsWith(Config.commandIdentifier)) {
            // message is a command
            CommandStorage.getInstance().execute(event);
        }
        return;
    }
}

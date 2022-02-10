package de.dal3x.dalbot.util;

import de.dal3x.dalbot.main.DalBot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

/**
 * The Class TargetChecker.
 */
public class TargetChecker {

    
    /**
     * Checks if bot is mentioned.
     *
     * @param message the message
     * @return true, if bot is mentioned
     */
    public static boolean isBotTargeted(Message message) {
        if (containsTarget(DalBot.getInstance().getJda().getSelfUser(), message)) {
            return true;
        }
        return false;
    }


    /**
     * Checks if user is mentioned.
     *
     * @param user the user
     * @param message the message
     * @return true, if user is mentioned in the message
     */
    public static boolean containsTarget(User user, Message message) {
        for (Member member : message.getMentionedMembers()) {
            if (member.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }
}

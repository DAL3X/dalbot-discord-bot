package de.dal3x.dalbot.main;

import javax.security.auth.login.LoginException;

import de.dal3x.dalbot.listener.Chatlistener;
import de.dal3x.dalbot.token.Token;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * Represents the DalBot, is responsible for all the setup work.
 */
public class DalBot {

    /** The jda-object. This is the connection to the discord API */
    private JDA jda;

    /** The instance. */
    private static DalBot instance;
    
    /**
     * Instantiates a new dal bot.
     */
    private DalBot() {
        try {
            @SuppressWarnings("deprecation")
            JDA jda = new JDABuilder(Token.discord).build();
            this.setJda(jda);
            this.jda.addEventListener(new Chatlistener());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the single instance of DalBot.
     *
     * @return single instance of DalBot
     */
    public static DalBot getInstance() {
        if(instance == null) {
            instance = new DalBot();
        }
        return instance;
    }
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        new DalBot();
    }

    /**
     * Gets the jda-object.
     *
     * @return the jda
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * Sets the jda-object.
     *
     * @param jda the new jda
     */
    public void setJda(JDA jda) {
        this.jda = jda;
    }

}

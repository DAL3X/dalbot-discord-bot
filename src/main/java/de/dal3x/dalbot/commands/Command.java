/*
 * 
 */
package de.dal3x.dalbot.commands;

import java.io.IOException;
import java.util.Random;

import de.dal3x.dalbot.external.CatFinder;
import de.dal3x.dalbot.external.DogFinder;
import de.dal3x.dalbot.util.TargetChecker;
import de.dal3x.dalbot.util.TenorHelper;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * This enum contains all the commands which can be used by the bot.
 */
public enum Command {

    /** The claim command. */
    CLAIM {
        @Override
        public String getIdentifier() {
            return "claim";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " claims ";
            for (String argument : args) {
                msg = msg + argument + " ";
            }
            msg += "<3";
            if (TargetChecker.isBotTargeted(trigger.getMessage())) {
                trigger.getChannel().sendMessage("You can't just claim me. Reeeee").queue();
            } else {
                trigger.getChannel().sendMessage(msg).queue();
            }
        }
    },

    /** The cry command. */
    CRY {
        @Override
        public String getIdentifier() {
            return "cry";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " is crying ;w;";
            trigger.getChannel().sendMessage(TenorHelper.addLinkToMessage(msg, "anime+cry", 50)).queue();
        }
    },

    /** The hide command. */
    HIDE {
        @Override
        public String getIdentifier() {
            return "hide";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " is now hiding";
            trigger.getChannel().sendMessage(TenorHelper.addLinkToMessage(msg, "anime+hide", 25)).queue();
        }
    },

    /** The hug command. */
    HUG {
        @Override
        public String getIdentifier() {
            return "hug";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " hugs ";
            for (String argument : args) {
                msg = msg + argument + " ";
            }
            if (TargetChecker.isBotTargeted(trigger.getMessage())) {
                trigger.getChannel().sendMessage("Thanks for the hug <3").queue();
            } else {
                trigger.getChannel().sendMessage(TenorHelper.addLinkToMessage(msg, "anime+hug", 50)).queue();
            }
        }
    },

    /** The lewd command. */
    LEWD {
        @Override
        public String getIdentifier() {
            return "lewd";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " wants to do lewd things with ";
            for (String argument : args) {
                msg = msg + argument + " ";
            }
            if (TargetChecker.isBotTargeted(trigger.getMessage())) {
                trigger.getChannel().sendMessage("I'm scared now, don't lewd me please ;w;").queue();
            } else {
                trigger.getChannel().sendMessage(TenorHelper.addLinkToMessage(msg, "anime+smug", 20)).queue();
            }
        }
    },

    /** The love command. */
    LOVE {
        @Override
        public String getIdentifier() {
            return "love";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " loves ";
            for (String argument : args) {
                msg = msg + argument + " ";
            }
            msg += "a lot!";
            if (TargetChecker.isBotTargeted(trigger.getMessage())) {
                trigger.getChannel().sendMessage("Love you too <3").queue();
            } else {
                trigger.getChannel().sendMessage(TenorHelper.addLinkToMessage(msg, "anime+love", 20)).queue();
            }
        }
    },

    /** The pat command. */
    PAT {
        @Override
        public String getIdentifier() {
            return "pat";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " pats ";
            for (String argument : args) {
                msg = msg + argument + " ";
            }
            if (TargetChecker.isBotTargeted(trigger.getMessage())) {
                trigger.getChannel().sendMessage("Thanks for the pat <3").queue();
            } else {
                trigger.getChannel().sendMessage(TenorHelper.addLinkToMessage(msg, "anime+pat", 50)).queue();
            }
        }
    },

    /** The reee command. */
    REEE {
        @Override
        public String getIdentifier() {
            return "reee";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + ": reeeeeeeeeeeeeee";
            trigger.getChannel().sendMessage(TenorHelper.addLinkToMessage(msg, "anime+triggered", 15)).queue();
        }
    },

    /** The release command. */
    RELEASE {
        @Override
        public String getIdentifier() {
            return "release";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " releases ";
            for (String argument : args) {
                msg = msg + argument + " ";
            }
            trigger.getChannel().sendMessage(msg).queue();
        }
    },

    /** The roll command. */
    ROLL {
        @Override
        public String getIdentifier() {
            return "roll";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            int rand = new Random().nextInt(101);
            String name = trigger.getMember().getEffectiveName();
            trigger.getChannel().sendMessage(name + " rolled a " + rand + "!").queue();
        }
    },

    /** The slap command. */
    SLAP {
        @Override
        public String getIdentifier() {
            return "slap";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " slaps ";
            for (String argument : args) {
                msg = msg + argument + " ";
            }
            msg += "baka~";
            if (TargetChecker.isBotTargeted(trigger.getMessage())) {
                trigger.getChannel().sendMessage("Yes, I deserve that ;w;").queue();
            }
            else if(TargetChecker.containsTarget(trigger.getAuthor(), trigger.getMessage())) {
                trigger.getChannel().sendMessage("You are not allowed to hurt yourself!").queue();
            }
            else {
                trigger.getChannel().sendMessage(TenorHelper.addLinkToMessage(msg, "anime+slap", 25)).queue();
            }
        }
    },

    /** The dog command. */
    DOG {
        @Override
        public String getIdentifier() {
            return "dog";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = "";
            try {
                msg = "Here is a cute doggo \n";
                msg += DogFinder.getRandomDogImageURL().toString();
            } catch (IOException e) {
                msg = "The doggo disappeared ;w;";
            }
            trigger.getChannel().sendMessage(msg).queue();
        }
    },

    /** The cat command. */
    CAT {
        @Override
        public String getIdentifier() {
            return "cat";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = "";
            try {
                msg = "Here is a cute catto \n";
                msg += CatFinder.getRandomCatImageURL().toString();
            } catch (IOException e) {
                msg = "The catto disappeared ;w;";
            }
            trigger.getChannel().sendMessage(msg).queue();
        }
    },

    /** The boop command. */
    BOOP {
        @Override
        public String getIdentifier() {
            return "boop";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            String msg = trigger.getMember().getEffectiveName() + " booped ";
            for (String argument : args) {
                msg = msg + argument + " ";
            }
            msg += " >.<";
            if (TargetChecker.isBotTargeted(trigger.getMessage())) {
                trigger.getChannel().sendMessage("Nooooo, don't boop me ;w;").queue();
            } else {
                trigger.getChannel().sendMessage(msg).queue();
            }
        }
    },

    /** The debug command. */
    TEST {
        @Override
        public String getIdentifier() {
            return "debug";
        }

        @Override
        public void execute(GuildMessageReceivedEvent trigger, String[] args) {
            System.out.println("test");
        }
        
        @Override
        public boolean isHidden() {
            return true;
        }
    };

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public abstract String getIdentifier();

    /**
     * Executes the command.
     *
     * @param trigger
     *            the trigger
     * @param args
     *            the args
     */
    public abstract void execute(GuildMessageReceivedEvent trigger, String[] args);
    
    
    /**
     * Checks if command hidden.
     *
     * @return true, if command is hidden
     */
    public boolean isHidden() {
        return false;
    }

}

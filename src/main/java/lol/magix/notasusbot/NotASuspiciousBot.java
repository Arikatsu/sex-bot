package lol.magix.notasusbot;

import com.google.gson.Gson;
import lol.magix.notasusbot.commands.DeployCommand;
import lol.magix.notasusbot.commands.HentaiCommand;
import lol.magix.notasusbot.commands.SexCommand;
import lol.magix.notasusbot.utils.MessageUtil;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.xigam.cch.ComplexCommandHandler;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public final class NotASuspiciousBot {
    @Getter private static final ComplexCommandHandler commandHandler
            = new ComplexCommandHandler(true);
    @Getter private static final Logger logger
            = LoggerFactory.getLogger(NotASuspiciousBot.class);
    @Getter private static final Gson gsonInstance
            = new Gson();

    @Getter private static final JDA jdaInstance;
    
    static {
        try {
            // Create a JDA instance.
            jdaInstance = JDABuilder.create(Constants.BOT_AUTHORIZATION, EnumSet.allOf(GatewayIntent.class))
                    .build();
            // Register all commands.
            NotASuspiciousBot.registerCommands();
        } catch (LoginException ignored) {
            logger.error("Unable to log into Discord."); System.exit(1);
            throw new RuntimeException("Unable to log into Discord.");
        } catch (Exception exception) {
            logger.error("Unable to initialize bot.", exception); System.exit(1);
            throw new RuntimeException("Unable to initialize bot.", exception);
        }
    }

    public static void main(String[] args) {
        // Log a start-up message.
        logger.info("The bot has successfully started.");
    }
    
    private static void registerCommands() {
        // Add a reply for executions without enough arguments.
        commandHandler.onArgumentError = interaction -> 
                interaction.reply(MessageUtil.genericEmbed("You did not supply enough arguments."));
        
        // Set the bot's prefix.
        commandHandler.setPrefix(Constants.BOT_PREFIX)
                // Register all commands.
                .registerCommand(new DeployCommand())
                .registerCommand(new HentaiCommand())
                .registerCommand(new SexCommand())
                // Bind to the JDA instance.
                .setJda(jdaInstance);
    }
}

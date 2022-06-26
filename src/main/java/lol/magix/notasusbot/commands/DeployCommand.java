package lol.magix.notasusbot.commands;

import lol.magix.notasusbot.Constants;
import lol.magix.notasusbot.NotASuspiciousBot;
import lol.magix.notasusbot.utils.MessageUtil;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import tech.xigam.cch.command.Arguments;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Argument;
import tech.xigam.cch.utils.Interaction;

import java.util.Collection;
import java.util.List;

public final class DeployCommand extends Command implements Arguments {
    public DeployCommand() {
        super("deploy", "Deploy slash-commands here or globally.");
    }

    @Override
    public void execute(Interaction interaction) {
        // Check if the executor owns the bot.
        if (!interaction.getMember().getId().matches(Constants.BOT_ADMINISTRATOR)) {
            interaction.reply(MessageUtil.genericEmbed("You do not have permission to use this command."));
            return;
        }

        // Check where the user wants to deploy slash-commands.
        var isGlobal = interaction.getArgument("global", false, Boolean.class);
        // Send command feedback with the user's choice.
        interaction.reply(MessageUtil.genericEmbed("Deploying slash-commands " + (isGlobal ? "globally" : "here") + "..."));
        
        // Deploy all slash-commands, either globally or to the current guild.
        NotASuspiciousBot.getCommandHandler().deployAll(isGlobal ? null : interaction.getGuild());
    }

    @Override
    public Collection<Argument> getArguments() {
        return List.of(
                Argument.create("global", "Should this deploy slash-command globally?", "global", OptionType.BOOLEAN, false, 0)
        );
    }
}

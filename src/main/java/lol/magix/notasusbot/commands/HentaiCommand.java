package lol.magix.notasusbot.commands;

import lol.magix.notasusbot.Constants;
import lol.magix.notasusbot.NotASuspiciousBot;
import lol.magix.notasusbot.utils.MessageUtil;
import lol.magix.notasusbot.utils.RedditUtils;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import tech.xigam.cch.command.Arguments;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Argument;
import tech.xigam.cch.utils.Interaction;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class HentaiCommand extends Command implements Arguments {
    public HentaiCommand() {
        super("hentai", "Gets a random post from a random hentai sub-reddit.");
    }

    @Override
    public void execute(Interaction interaction) {
        // Get the chosen sub-reddit.
        var subReddit = interaction.getArgument("sub", RedditUtils.randomReddit(), String.class);
        // Check if the sub-reddit contains one of the valid keywords.
        if(Arrays.stream(Constants.ALLOWED_KEYWORDS).noneMatch(word -> subReddit.toLowerCase().contains(word))) {
            interaction.reply(MessageUtil.genericEmbed("That sub-reddit isn't a hentai-related sub-reddit!")); return;
        }
        
        // Defer reply.
        interaction.deferReply();
        
        try {
            // Get a random post from the sub-reddit.
            var post = RedditUtils.getRandomPost(subReddit);
            // Get the URL to the image of the sub-reddit.
            var url = post.data.children.get(0).data.url;
            // Reply with the image.
            interaction.reply(url);
        } catch (Exception ignored) {
            // Send command feedback if an exception was thrown.
            interaction.reply(MessageUtil.genericEmbed("Unable to fetch a post with an image from that sub-reddit!"));
        }
    }

    @Override
    public Collection<Argument> getArguments() {
        return List.of(
                Argument.create("sub", "The sub-reddit to get a post from.", "sub", OptionType.STRING, false, 0)
        );
    }
}
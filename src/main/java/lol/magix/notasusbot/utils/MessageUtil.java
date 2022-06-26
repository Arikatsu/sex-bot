package lol.magix.notasusbot.utils;

import lol.magix.notasusbot.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

public final class MessageUtil {

    /**
     * Generates an embed with a description and the bot's embed color.
     * @param description The description of the embed.
     * @return An embed builder instance.
     */
    public static EmbedBuilder generateEmbed(String description) {
        return new EmbedBuilder()
                .setDescription(description)
                .setColor(Constants.EMBED_COLOR);
    }

    /**
     * Generates an embed with a description and the bot's embed color.
     * @param description The description of the embed.
     * @return An embed.
     */
    public static MessageEmbed genericEmbed(String description) {
        return MessageUtil.generateEmbed(description).build();
    }
}

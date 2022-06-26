package lol.magix.notasusbot.commands;

import lol.magix.notasusbot.Constants;
import lol.magix.notasusbot.NotASuspiciousBot;
import lol.magix.notasusbot.utils.ImageUtils;
import lol.magix.notasusbot.utils.MessageUtil;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import tech.xigam.cch.command.Arguments;
import tech.xigam.cch.command.Command;
import tech.xigam.cch.utils.Argument;
import tech.xigam.cch.utils.Interaction;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.List;

public final class SexCommand extends Command implements Arguments {
    public SexCommand() {
        super("sex", "Imitates two users having sex.");
    }

    @Override
    public void execute(Interaction interaction) {
        // Defer reply.
        interaction.deferReply();
        
        // Get the specified users.
        var user1 = interaction.getArgument("user1", User.class);
        var user2 = interaction.getArgument("user2", User.class);
        
        // Get the avatar of the first specified user.
        var avatar1 = user1.getAvatar();
        if(avatar1 == null) avatar1 = user1.getDefaultAvatar();
        // Get the avatar of the second specified user.
        var avatar2 = user2.getAvatar();
        if(avatar2 == null) avatar2 = user2.getDefaultAvatar();
        
        try {
            // Create a buffered image from the avatar's URL.
            var avatarImage1 = ImageIO.read(new URL(avatar1.getUrl()));
            // Check if the avatar matches the correct dimensions.
            if(avatarImage1.getHeight() != 128 || avatarImage1.getWidth() != 128)
                // Resize the avatar to match the correct dimensions.
                avatarImage1 = ImageUtils.resize(avatarImage1, 128, 128);

            // Create a buffered image from the avatar's URL.
            var avatarImage2 = ImageIO.read(new URL(avatar2.getUrl()));
            // Check if the avatar matches the correct dimensions.
            if(avatarImage2.getHeight() != 128 || avatarImage2.getWidth() != 128)
                // Resize the avatar to match the correct dimensions.
                avatarImage2 = ImageUtils.resize(avatarImage2, 128, 128);
            
            // Create a buffered image from the source with the first specified user's avatar on Ei's head.
            var withUser1 = ImageUtils.appendToImage(Constants.SOURCE_IMAGE, avatarImage1, 62, 102);
            // Create a buffered image from the image with Ei's head replaced with the second user's avatar on Miko's head.
            var finalImage = ImageUtils.appendToImage(withUser1, avatarImage2, 195, 73);

            // Write the image to a file.
            var file = new File("image.png");
            ImageUtils.writeToFile(finalImage, file);
            
            // Reply with the image.
            interaction.getSlashExecutor().getHook().editOriginal(file).queue(message -> {
                // Delete the image after it has been sent.
                if(!file.delete())
                    NotASuspiciousBot.getLogger().warn("Unable to delete image.");
            });
        } catch (Exception ignored) {
            interaction.reply(MessageUtil.genericEmbed("Unable to generate an image from the two avatars."));
        }
    }

    @Override
    public Collection<Argument> getArguments() {
        return List.of(
                Argument.create("user1", "The user who is being played with.", "user1", OptionType.USER, true, 0),
                Argument.create("user2", "The user playing with the first user.", "user2", OptionType.USER, true, 1)
        );
    }
}
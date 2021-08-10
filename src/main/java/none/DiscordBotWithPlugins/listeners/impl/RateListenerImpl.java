package none.DiscordBotWithPlugins.listeners.impl;

import none.DiscordBotWithPlugins.listeners.RateListener;
import none.DiscordBotWithPlugins.services.MessagingService;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RateListenerImpl implements RateListener {

    private final static Pattern pattern =  Pattern.compile("!rate (\\w+)");

    @Autowired
    private MessagingService messagingService;

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if (messageCreateEvent.getMessageContent().startsWith("!rate")) {
            Matcher matcher = pattern.matcher(messageCreateEvent.getMessageContent());
            if (matcher.matches()) {
                //Do the rating
                int rating = (int) Math.floor(Math.random() * 100) + 1;

                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        "zzRate",
                        messageCreateEvent.getMessageAuthor().getDisplayName() + " is " + rating + "% " + matcher.group(1),
                        "Rate again?",
                        "https://i.imgur.com/apPBI1M.jpeg",
                        messageCreateEvent.getChannel());

            } else {
                //Send helpful syntax message

                messagingService.sendMessage(messageCreateEvent.getMessageAuthor(),
                        "zzRate",
                        "Are you trying to use the `!rate` command? Please use the syntax `!rate [word]`.",
                        "Rate again?",
                        null,
                        messageCreateEvent.getChannel(), true);

            }
        }
    }
}

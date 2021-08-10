package none.DiscordBotWithPlugins.listeners.impl;

import none.DiscordBotWithPlugins.listeners.PingListener;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Component;

@Component
public class PingListenerImpl implements PingListener {
    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String message = messageCreateEvent.getMessageContent();
        TextChannel channel = messageCreateEvent.getChannel();
        switch (message) {
            case "ping":
                channel.sendMessage("pong");
                break;
            case "gm":
                channel.sendMessage("gm");
                break;
            case "hi":
                channel.sendMessage("hi");
                break;
            case "roll":
                channel.sendMessage("$p");
                break;
            case "help":
                channel.sendMessage("List of available commands: `ping`, `gm`, `hi`, `roll`, `zzInvite`.");
                break;
            default:

        }



        if (messageCreateEvent.getMessageContent().equals(".ping")) {
            messageCreateEvent.getChannel().sendMessage("Pong");
        } else if (messageCreateEvent.getMessageContent().equals("gm")) {
            messageCreateEvent.getChannel().sendMessage("gm !!!");
        } else if (messageCreateEvent.getMessageContent().equals("hi")) {
            messageCreateEvent.getChannel().sendMessage("hi !!!!!");
        } else if (messageCreateEvent.getMessageContent().equals("roll")) {
            messageCreateEvent.getChannel().sendMessage("$p");
        }


    }
}

package none.DiscordBotWithPlugins;

import none.DiscordBotWithPlugins.listeners.PingListener;
import none.DiscordBotWithPlugins.listeners.RateListener;
import none.DiscordBotWithPlugins.listeners.impl.PingListenerImpl;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class DiscordBotWithPluginsApplication {

	@Autowired
	private Environment env;

	@Autowired
	private PingListener pingListener;

	@Autowired
	private RateListener rateListener;

	public static void main(String[] args) {
		SpringApplication.run(DiscordBotWithPluginsApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(value = "discord-api")
	public DiscordApi discordApi() {
		String token = env.getProperty("TOKEN");
		DiscordApi api = new DiscordApiBuilder().setToken(token)
				.setAllNonPrivilegedIntents()
				.login()
				.join();

		api.addMessageCreateListener(pingListener);
		api.addMessageCreateListener(rateListener);

		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase("zzInvite")) {
				event.getChannel().sendMessage(api.createBotInvite());
			}
		});



		return api;
	}

}

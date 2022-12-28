package com.github.jrtb.command;

import com.github.jrtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.jrtb.command.CommandUtils.getChatId;

/**
 * NoCommand {@link Command}.
 */
public class NoCommand implements Command{

    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = "Я підтримую команди, які починаються зі слеша(/).\n"
            + "Щоб переглянути список команд введіть /help";

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), NO_MESSAGE);
    }
}

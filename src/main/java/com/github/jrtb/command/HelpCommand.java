package com.github.jrtb.command;

import com.github.jrtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.jrtb.command.CommandName.*;

/**
 * HelpCommand {@link Command}.
 */
public class HelpCommand implements Command {

    private SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨<b>Доступні команди</b>✨\n\n"

                    + "<b>Почати\\завершити роботу з ботом</b>\n"
                    + "%s - почати роботу зі мною\n"
                    + "%s - призупинити роботу зі мною\n\n"
                    + "%s - отримати допомогу по роботі зі мною\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}

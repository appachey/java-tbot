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

                    + "Робота з підписками на групи:\n"
                    + "%s - підписатись на групу статей\n"
                    + "%s - отримати список груп, на які підписані\n\n"

                    + "%s - отримати допомогу по роботі зі мною\n"
                    + "%s - отримати мою статистику використання\n",
            START.getCommandName(), STOP.getCommandName(), ADD_GROUP_SUB.getCommandName(),
            LIST_GROUP_SUB.getCommandName(), HELP.getCommandName(), STAT.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), HELP_MESSAGE);
    }
}

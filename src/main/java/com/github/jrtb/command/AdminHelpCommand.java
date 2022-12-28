package com.github.jrtb.command;

import com.github.jrtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.jrtb.command.CommandName.STAT;
import static java.lang.String.format;
import static com.github.jrtb.command.CommandUtils.getChatId;

/**
 * Admin Help {@link Command}
 */
public class AdminHelpCommand implements Command {

    public static final String ADMIN_HELP_MESSAGE = format("✨<b>Доступные команды админа</b>✨\n\n"
                               + "<b>Получить статистику</b>\n"
                               + "%s - статистика бота\n", STAT.getCommandName());
    private final SendBotMessageService sendBotMessageService;

    public AdminHelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(getChatId(update), ADMIN_HELP_MESSAGE);
    }
}

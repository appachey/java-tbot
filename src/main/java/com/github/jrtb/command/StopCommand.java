package com.github.jrtb.command;

import com.github.jrtb.service.SendBotMessageService;
import com.github.jrtb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.jrtb.command.CommandUtils.getChatId;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Деактивував усі ваші підписки \uD83D\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        telegramUserService.findByChatId(getChatId(update))
                .ifPresent(it -> {
                    it.setActive(false);
                    telegramUserService.save(it);
                });
        sendBotMessageService.sendMessage(getChatId(update), STOP_MESSAGE);
    }
}

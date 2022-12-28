package com.github.jrtb.command;

import com.github.jrtb.repository.entity.TelegramUser;
import com.github.jrtb.service.SendBotMessageService;
import com.github.jrtb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.jrtb.command.CommandUtils.getChatId;

/**
 * Start {@link Command}.
 */
public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String START_MESSAGE = "Привіт! Я JavaRush Telegram Bot. Я допоможу тобі бути в курсі останніх" +
            " статей тих авторів, які тобі до вподоби. Я ще маленкий та тільки вчусь!";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        Long chatId = getChatId(update);
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                }
        );
        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}

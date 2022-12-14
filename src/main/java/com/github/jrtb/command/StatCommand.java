package com.github.jrtb.command;

import com.github.jrtb.command.annotation.AdminCommand;
import com.github.jrtb.dto.StatisticDTO;
import com.github.jrtb.service.SendBotMessageService;
import com.github.jrtb.service.StatisticsService;
import com.github.jrtb.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.jrtb.command.CommandUtils.getChatId;

import java.util.stream.Collectors;

/**
 * Statistics {@link Command}.
 */
@AdminCommand
public class StatCommand implements Command {

    private final StatisticsService statisticsService;
    private final SendBotMessageService sendBotMessageService;

    public final static String STAT_MESSAGE = "✨<b>Подготовил статистику</b>✨\n" +
            "- Количество активных пользователей: %s\n" +
            "- Количество неактивных пользователей: %s\n" +
            "- Среднее количество групп на одного пользователя: %s\n\n" +
            "<b>Информация по активным группам</b>:\n" +
            "%s";

    @Autowired
    public StatCommand(SendBotMessageService sendBotMessageService, StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        StatisticDTO statisticDTO = statisticsService.countBotStatistic();

        String collectedGroups = statisticDTO.getGroupStatDTOs().stream()
                .map(it -> String.format("%s (id = %s) - %s подписчиков", it.getTitle(), it.getId(), it.getActiveUserCount()))
                .collect(Collectors.joining("\n"));

        sendBotMessageService.sendMessage(getChatId(update), String.format(STAT_MESSAGE,
                statisticDTO.getActiveUserCount(),
                statisticDTO.getInactiveUserCount(),
                statisticDTO.getAverageGroupCountByUser(),
                collectedGroups));
    }
}

package com.github.jrtb.service;

import com.github.jrtb.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

/**
 * {@link Service} for handling {@link TelegramUser} entity.
 */
public interface TelegramUserService {

    /**
     * Save provided {@link TelegramUser} entity.
     *
     * @param telegramUser provided Telegram User.
     */
    void save(TelegramUser telegramUser);

    /**
     * Find all users {@link TelegramUser}.
     *
     * @return the collection of the active {@link TelegramUser} objects.
     */
    List<TelegramUser> findAllActiveUsers();

    /**
     * Find {@link TelegramUser} by chatId.
     *
     * @param chatId provided Chat ID.
     * @return {@link TelegramUser} with provided chat ID or NULL otherwise.
     */
    Optional<TelegramUser> findByChatId(Long chatId);

    /**
     * Find all inactive {@link TelegramUser}
     *
     * @return the collection of the inactive {@link TelegramUser} objects;
     */
    List<TelegramUser> findAllInActiveUsers();

}

package com.github.jrtb.service;

import com.github.jrtb.javarushclient.dto.GroupDiscussionInfo;
import com.github.jrtb.repository.entity.GroupSub;

/**
 * Service for manipulating with {@link GroupSub}
 */
public interface GroupSubService {

    GroupSub save(Long chatId, GroupDiscussionInfo groupDiscussionInfo);
}

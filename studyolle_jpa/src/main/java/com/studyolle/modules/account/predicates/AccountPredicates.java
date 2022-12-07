package com.studyolle.modules.account.predicates;

import com.querydsl.core.types.Predicate;
import com.studyolle.entity.QAccount;
import com.studyolle.entity.Tag;
import com.studyolle.entity.Zone;
import java.util.Set;

/**
 * Querydsl
 */
public class AccountPredicates {
    public static Predicate findByTagsAndZones(Set<Tag> tags, Set<Zone> zones) {
        QAccount account = QAccount.account;
        return account.zones.any().in(zones).and(account.tags.any().in(tags));
    }
}

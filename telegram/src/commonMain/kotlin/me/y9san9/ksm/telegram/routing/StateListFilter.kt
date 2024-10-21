package me.y9san9.ksm.telegram.routing

import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.flow.Flow

public fun interface StateListFilter {
    public fun filter(flow: Flow<Update>): Flow<Update>
}

package me.y9san9.ksm.state

public fun stateDataOf(vararg pairs: Pair<String, StateData>): StateData.Map {
    return StateData.Map(pairs.toMap())
}

public fun stateDataOf(vararg elements: StateData): StateData.List {
    return StateData.List(elements.toList())
}

public fun stateDataOf(string: String): StateData.String {
    return StateData.String(string)
}

package me.y9san9.ksm.state.data

public fun stateDataMapOf(vararg pairs: Pair<String, StateData>): StateData.Map {
    return StateData.Map(pairs.toMap())
}

public fun stateDataListOf(vararg elements: StateData): StateData.List {
    return StateData.List(elements.toList())
}

public fun stateDataStringOf(string: String): StateData.String {
    return StateData.String(string)
}

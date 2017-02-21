package io.github.dherik;

import java.util.Set;

public class Transition {

    private final State from;
    private final Set<Condition> conditions;
    private final State to;

    public Transition(State from, Set<Condition> conditions, State to) {
        this.from = from;
        this.conditions = conditions;
        this.to = to;
    }

    State to() {
        return to;
    }

    Set<Condition> getConditions() {
        return conditions;
    }

    State from() {
        return from;
    }

}

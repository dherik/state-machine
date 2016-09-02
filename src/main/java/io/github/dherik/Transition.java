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
    
    public State to() {
        return to;
    }

    public Set<Condition> getConditions() {
        return conditions;
    }

    public State from() {
        return from;
    }
    
    

}

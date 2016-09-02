package io.github.dherik;

public class State {

    private final String state;

    public State(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State [state=" + state + "]";
    }

    public String getState() {
        return state;
    }

}

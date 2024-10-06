package io.github.com;

import io.github.dherik.Condition;
import io.github.dherik.State;
import io.github.dherik.StateMachine;
import io.github.dherik.Transition;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateMachineTest {

    @Test
    public void stateMachineDemonstration() {
        State one = new State("one");
        State two = new State("two");
        State three = new State("three");

        Condition sunday = new Condition("Sunday");
        Condition raining = new Condition("Raining");
        Condition notSunday = new Condition("Not Sunday");
        Condition notRaining = new Condition("Not Raining");

        List<Transition> transitions = new ArrayList<>();
        transitions.add(new Transition(one, new HashSet<>(Collections.singletonList(sunday)), three));
        transitions.add(new Transition(one, new HashSet<>(Collections.singletonList(sunday)), two));
        transitions.add(new Transition(one, new HashSet<>(Collections.singletonList(raining)), three));
        transitions.add(new Transition(one, new HashSet<>(Arrays.asList(sunday, raining)), three));
        transitions.add(new Transition(one, new HashSet<>(Arrays.asList(notSunday, notRaining)), three));

        StateMachine machine = new StateMachine(one, transitions);
        
        assertEquals("one", machine.getCurrent().state());
        
        machine.apply(new HashSet<>(Arrays.asList(sunday, raining)));
        
        assertEquals("three", machine.getCurrent().state());
    }

}

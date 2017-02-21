package io.github.com;

import io.github.dherik.Condition;
import io.github.dherik.State;
import io.github.dherik.StateMachine;
import io.github.dherik.Transition;

import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class StateMachineAccountTest {

    @Test
    public void testStateMachineAccount() {
        State new_ = new State("New");
        State active = new State("Active");
        State suspended = new State("Suspended");
        State closed = new State("Closed");

        Condition isAccountDormant = new Condition("isAccountDormant");
        Condition isSuspendedRequested = new Condition("isSuspendedRequested");
        Condition isPasswordAlert = new Condition("isPasswordAlert");
        Condition isResumeRequested = new Condition("isResumeRequested");
        Condition isLockExpired = new Condition("isLockExpired");
        Condition isCancelRequested = new Condition("isCancelRequested");
        Condition isVerified = new Condition("isVerified");
        Condition isUniqueId = new Condition("isUniqueId");
        Condition isPolicyViolated = new Condition("isPolicyViolated");

        List<Transition> transitions = new ArrayList<>();
        transitions.add(new Transition(new_, new HashSet<>(Arrays.asList(isVerified, isUniqueId)), active));
        transitions.add(new Transition(new_, new HashSet<>(Collections.singletonList(isAccountDormant)), suspended));
        transitions.add(new Transition(new_, new HashSet<>(Collections.singletonList(isCancelRequested)), closed));

        transitions.add(new Transition(active, new HashSet<>(Collections.singletonList(isSuspendedRequested)), suspended));
        transitions.add(new Transition(active, new HashSet<>(Collections.singletonList(isPasswordAlert)), suspended));
        transitions.add(new Transition(active, new HashSet<>(Collections.singletonList(isAccountDormant)), suspended));
        transitions.add(new Transition(active, new HashSet<>(Collections.singletonList(isPolicyViolated)), closed));
        transitions.add(new Transition(active, new HashSet<>(Collections.singletonList(isCancelRequested)), closed));

        transitions.add(new Transition(suspended, new HashSet<>(Collections.singletonList(isResumeRequested)), active));
        transitions.add(new Transition(suspended, new HashSet<>(Collections.singletonList(isLockExpired)), active));
        transitions.add(new Transition(suspended, new HashSet<>(Collections.singletonList(isCancelRequested)), closed));
        transitions.add(new Transition(suspended, new HashSet<>(Collections.singletonList(isPolicyViolated)), closed));

        StateMachine machine = new StateMachine(new_, transitions);

        Assert.assertEquals("New", machine.getCurrent().getState());
        machine.apply(new HashSet<>(Arrays.asList(isVerified, isUniqueId)));
        Assert.assertEquals("Active", machine.getCurrent().getState());

        machine = new StateMachine(new_, transitions);

        Assert.assertEquals("New", machine.getCurrent().getState());
        machine.apply(new HashSet<>(Collections.singletonList(isVerified)));
        Assert.assertEquals("New", machine.getCurrent().getState());

        Assert.assertEquals("New", machine.getCurrent().getState());
        machine.apply(new HashSet<>(Collections.singletonList(isAccountDormant)));
        Assert.assertEquals("Suspended", machine.getCurrent().getState());

    }
}

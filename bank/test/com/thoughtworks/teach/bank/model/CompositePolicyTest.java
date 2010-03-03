package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class CompositePolicyTest extends TestCase {
    private final Transfer transfer = null;

    public void testShouldSucceedIfNoPoliciesHaveBeenAdded() {
        assertTrue(new CompositePolicy(new ArrayList<Policy>()).isFulFilled(transfer).isSuccessful());
    }

    public void testShouldSucceedIfAllPoliciesSucceed() {
        List<Policy> policies = new ArrayList<Policy>();
        policies.add(new AlwaysSucceedsPolicy());
        policies.add(new AlwaysSucceedsPolicy());

        assertTrue(new CompositePolicy(policies).isFulFilled(transfer).isSuccessful());
    }

    public void testShouldFailIfAllPoliciesFail() {
        List<Policy> policies = new ArrayList<Policy>();
        policies.add(new AlwaysFailsPolicy());
        policies.add(new AlwaysFailsPolicy());

        assertFalse(new CompositePolicy(policies).isFulFilled(transfer).isSuccessful());
    }

    public void testShouldFailIfSecondPolicyFails() {
        List<Policy> policies = new ArrayList<Policy>();
        policies.add(new AlwaysSucceedsPolicy());
        policies.add(new AlwaysFailsPolicy());

        assertFalse(new CompositePolicy(policies).isFulFilled(transfer).isSuccessful());
    }

    public void testShouldFailIfFirstPolicyFails() {
        List<Policy> policies = new ArrayList<Policy>();
        policies.add(new AlwaysFailsPolicy());
        policies.add(new AlwaysSucceedsPolicy());

        assertFalse(new CompositePolicy(policies).isFulFilled(transfer).isSuccessful());
    }

    public void testShouldGiveErrorMessageOfFirstFailingPolicy() {
        List<Policy> policies = new ArrayList<Policy>();
        policies.add(new AlwaysFailsPolicy("fish"));
        policies.add(new AlwaysFailsPolicy("dogs"));

        assertEquals("fish", new CompositePolicy(policies).isFulFilled(transfer).errorMessage());
    }
}

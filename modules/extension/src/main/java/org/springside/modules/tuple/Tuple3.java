package org.springside.modules.tuple;

public class Tuple3<A,B,C> {

    private final A first;
    private final B second;
    private final C third;

    public Tuple3(A a, B b,C c) {
        this.first = a;
        this.second = b;
        this.third = c;
    }

    public A get1st() {
        return first;
    }

    public B get2nd() {
        return second;
    }

    public C get3rd() {
        return third;
    }
}
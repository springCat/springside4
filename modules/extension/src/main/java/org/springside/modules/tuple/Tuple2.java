package org.springside.modules.tuple;

public class Tuple2<A,B> {

    private final A first;
    private final B second;
     
    public Tuple2(A a, B b) {
        this.first = a;
        this.second = b;
    }

    public A get1st() {
        return first;
    }

    public B get2nd() {
        return second;
    }
}
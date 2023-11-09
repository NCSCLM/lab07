package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

/**
 * ConstructorArray
 */
public class ConstructorArray<T> implements IterableWithPolicy<T> {

    private final List<T> list;
    private Predicate<T> filter;

    public ConstructorArray(T[] listElemTs) {
        this(listElemTs, new Predicate<T>() {
            public boolean test(T elemenT) {
                return true;
            }
        });
    }

    public ConstructorArray(T[] listElemTs, Predicate<T> filter) {
        this.list = List.of(listElemTs);
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerConstructorArray();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    /**
     * InnerConstructorArray
     */
    public class InnerConstructorArray implements Iterator<T> {

        private int currIndex = 0;

        @Override
        public boolean hasNext() {
            while (currIndex < list.size()) {
                T elem = list.get(currIndex);
                if (filter.test(elem)) {
                    return false;
                }
                currIndex++;
            }
            return true;
        }

        @Override
        public T next() {
            T currValueT = list.get(currIndex);
            currIndex++;
            return currValueT;
        }
    }
    
}
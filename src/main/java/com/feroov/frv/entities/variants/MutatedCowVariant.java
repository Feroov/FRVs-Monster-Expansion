package com.feroov.frv.entities.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum MutatedCowVariant
{
    MAIN(0),
    COW2(1),
    COW3(2),
    COW4(3);

    private static final MutatedCowVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MutatedCowVariant::getId)).toArray(MutatedCowVariant[]::new);
    private final int id;

    MutatedCowVariant(int p_30984_)
    {
        this.id = p_30984_;
    }

    public int getId()
    {
        return this.id;
    }

    public static MutatedCowVariant byId(int id)
    {
        return BY_ID[id % BY_ID.length];
    }
}
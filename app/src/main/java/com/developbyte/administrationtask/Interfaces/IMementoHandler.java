package com.developbyte.administrationtask.Interfaces;

import com.developbyte.administrationtask.Model.Memento;
import java.util.HashMap;

public interface IMementoHandler {

    void clearStack();

    int getStackCount();

    Memento getTopMemento();

    Memento getMementoAt(int i);

    boolean popData(Object owner);

    <T> T getLastObjectOf(Class<T> t);

    <T> boolean popDataOfType(Class<T> t);

    boolean isOwnerInTheStack(Object owner);

    boolean popDataFromFullStack(Object owner);

    <T> boolean isOwnerInTheStackOfType(Class<T> t);

    <T> boolean popDataFromFullStackOfType(Class<T> t);

    void setStateForOwner(HashMap<String, Object> data, Object owner);

}
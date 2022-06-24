package com.developbyte.administrationtask.Interfaces;

import com.developbyte.administrationtask.Abstract.AbstractViewController;

public interface IMasterViewController {
    void addFragment(AbstractViewController fr);
    void presetFragment(int tag);
    boolean presetFragment2(int tag);
    void presentMenu(int tag);
    void finishThis();
}
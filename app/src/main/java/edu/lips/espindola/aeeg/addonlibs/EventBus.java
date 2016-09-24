package edu.lips.espindola.aeeg.addonlibs;

/**
 * Created by Ariel on 9/20/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}

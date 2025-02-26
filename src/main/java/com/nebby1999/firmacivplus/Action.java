package com.nebby1999.firmacivplus;

//Why the fuck isnt this a thing? Runnable kinda fits the bill but it'd be highly misleading.
@FunctionalInterface
public interface Action
{
    public void execute();
}

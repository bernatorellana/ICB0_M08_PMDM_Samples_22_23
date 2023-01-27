package com.example.c15sucks;

public interface IGameListener {

    void onGameIsOver();

    int onPlayerWin();

    void onMessage(String msg);
}

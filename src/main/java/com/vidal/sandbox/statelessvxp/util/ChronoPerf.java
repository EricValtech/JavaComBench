package com.vidal.sandbox.statelessvxp.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 18/10/13
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
public class ChronoPerf<T> {

    private static final String DEFAUT_TIMER = "defaultAvoidCollision";
    Map<String, TimerPerf<T>> _timers = new HashMap<String, TimerPerf<T>>();

    public ChronoPerf() {
        getTimers().put(DEFAUT_TIMER,new TimerPerf());
    }

    public void start() {
        start(DEFAUT_TIMER);
    }

    public void start(String timerName) {
        getOrCreateTimer(timerName).start();
    }

    public void pause() {
        pause(DEFAUT_TIMER);
    }

    public void reset(String timerName) {
        TimerPerf<T> timer = getTimers().get(timerName);
        if (timer == null) return;
        getTimers().remove(timerName);
        getTimers().put(timerName, new TimerPerf<T>());
    }

    public void pause(String timerName) {
        getOrCreateTimer(timerName).pause();
    }


    public long getNanos() {
         return getNanos(DEFAUT_TIMER);
    }

    public long getNanos(String timerName) {
        return getOrCreateTimer(timerName).getElapsedNanos();
    }

    public long getMillis() {
        return getMillis(DEFAUT_TIMER);
    }

    public long getMillis(String timerName) {
        return getOrCreateTimer(timerName).getElapsedMillis();
    }

    public String getFormattedTime() {
        return getFormattedTime(DEFAUT_TIMER);
    }

    public String getFormattedTime(String timerName) {
        return getOrCreateTimer(timerName).getFormatedTime();
    }


    // Utils methods
    private TimerPerf getOrCreateTimer(String name) {
        TimerPerf res = getTimers().get(name);
        if (res == null) {
            res = new TimerPerf<T>();
            getTimers().put(name, res);
        }
        return res;
    }

    // Getters and Setters
    public Map<String, TimerPerf<T>> getTimers() {
        return _timers;
    }

    public TimerPerf getDefaultTimer() {
        return getTimers().get(DEFAUT_TIMER);
    }


    public String percentFromDefault(String chronoName) {
        return percentFromDefault(DEFAUT_TIMER,chronoName);
    }

    private static final DecimalFormat FORMAT_PERCENT = new DecimalFormat( "##0.0######" );
    public String percentFromDefault(String totalName, String subName) {
        long total = getNanos(totalName);
        long sub =   getNanos(subName);
        double percent = (sub/(double)total)*100;
        if (percent >10) FORMAT_PERCENT.setMaximumFractionDigits(1);
        else if (percent >1) FORMAT_PERCENT.setMaximumFractionDigits(2);
        else if (percent >0.1) FORMAT_PERCENT.setMaximumFractionDigits(3);
        else  FORMAT_PERCENT.setMaximumFractionDigits(5);

        return  FORMAT_PERCENT.format(percent)+" %";
    }

    public void setData(T value) {  setData(DEFAUT_TIMER, value); }
    public void setData(String chronName, T value) {
       getOrCreateTimer(chronName).setData(value);
    }
    public T getData() { return getData(DEFAUT_TIMER); }
    public T getData(String chronName) {
        return (T)getOrCreateTimer(chronName).getData();
    }


}

class TimerPerf <T>{

    private long _start=0;
    private long _pausedTime=0;
    private long _pauseStart = 0;
    private T _data = null;

    public void start() {
        if (isPaused()) {
          _pausedTime+=System.nanoTime() - _pauseStart;
          _pauseStart=0;
        }
        else
            _start=System.nanoTime();
    }

    public void pause() {
        if (isPaused()) { return; }
        _pauseStart=System.nanoTime();
    }


    public boolean isStarted() {
        return _start != 0;
    }

    public boolean isPaused() {
        return _pauseStart != 0;
    }

    public long getElapsedNanos() {
        if (!isStarted()) return 0;
        long res = (System.nanoTime() - _start) -_pausedTime;

        if (  ! isPaused() ) return res;
        else return res - (System.nanoTime() - _pauseStart);
    }

    public long getElapsedMillis() {
        return getElapsedNanos()/1000000;
    }

    public String getFormatedTime() {
        long elapsed= getElapsedNanos();
        long elapsedMillis = elapsed/1000000;
        if (elapsedMillis< 100) return elapsedMillis +"."+elapsed%1000000+ " ms";
        return elapsedMillis/1000 + "." +  elapsedMillis%1000 + " s";
    }

    public T getData() {
        return _data;
    }
    public void setData(T data) {
        _data=data;
    }




}

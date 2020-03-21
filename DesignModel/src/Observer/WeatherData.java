package Observer;

import java.util.ArrayList;

/**
 * 类是核心，被观察者
 * 1.包含最新天气
 * 2.含有观察者集合，使用ArrayList管理
 * 3.当数据有更新时，就主动的调用ArrayList，通知所有的（接入方）就看到最新的消息
 * @author goodtime
 * @create 2020-03-10 3:15 下午
 */
public class WeatherData implements Subject {
    private String temperature;
    private String date;

    //观察者集合
    private ArrayList<Observer> observers;

    public WeatherData(String temperature,String date){//初始化被观察者
        this.temperature = temperature;
        this.date = date;
        observers = new ArrayList<Observer>();//构造器中初始化观察者集合
    }


    public String getDate() {
        return this.date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void dataChange(String temperature,String date) {//数据一更新，就通知观察者
        this.temperature = temperature;
        this.date = date;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer o) {//注册被观察者
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this.temperature,this.date);
        }
    }
}

package ru.job4j.ood.lsp;

public class CountingRoom {

    protected int normHours;
    protected int payPerHour;
    public CountingRoom(int normHours, int payPerHour) {
        this.normHours = normHours;
        this.payPerHour = payPerHour;
    }
    public int pay(WorkDays workDays) {
        int factHours = 0;
        for (Integer hoursPerDay : workDays) {
            factHours += hoursPerDay;
        }
        if (factHours < normHours) {
            throw new IllegalArgumentException("Worker didn't work enough!");
        }
        return factHours * payPerHour;
    }

}

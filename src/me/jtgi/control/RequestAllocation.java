package me.jtgi.control;

import me.jtgi.control.Elevator;
import me.jtgi.control.ElevatorRequest;

/**
 * Created by jtgi on 7/26/15.
 */
public class RequestAllocation {
    private final int time;
    private final Elevator elevator;
    private final ElevatorRequest req;

    public RequestAllocation(int time, Elevator elevator, ElevatorRequest req) {
        this.time = time;
        this.elevator = elevator;
        this.req = req;
    }

    public String toString() {
        return String.format("[elevator: %s, req: %s]", elevator, req);
    }
}

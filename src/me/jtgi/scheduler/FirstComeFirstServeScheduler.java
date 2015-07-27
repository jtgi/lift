package me.jtgi.scheduler;

import me.jtgi.control.Elevator;
import me.jtgi.control.ElevatorRequest;

import java.util.*;

/**
 * Created by jtgi on 7/26/15.
 */
public class FirstComeFirstServeScheduler extends ElevatorScheduler {
    private int next = 0;
    private Queue<ElevatorRequest> waiting;

    public FirstComeFirstServeScheduler() {
        super();
        this.waiting = new LinkedList<>();
    }

    @Override
    public void submit(ElevatorRequest request) {
        waiting.add(request);
    }

    @Override
    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    /**
     * Just blindly schedules waiting on to each
     * elevator.
     */
    @Override
    public void schedule() {
        while (!waiting.isEmpty()) {
            ElevatorRequest req = waiting.poll();
            Elevator elevator = elevators.get(next);
            elevator.addRequest(req);
            logAlloc(elevator, req);

            next = (next + 1) % elevators.size();
        }
    }

    public boolean hasPendingRequests() {
        return !waiting.isEmpty();
    }
}


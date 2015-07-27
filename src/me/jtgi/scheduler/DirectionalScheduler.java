package me.jtgi.scheduler;

import me.jtgi.control.Elevator;
import me.jtgi.control.ElevatorRequest;

import java.util.*;

/**
 * Algorithm:
 * - Partition on request direction.
 * - Sort `up` requests by start floor
 * - Sort `down` requests send floor for down
 * - Coalesce ranges
 * - Schedule
 */
public class DirectionalScheduler extends ElevatorScheduler {

    private Queue<ElevatorRequest> up;
    private Queue<ElevatorRequest> down;

    public DirectionalScheduler() {
        super();
        up = new PriorityQueue<>(createUpComparator());
        down = new PriorityQueue<>(createDownComparator());
    }

    @Override
    public void submit(ElevatorRequest request) {
        if(request.isUp()) {
            up.add(request);
        } else {
            down.add(request);
        }
    }

    @Override
    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    @Override
    public void schedule() {
    }

    public boolean hasPendingRequests() {
        return false;
    }

    private Comparator<ElevatorRequest> createUpComparator() {
        return (o1, o2) -> {
            if (o1.getStartFloor() < o2.getStartFloor()) {
                return -1;
            } else if (o1.getStartFloor() > o2.getStartFloor()) {
                return 1;
            } else {
                return 0;
            }
        };
    }

    private Comparator<ElevatorRequest> createDownComparator() {
        return (o1, o2) -> {
            if (o1.getStartFloor() < o2.getStartFloor()) {
                return -1;
            } else if (o1.getStartFloor() > o2.getStartFloor()) {
                return 1;
            } else {
                return 0;
            }
        };
    }
}


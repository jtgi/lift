package me.jtgi.scheduler;

import me.jtgi.control.Elevator;
import me.jtgi.control.ElevatorControlSystem;
import me.jtgi.control.ElevatorRequest;
import me.jtgi.control.RequestAllocation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ElevatorScheduler {
  List<Elevator> elevators;
  List<RequestAllocation> log;

  public ElevatorScheduler() {
    this.elevators = new ArrayList<>();
    this.log = new LinkedList<>();
  }

  public abstract void submit(ElevatorRequest request);
  public abstract void addElevator(Elevator elevator);
  public abstract void schedule();
  public abstract boolean hasPendingRequests();

  public void logAlloc(Elevator elevator, ElevatorRequest req) {
    log.add(new RequestAllocation(ElevatorControlSystem.time, elevator, req));
  }

  public List<RequestAllocation> dump() {
    return this.log;
  }

}

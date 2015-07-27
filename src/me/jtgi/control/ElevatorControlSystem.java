package me.jtgi.control;

import me.jtgi.scheduler.ElevatorScheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. system gives elevators to the scheduler.
 * 2. system submits requests to the scheduler.
 * 3. scheduler assigns requests to elevators.
 */
public class ElevatorControlSystem {
  public static int time = 0;

  private ElevatorScheduler scheduler;
  private List<Elevator> elevators;
  private Queue<Elevator> freeElevators;

  public ElevatorControlSystem(int numElevators, ElevatorScheduler scheduler) {
    this.scheduler = scheduler;
    this.elevators = new ArrayList<>();
    this.freeElevators = new LinkedList<>();

    for(int i = 0; i < numElevators; i++) {
      this.elevators.add(new Elevator());
    }

    freeElevators.addAll(elevators);
    offerResources();
  }

  public void pickup(ElevatorRequest req) {
    scheduler.submit(req);
  }

  public void simulate() {
    while(scheduler.hasPendingRequests() || isServicingRequests()) {
      step();
    }
  }

  public boolean isServicingRequests() {
    for (Elevator e : elevators) {
      if (e.hasRequests()) {
        return true;
      }
    }

    return false;
  }

  public void step() {
    offerResources();
    scheduler.schedule();
    elevators.forEach(Elevator::move);
    time++;
  }

  public List<RequestAllocation> history() {
    return scheduler.dump();
  }

  private void offerResources() {
    while(!freeElevators.isEmpty()) {
      scheduler.addElevator(freeElevators.poll());
    }
  }

}

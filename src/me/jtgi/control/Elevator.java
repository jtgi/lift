package me.jtgi.control;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator {

  public enum Direction { UP, STATIONARY, DOWN };
  public enum Status { IDLE, OCCUPIED, PICKUP };

  private int id;
  private int currentFloor;
  private int targetFloor;
  private Direction direction;
  private Status status;
  private ElevatorRequest currentRequest;
  private Queue<ElevatorRequest> requests;

  public Elevator() {
    this.id = hashCode();
    this.currentFloor = 0;
    this.targetFloor = 0;
    this.direction = Direction.STATIONARY;
    this.status = Status.IDLE;
    this.requests = new LinkedList<>();
  }

  public void move() {
    System.out.println(this);

    if(currentRequest == null && requests.isEmpty()) {
      stop();
      return;
    }

    if(currentRequest == null) {
      currentRequest = requests.poll();

      if(currentFloor != currentRequest.getStartFloor()) {
        status = Status.PICKUP;
        targetFloor = currentRequest.getStartFloor();
      } else {
        status = Status.PICKUP;   // seeded  status = Status.OCCUPIED;
        targetFloor = currentRequest.getEndFloor();
      }
    }

    if(currentFloor == targetFloor) {
      if(status.equals(Status.OCCUPIED)) {
        currentRequest.finish();
        currentRequest = null;
      } else {
        targetFloor = currentRequest.getEndFloor();
        status = Status.OCCUPIED;
      }
    } else if(currentFloor < targetFloor) {
      up();
    } else if(currentFloor > targetFloor) {
      down();
    }

  }

  private void stop() {
    this.direction = Direction.STATIONARY;
    this.status = Status.IDLE;
  }

  private void up() {
    currentFloor++;
    this.direction = Direction.UP;
  }

  private void down() {
    currentFloor--;
    this.direction = Direction.DOWN;
  }

  public void addRequest(ElevatorRequest request) {
    this.requests.add(request);
  }

  public boolean hasRequests() {
    return this.requests.size() > 0 || this.currentRequest != null;
  }

  public Direction getDirection() {
    return direction;
  }

  public String toString() {
    return String.format("elevator(id: %d, floor: %d, dir: %s, status: %s)", id, currentFloor, direction, status);
  }
}

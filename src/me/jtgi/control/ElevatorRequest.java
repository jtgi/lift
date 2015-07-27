package me.jtgi.control;

public class ElevatorRequest {

    public enum RequestStatus { WAITING, COMPLETE };
    private final int endFloor;
    private final int startFloor;
    private final int id;
    private RequestStatus status;

    public ElevatorRequest(int startFloor, int endFloor) {
        this.id = hashCode();
        this.startFloor = startFloor;
        this.endFloor = endFloor;
        this.status = RequestStatus.WAITING;
    }

    public RequestStatus getStatus() {
        return this.status;
    }

    public void finish() {
        this.status = RequestStatus.COMPLETE;
    }

    public int getEndFloor() {
        return endFloor;
    }

    public int getStartFloor() {
        return startFloor;
    }

    public String toString() {
        return String.format("[%d: %d -> %d]", id, startFloor, endFloor);
    }

    public boolean isUp() {
        return startFloor < endFloor;
    }

    public boolean isDown() {
        return !isUp();
    }
}

## Lift

A rudimentary elevator scheduling simulation.

### Overview

This library is designed to support the allocation of
elevator requests to a set of available elevators.

### Design

The design is supposed to separate into 2 main pieces:

1. Book keeping of available elevators (`ElevatorControlSystem`)
2. Scheduling of an elevator request to an available elevator (`ElevatorScheduler`)

**Elevator Control System**
- Offers idle elevators to the scheduler for allocating requests.
- Manages the simulation moving each elevator towards its destination.
- Accepts and submits `ElevatorRequests` to the scheduler.

**Elevator**
- Maintains queue of `ElevatorRequests` for its own elevator.
- Moves closer to its destination in FIFO order.

**Elevator Request**
- Requests `(startFloor, endFloor)` that are submitted to the scheduler for allocation to an elevator.

**Elevator Scheduler**
- Provides a generic interface for allocating `ElevatorRequests` to available Elevators.

### How to run

1. Install Java 8
2. `java -jar lift.jar`
3. Lift will listen over `stdin` for the following args:

```
<num_elevators> <num_requests> <scheduler_class_name>
<start_floor> <end_floor> (num_requests times)
```

**Only supported scheduler**: `FirstComeFirstServeScheduler`

**Sample Input**
```
2 3 FirstComeFirstServeScheduler
0 5
5 0
2 4
```

**Sample Output**
```
// Shows what decision each elevator made at each step
elevator(id: 1878246837, floor: 0, dir: STATIONARY, status: IDLE)
elevator(id: 789451787, floor: 0, dir: STATIONARY, status: IDLE)
elevator(id: 1878246837, floor: 1, dir: UP, status: OCCUPIED)
elevator(id: 789451787, floor: 1, dir: UP, status: PICKUP)
elevator(id: 1878246837, floor: 2, dir: UP, status: OCCUPIED)
elevator(id: 789451787, floor: 2, dir: UP, status: PICKUP)
...

Allocation History =======
// Shows what rides where schedules to what elevators
[elevator: elevator(id: 1878246837, floor: 3, dir: STATIONARY, status: IDLE), req: [1625635731: 0 -> 3]]
[elevator: elevator(id: 789451787, floor: 0, dir: DOWN, status: OCCUPIED), req: [644117698: 2 -> 0]]
```

### Shortcomings
- Spent too much time on design; didn't get to do any of the fun scheduling algorithms :(
- Not as clear separation between elevator movement and scheduling as I would like.
  - Right now schedule must be given carefully to elevator and trusted that it follows the way you'd like.
- Need a better approach to dealing with the simulation. How to test/run a real workload. Right now all requests
are frontloaded, scheduler basically gets farthest in the future look at the world.
- Didn't get to add metrics for utilization, avg. wait, avg completion, requests completed per step

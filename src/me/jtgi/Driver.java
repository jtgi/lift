package me.jtgi;

import me.jtgi.control.ElevatorControlSystem;
import me.jtgi.control.ElevatorRequest;
import me.jtgi.scheduler.ElevatorScheduler;
import me.jtgi.control.RequestAllocation;

import java.util.List;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("Usage:");
        System.out.println("<num_elevators> <num_requests> <scheduler_class_name>");
        System.out.println("<start_floor> <end_floor> (num_requests times)");
        System.out.println();
        System.out.println("Sample:");
        System.out.println("2 3 FirstComeFirstServeScheduler");
        System.out.println("0 5");
        System.out.println("5 0");
        System.out.println("2 4");
        System.out.println();

        Scanner s = new Scanner(System.in);
        int numElevators = s.nextInt();
        int numRequests = s.nextInt();
        String schedulerClass = s.next(); s.nextLine();
        ElevatorRequest[] requests = new ElevatorRequest[numRequests];

        for(int i = 0; i < numRequests; i++) {
            int startFloor = s.nextInt();
            int endFloor = s.nextInt();
            requests[i] = new ElevatorRequest(startFloor, endFloor);
        }

        String schedulerPackageName = String.format("me.jtgi.scheduler.%s", schedulerClass);
        ElevatorScheduler scheduler = (ElevatorScheduler) Class.forName(schedulerPackageName).newInstance();
        ElevatorControlSystem system = new ElevatorControlSystem(numElevators, scheduler);

        for(ElevatorRequest req : requests) {
            system.pickup(req);
        }

        system.simulate();

        System.out.println("\nAllocation History =======");
        List<RequestAllocation> history = system.history();
        for(RequestAllocation alloc : history) {
            System.out.println(alloc);
        }
    }
}

/*
*   Christie Ruales technical assesment for Red Ventures (2020)
*   
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Robot {

    private String name;
    private String type;
    private static ArrayList<String> robotTypes =  new ArrayList<String>(Arrays.asList(
        "unipedal", "bipedal", "quadrupedal", "arachnid", "radial", "aeronautical"));

    // taskNum and taskTime associate each task description and eta with a number for easy indexing
    private static Map<Integer, String> taskNum = new HashMap<Integer, String>();
    private static Map<Integer, Integer> taskTime = new HashMap<Integer, Integer>();

    private final static Timer timer = new Timer("Timer");

    // robotTasksCompleted keeps track of which tasks each robot has succesfully completed 
    private static Map<String, ArrayList<String>> robotTasksCompleted = new HashMap<String, ArrayList<String>>();

    public Robot(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /*  
    *   Creates the taskNum and taskTime map with tasks outlined in README.
    */
    public void initializeTasks() {
        taskNum.put(1, "do the dishes");
        taskTime.put(1, 1000);

        taskNum.put(2, "sweep the house");
        taskTime.put(2, 3000);

        taskNum.put(3, "do the laundry");
        taskTime.put(3, 10000);

        taskNum.put(4, "take out the recycling");
        taskTime.put(4, 4000);

        taskNum.put(5, "make a sammich");
        taskTime.put(5, 7000);

        taskNum.put(6, "mow the lawn");
        taskTime.put(6, 2000);

        taskNum.put(7, "rake the leaves");
        taskTime.put(7, 18000);

        taskNum.put(8, "give the dog a bath");
        taskTime.put(8, 14500);

        taskNum.put(9, "bake some cookies");
        taskTime.put(9, 8000);

        taskNum.put(10, "wash the car");
        taskTime.put(10, 20000);
    }

    /*  
    *   Generates a random number which is then used to assign a robot five random tasks. 
    *   Upon completion of the task (using it's eta) it is then removed from the list of tasks left to do. 
    *   Completed tasks are also stored in the robotTasksCompleted map to print out before finishing the program.
    */
    public void doTask() {
        System.out.println("Completing " + name + "'s tasks. Please wait a moment before continuing.");
        if (taskTime.isEmpty()) {
            initializeTasks();
        }
        ArrayList<String> tasks = new ArrayList<String>();
        
        int completedTasks = 0;
        while (completedTasks < 5) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);

            if (taskTime.containsKey(randomNum)) {
                int delayTime = taskTime.get(randomNum);
                taskTime.remove(randomNum);
                TimerTask task = new TimerTask() {
                    public void run() {
                        tasks.add(taskNum.get(randomNum));
                        taskNum.remove(randomNum);
                        cancel();
                    }
                };
                timer.schedule(task, delayTime);
                ++completedTasks;
            }

        }
        robotTasksCompleted.put(name, tasks);
    }

    /*
    *   Main driver of the program.
    *   Sets up prompts for user input with some basic error handling.
    *   Creates robot(s) and executes random tasks then closes Timer thread and prints all the tasks finished by each robot.
    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many robots would you like to create?");
        int numRobots = Integer.parseInt(scanner.nextLine());

        int robotIndex = 1;
        while (numRobots > 0) {
            System.out.print("Enter robot " + robotIndex + "'s name and type: ");
            String robotInfo = scanner.nextLine();
            String[] infoSplit = robotInfo.split(",");

            if (!robotTypes.contains(infoSplit[0].toLowerCase().replaceAll(" ", ""))
                    && !robotTypes.contains(infoSplit[1].toLowerCase().replaceAll(" ", ""))) {
                System.out.println("Invalid Robot Type entered. Please enter a valid robot type.");
            } else {
                String nameInput = "";
                String typeInput = "";
                if (robotTypes.contains(infoSplit[0].toLowerCase().replaceAll(" ", ""))) {
                    nameInput = infoSplit[1];
                    typeInput = infoSplit[0];
                } else if (robotTypes.contains(infoSplit[1].toLowerCase().replaceAll(" ", ""))) {
                    nameInput = infoSplit[0];
                    typeInput = infoSplit[1];
                }

                Robot robot = new Robot(nameInput, typeInput);
                robot.doTask();

                ++robotIndex;
                --numRobots;
            }

        }
        System.out.println("Would you like to terminate the program? (Enter yes or no)");
        String exit = scanner.nextLine();
        if (exit.toLowerCase().equals("yes")) {
            for (String robot : robotTasksCompleted.keySet()) {
                String key = robot;
                ArrayList<String> value = new ArrayList<String>();
                value = robotTasksCompleted.get(robot);
                System.out.println(key + " has completed the following tasks: " + value);
            }
            timer.cancel();
            
        }
        scanner.close();
    }
}
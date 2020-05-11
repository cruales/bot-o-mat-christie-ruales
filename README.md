# BOT-O-MAT
Use any language to complete this challenge. The implementation is up to you: it can be a command-line application or have a graphical interface.

Your application should collect a name and robot type from the types we list below. For each, it should create a Robot of the type the user chooses, e.g. Larry, Bipedal. 

Given the list of tasks below, your application should then assign the Robot a set of five tasks, all of which complete after a duration that we show in milliseconds. 



- Collect a name and robot type from user.
- Instantiate a Robot of the type provided by the user with the name provided by the user
  - for example: Bipedal, Larry
- Set up methods on Robot to complete tasks from the provided list

## Robot
Robot completes tasks and removes them from the list when they are done (i.e. enough time has passed since starting the task).

## Tasks
Tasks have a description and an estimated time to complete.

```
[
  {
    description: 'do the dishes',
    eta: 1000,
  },{
    description: 'sweep the house',
    eta: 3000,
  },{
    description: 'do the laundry',
    eta: 10000,
  },{
    description: 'take out the recycling',
    eta: 4000,
  },{
    description: 'make a sammich',
    eta: 7000,
  },{
    description: 'mow the lawn',
    eta: 20000,
  },{
    description: 'rake the leaves',
    eta: 18000,
  },{
    description: 'give the dog a bath',
    eta: 14500,
  },{
    description: 'bake some cookies',
    eta: 8000,
  },{
    description: 'wash the car',
    eta: 20000,
  },
]
```

## Types
```
{ 
  UNIPEDAL: 'Unipedal',
  BIPEDAL: 'Bipedal',
  QUADRUPEDAL: 'Quadrupedal',
  ARACHNID: 'Arachnid',
  RADIAL: 'Radial',
  AERONAUTICAL: 'Aeronautical'
}
```
## How to run the program
I used Java to create the command-line application so simply enter the following commands to compile and run the program:
```
javac Robot.java
java Robot
```
Once the program is run it will first prompt the user with how many robots they would like to create. The user should then enter an integer number indicating the number of robots. Using the number the user inputted, the program will prompt the user to enter a name and robot type for each robot. Between each new robot creation the program tells the user to wait a moment before entering the next robot's information. This ensures enough time has passed for the previous robot to complete all of their assigned tasks and remove it from the list of tasks. Finally, after the last robot has been created the program asks the user if they would like to terminate the program. In order to acheive the delay from when a robot is assigned a task to when they actually complete it I used the Timer class built into Java. This meant there is a background thread that is left open to run different tasks. I wanted to leave the Timer thread open so that each robot has enough time to complete their tasks so only after the user indicates they would like the end the program do I actually close the thread. According to java docs for the Timer class, the background thread eventually closes so it has graceful termination but only after an arbitrarily long time so this prompt just speeds that up therfore providing a better experience for the user.

## Assumptions and Limitations
I made a number of assumptions when creating this application. They are as follows:
- The eta on each task is in milliseconds
- When all tasks are completed, the task list reinitilaizes for the following robots
- The input for the number of robots the user wishes to create will always be a whole number in the form of 1, 2, etc.
- The input for robot information will always be in the form 'name, type' or 'type, name' (will always include a comma separating the two fields)

Furthermore, there are limitations to the program. Making assumptions about user input in a command-line application leaves a lot of room for error. Ideally, a graphical user interface would solve these problems by restricting what the user can actually enter. A GUI would also provide a nice way to display the information which would allow for a more pleasant user interface. In addition, while the program prompts the user to wait a moment before entering the next line of input, there is nothing in the code to actually enforce that. 

## Features to add once the core functionality is complete
Be creative and have fun! Use this list or create your own features.
- Allow users to create multiple robots at one time
- Create a leaderboard for tasks completed by each Robot
- Create tasks specific for each robot type, this could work in conjunction with the leaderboard. For e.g. robots that are assigned tasks that their type can’t perform won’t get “credit” for finishing the task.
- Add persistance for tasks, bots and leaderboard stats


## Authors
- Scott Hoffman <https://github.com/scottshane>
- Olivia Osby <https://github.com/oosby>
- Additions made by: Christie Ruales <https://github.com/cruales>

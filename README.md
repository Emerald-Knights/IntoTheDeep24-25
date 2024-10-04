# Fancy Code Base Documentation

![alt text](https://images-ext-1.discordapp.net/external/ZDmSCC4ph744nzKZghkdjBnECEv0mcm1SGylW7qg3mo/https/i.postimg.cc/KxqsdcJt/EKCode-Structure-Flow-Chart.png?format=webp&quality=lossless&width=1483&height=834)
## Folders
1. ### EK10582
      1. #### auton
            Auton has two folders, action and modes.
            
            modes contains every auton file that the robot can run; ExAuton is a file which shows how auton should be formatted.
            
            action contains every action: action is an abstract class that needs to have children.


            **Organization tip:** Make a new folder for every subsystem like a 'claw' folder for all the claw actions.  


      2. #### Subsystem
            subsystem is a folder that contains all of the robot's subsystem, plus the robot class.  

            _!!The robot is not a subsystem!!_  

            Each robot subsystem extends an abstract Subsystem class, a parent class that helps organize
            our codebase. A subsystem is any mini-system on the robot that performs a function.
            Examples of subsystems may include (but are not limited to):  

            * Intake
            * Drive
            * Lift
            * Transfer  

            
            Each of these subsystems is built on 4 fundamental methods:  
            * init: 
                  This runs once when the robot is initialized, and never again.  

            * update: 
                  This is repeatedly run every code loop until the program is stopped.  

            * stop: 
                  This code is run once, when the code is stopped.  

            * printToTelemetry: 
                  This code is similarly run every update loop, but serves to print to telemetry.  

      
            In each subsystem, you can initialize variables that can be accessed from Teleop and Auton.
            You can check Drive.java for an example. You will be setting the motor powers and servo 
            positions from each respective subsystem. The purpose of using subsystems and actions is 
            to make our teleop code readable and concise!

            To utilize a subsystem, you must create a class for the subsystem.
            Our naming convention is making the name of the class uppercase (i.e. Intake, Drive)
            However, to access the subsystem and utilize the information (methods) inside, we must create instances of the class.
            When we name these instances, name them the same as their respective classes except lowercase (i.e. intake, drive).
         
            
      4. ### teleop  
            Teleop contains 2 main parts: The actual teleop files and the driver station.
            The actual teleop files are intended to be concise. Therefore, most if not all of the
            statements will have a pretty self-explanatory function.
            For instance, many of the statements follow this format:

            robot.[subsystem].[variable] = driverStation.[function/button];

            The driverStation portion of it creates a class that serves as an access point for all values
            from the driver station. It includes joystick constants like a deadzone and min/max.
            Additionally, the driver station includes methods that can filter the joystick (and trigger) values
            for smoother driving following a quadratic curve rather than a linear one.

            It also includes buttons for functions. The frame change late variable system used to cloud up our
            teleop loop, but by placing these gamepad functions inside one method under the driver station, 
            we are able to keep our teleop short and concise.


      5. ### test
         This folder is for testing singular motors, servos, or other hardware. For the sake of simplicity, we do not need to make a subsystem for everything.

      6. ### Robot
         There are 2 main parts to Robot: It'll go through all of the subsystems above in the list and update each of them and it names the hardware map
         It'll name the different hardware on the robot like the Servos and Claws so that the other classes can use them
         For the hardware map most of the statments will follow this format:

         public Servo tServo;

         and

         leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");

         and

         leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE); or leftFront.setDirection(DCMotorSimple.Direction.REVERSE);

         The subsystems uses a for each loop to go through multiple subsystems so the robot can simultaneously update its subsystems depending on inputs
         The point of the for each loop is so that the subsystems each get updated and then it keeps on going back in a loop until its told to stop
         It also sets the telemetry for the phone and telemetry is the information that we can see that the robot when checking the phone.

      7. ### EKLinear
         EKLinear extends LinearOpMode
         EkLinears main goal is for setting up the instances (shortcut to refer to instance of Robot Class and telemtery for the robot during the match)
         LinearOpMode makes code run sequentially for the robot
         The waitForStart method sets the telemetry up for us to see on the phone
         It sets the match timer, driver station(controllers), and the dashboard(allTelemetry)
         While the match hasn't started and if the stop isn't requested the telemtery keeps updating

      8. ### AutonBase
         AutonBase extends EKLinear
         AutonBase is the base of the code for Autonomous mode
         Has a method for waitForStart and intializes the telemetry and the code is the same as EKLinear
   


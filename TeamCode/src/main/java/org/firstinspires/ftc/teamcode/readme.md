# What we are working on right now
- Adding Road Runner to auton
- Writing trajectories
- Adding markers
- testing and tuning RR



# Fancy Code Base Documentation

## Folders
1. ### EK10582
    1. #### auton
       Auton has two folders, action and modes.

       modes contains every auton file that the robot can run; ExAuton is a file which shows how
       auton should be formatted.

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
            
      3. ### teleop  
            Teleop contains 2 main parts: The actual teleop files and the driver station.
            The actual teleop files are intended to be concise. Therefore, most if not all of the
            statements will have a pretty self-explanatory function.
            For instance, many of the statements follow this format:

            robot.[subsystem].[variable] = driverStation.[function/button];
  
            OR
  
            if(driverStation.[function/button]){
               robot.[subsystem].[method];
            }

            The driverStation portion of it creates a class that serves as an access point for all values
            from the driver station. It includes joystick constants like a deadzone and min/max.
            Additionally, the driver station includes methods that can filter the joystick (and trigger) values
            for smoother driving following a quadratic curve rather than a linear one.

            It also includes buttons for functions. The frame change late variable system used to cloud up our
            teleop loop, but by placing these gamepad functions inside one method under the driver station, 
            we are able to keep our teleop short and concise.


      5. ### test  
      6. Outside

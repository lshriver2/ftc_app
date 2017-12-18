package encoder_teleop;

        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.ElapsedTime;
        import com.qualcomm.robotcore.util.RobotLog;

        /**
 + * This is NOT an opmode. tyjtyjdnyfgh
 + *
 + * This class can be used to define all the specific hardware for a single robot.
 + * In this case that robot is a Pushbot.
 + * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 + *
 + * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left motor"
 * Motor channel:  Right drive motor:        "right motor"
  * Motor channel:  Manipulator drive motor:  "arm motor"
  * Servo channel:  Servo to open left claw:  "left claw"
  * Servo channel:  Servo to open right claw: "right claw"
  */
        public class encoder_teleop
 {
             /* Public OpMode members. */
             public DcMotor  leftMotor   = null;
            public DcMotor  rightMotor  = null;
            public DcMotor  leftBackMotor    = null;
            public DcMotor  rightBackMotor    = null;

     /**
            public Servo    leftClaw    = null;
            public Servo    rightClaw   = null;

            public static final double MID_SERVO       =  0.5 ;
            public static final double ARM_UP_POWER    =  0.45 ;
            public static final double ARM_DOWN_POWER  = -0.45 ;
      */
            /* local OpMode members. */
            HardwareMap hwMap           =  null;
            private ElapsedTime period  = new ElapsedTime();

            /* Constructor */
            public encoder_teleop(){

            }

            /* Initialize standard Hardware interfaces */
            public void init(HardwareMap ahwMap) {
                // Save reference to Hardware map
                hwMap = ahwMap;


                // Define and Initialize Motors
                leftMotor   = hwMap.dcMotor.get("left_drive");
                rightMotor  = hwMap.dcMotor.get("right_drive");
                leftBackMotor   = hwMap.dcMotor.get("left_back_drive");
                rightBackMotor  = hwMap.dcMotor.get("right_back_drive");

                leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
                rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
                leftBackMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
                rightBackMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

                // Set all motors to zero power
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                leftBackMotor.setPower(0);
                rightBackMotor.setPower(0);

                // Set all motors to run without encoders.
                // May want to use RUN_USING_ENCODERS if encoders are installed.
                leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                leftBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                rightBackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                 /**
                  Define and initialize ALL installed servos.
                leftClaw = hwMap.servo.get("left claw");
                rightClaw = hwMap.servo.get("right claw");
                leftClaw.setPosition(MID_SERVO);
                rightClaw.setPosition(MID_SERVO);
                  */
            }

            /**
 +     *
 +     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
 +     * periodic tick.  This is used to compensate for varying processing times for each cycle.
 +     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
 +     *
 +     * @param periodMs  Length of wait cycle in mSec.
 +     * @throws InterruptedException
 +     */
            public void waitForTick(long periodMs) throws InterruptedException {

                long  remaining = periodMs - (long)period.milliseconds();

                // sleep for the remaining portion of the regular cycle period.
                if (remaining > 0)
                    Thread.sleep(remaining);

                // Reset the cycle clock for the next pass.
                period.reset();
            }
        }

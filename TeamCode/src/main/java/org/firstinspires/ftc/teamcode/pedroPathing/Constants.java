package org.firstinspires.ftc.teamcode.pedroPathing;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Configurable
public class Constants {
    public static MecanumConstants driveConstants = new MecanumConstants()
            // Type your motor names here:
            .leftFrontMotorName("")
            .leftRearMotorName("")
            .rightFrontMotorName("")
            .rightRearMotorName("")

            // Motor directions, need adjustments for your robot.
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)

            // xVelocity and yVelocity need tuning too.
            .xVelocity(81)
            .yVelocity(65);

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(10) // This is the mass of your robot, and it also doesn't matter much, so leave it at 10
            .forwardZeroPowerAcceleration(-35) // Tune this
            .lateralZeroPowerAcceleration(-78) // Tune this

            .headingPIDFCoefficients(new PIDFCoefficients(2, 0, 0.1, 0.002))
            .automaticHoldEnd(true);

    public static PinpointConstants localizerConstants = new PinpointConstants()

            .forwardPodY(0) // Tune this using the Offsets Tuner
            .strafePodX(0)  // Tune this using the Offsets Tuner
            .hardwareMapName("pinpoint") // This name must match in your config

            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .distanceUnit(DistanceUnit.INCH)

            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED) // If x is getting decreased when you move the robot forward, change this
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED); // If y is getting decreased when you move the robot left, change this

    public static PathConstraints pathConstraints = new PathConstraints(
            0.995, 1, 1, 1, 100, 1, 10, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
    }
}

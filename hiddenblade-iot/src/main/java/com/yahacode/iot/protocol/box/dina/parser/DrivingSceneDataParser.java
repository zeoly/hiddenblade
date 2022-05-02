package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.box.dina.data.DrivingSceneData;
import com.yahacode.iot.protocol.box.dina.data.sub.DrivingScene;

public class DrivingSceneDataParser extends AbstractDinaParser<DrivingSceneData> {

    @Override
    public DrivingSceneData parse(byte[] data) {
        DrivingSceneData drivingSceneData = new DrivingSceneData();
        drivingSceneData.setTimestamp(getTimestamp(data));

        drivingSceneData.setPreInterval((data[6] >> 4) & 0x0F);
        drivingSceneData.setMidInterval(data[6] & 0x0F);
        drivingSceneData.setPreNumber((data[7] >> 4) & 0x0F);
        drivingSceneData.setMidNumber(data[7] & 0x0F);

        final int gpsUnitLength = 14;
        final int accUnitLength = 6;
        int totalNumber = drivingSceneData.getPreNumber() * 2 + drivingSceneData.getMidNumber();
        int gpsUnitNumber = totalNumber;
        int accUnitNumber = drivingSceneData.getMidNumber();
        if (gpsUnitNumber * gpsUnitLength + totalNumber * accUnitLength + 8 + 1 == data.length) {
            accUnitNumber = totalNumber;
        }

        int index = 8;
        int accIndex = index + gpsUnitLength * gpsUnitNumber;
        for (int i = 0; i < totalNumber; i++) {
            DrivingScene scene = new DrivingScene();
            double[] coordinates = toCoordinates(data, index);
            scene.setLongitude(coordinates[0]);
            scene.setLatitude(coordinates[1]);
            index += 8;
            scene.setSpeed(MathUtil.precision(0.1 * toInt(data, index, 2), 1));
            index += 2;
            scene.setDirection(toInt(data, index, 2) / 10);
            index += 2;
            scene.setRpm(toInt(data, index, 2));
            index += 2;

            boolean accDataExists = false;
            if (accUnitNumber == totalNumber || i >= drivingSceneData.getPreNumber() && i < drivingSceneData.getPreNumber() + drivingSceneData.getMidNumber()) {
                accDataExists = true;
            }
            if (accDataExists) {
                scene.setAccelerationX(MathUtil.precision(0.1 * toSignedInt(data, accIndex, 2), 1));
                accIndex += 2;
                scene.setAccelerationY(MathUtil.precision(0.1 * toSignedInt(data, accIndex, 2), 1));
                accIndex += 2;
                scene.setAccelerationZ(MathUtil.precision(0.1 * toSignedInt(data, accIndex, 2), 1));
                accIndex += 2;
            }
            drivingSceneData.getScenes().add(scene);
        }
        drivingSceneData.setDuration(toInt(data[accIndex]));
        return drivingSceneData;
    }
}

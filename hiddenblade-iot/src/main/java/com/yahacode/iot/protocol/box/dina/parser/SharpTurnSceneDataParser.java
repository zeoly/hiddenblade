package com.yahacode.iot.protocol.box.dina.parser;

import com.yahacode.hiddenblade.tool.utils.ByteUtil;
import com.yahacode.hiddenblade.tool.utils.MathUtil;
import com.yahacode.iot.protocol.box.dina.data.DrivingSceneData;
import com.yahacode.iot.protocol.box.dina.data.SharpTurnSceneData;
import org.springframework.beans.BeanUtils;

public class SharpTurnSceneDataParser extends AbstractDinaParser<SharpTurnSceneData> {

    private DrivingSceneDataParser drivingSceneDataParser = new DrivingSceneDataParser();

    @Override
    public SharpTurnSceneData parse(byte[] data) {
        SharpTurnSceneData sharpTurnSceneData = new SharpTurnSceneData();
        DrivingSceneData drivingSceneData = drivingSceneDataParser.parse(ByteUtil.subBytes(data, 0, data.length - 4));
        BeanUtils.copyProperties(drivingSceneData, sharpTurnSceneData);
        int index = data.length - 4;
        sharpTurnSceneData.setDirectionRange(MathUtil.precision(0.1 * toInt(data, index, 2), 1));
        index += 2;
        sharpTurnSceneData.setLowSpeed(MathUtil.precision(0.1 * toInt(data, index, 2), 1));
        return sharpTurnSceneData;
    }
}

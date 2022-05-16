package com.yahacode.iot.protocol.box.dina.data;

import com.yahacode.iot.protocol.box.dina.data.sub.EVDoorStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.EVSecurityStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.EVWindowStatus;
import com.yahacode.iot.protocol.box.dina.data.sub.LockStatus;

import java.time.LocalDateTime;

public class EVCanData {

    LocalDateTime timestamp;

    EVSecurityStatus securityStatus;

    EVDoorStatus doorStatus;

    EVWindowStatus windowStatus;

    LockStatus lockStatus;

    Integer motorRpm;

    Integer motorTorque;

    Integer motorTemp;

    Double motorCtrlOutputCurr;

    Double motorCtrlDcVolt;

    Integer motorCtrlTemp;

    String motorCtrlStatus;

    Integer motorCtrlLife;

    Double totalVolt;

    Double totalCurr;

    Double soc;

    Integer maxTempBatSeqId;

    Integer maxTempValue;

    Integer minTempBatSeqId;

    Integer minTempValue;

    Integer batMaxTempDiff;

    Integer maxVoltBatSeqId;

    Double maxVoltValue;

    Integer minVoltBatSeqId;

    Double minVoltValue;

    Double batMaxVoltDiff;

    Double limitDcVolt;

    Double limitDcCurr;

    String motorWorkModeOrder;

    String batWorkModeOrder;

    Integer vcuCtrlLife;

    Double vehicleSpeed;

    Long distanceTotal;

    Double accPedalPosition;

    Double brakePedalPosition;

    String operationStatus;

    String gearStatus;

    Integer motorTargetOutputTorque;

    Integer motorTargetOutputRpm;

    String motorCtrlReqType;

    String amtReqResp;

    Double tireSize;

    Double driveTrainAxleRatio;

    String amtStatus;

    String amtChangeGear;

    Integer amtOutputRpm;

    Integer amtCtrlLife;

    Double amtVehicleSpeed;

    Long amtDistance;

    Integer motorTargetOutputTorqueRpm;

    Integer vcuCtrlLife2;

    Integer maxTempProbeSeqId;

    Integer minTempProbeSeqId;

    Integer insulationResistance;

    String recableEnergyCode;

    Integer recableEnergyNum;

    Integer singleBatteryTotal;

    Integer recableTempProbeNum;

    Integer recableSubFaultTotal;

    Integer recableModuleTotal;

    String dcWorkOrder;

    String carVcuStatus;

    String runMode;

    Double dcOutputVolt;

    Double dcOutputCurr;

    Double dcInputVolt;

    Integer dcTemp;

    Integer dcLife;

    String dcStatus;

    String checkSumVcu1;

    Integer readyLedStatus;

    Long panelMileage;

    Long enduranceMileage;

    Integer chargeDischargeStatus;

    Integer accStatus;

    Double latitude;

    Double longitude;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public EVSecurityStatus getSecurityStatus() {
        return securityStatus;
    }

    public void setSecurityStatus(EVSecurityStatus securityStatus) {
        this.securityStatus = securityStatus;
    }

    public EVDoorStatus getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(EVDoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }

    public EVWindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(EVWindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public LockStatus getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Integer getMotorRpm() {
        return motorRpm;
    }

    public void setMotorRpm(Integer motorRpm) {
        this.motorRpm = motorRpm;
    }

    public Integer getMotorTorque() {
        return motorTorque;
    }

    public void setMotorTorque(Integer motorTorque) {
        this.motorTorque = motorTorque;
    }

    public Integer getMotorTemp() {
        return motorTemp;
    }

    public void setMotorTemp(Integer motorTemp) {
        this.motorTemp = motorTemp;
    }

    public Double getMotorCtrlOutputCurr() {
        return motorCtrlOutputCurr;
    }

    public void setMotorCtrlOutputCurr(Double motorCtrlOutputCurr) {
        this.motorCtrlOutputCurr = motorCtrlOutputCurr;
    }

    public Double getMotorCtrlDcVolt() {
        return motorCtrlDcVolt;
    }

    public void setMotorCtrlDcVolt(Double motorCtrlDcVolt) {
        this.motorCtrlDcVolt = motorCtrlDcVolt;
    }

    public Integer getMotorCtrlTemp() {
        return motorCtrlTemp;
    }

    public void setMotorCtrlTemp(Integer motorCtrlTemp) {
        this.motorCtrlTemp = motorCtrlTemp;
    }

    public String getMotorCtrlStatus() {
        return motorCtrlStatus;
    }

    public void setMotorCtrlStatus(String motorCtrlStatus) {
        this.motorCtrlStatus = motorCtrlStatus;
    }

    public Integer getMotorCtrlLife() {
        return motorCtrlLife;
    }

    public void setMotorCtrlLife(Integer motorCtrlLife) {
        this.motorCtrlLife = motorCtrlLife;
    }

    public Double getTotalVolt() {
        return totalVolt;
    }

    public void setTotalVolt(Double totalVolt) {
        this.totalVolt = totalVolt;
    }

    public Double getTotalCurr() {
        return totalCurr;
    }

    public void setTotalCurr(Double totalCurr) {
        this.totalCurr = totalCurr;
    }

    public Double getSoc() {
        return soc;
    }

    public void setSoc(Double soc) {
        this.soc = soc;
    }

    public Integer getMaxTempBatSeqId() {
        return maxTempBatSeqId;
    }

    public void setMaxTempBatSeqId(Integer maxTempBatSeqId) {
        this.maxTempBatSeqId = maxTempBatSeqId;
    }

    public Integer getMaxTempValue() {
        return maxTempValue;
    }

    public void setMaxTempValue(Integer maxTempValue) {
        this.maxTempValue = maxTempValue;
    }

    public Integer getMinTempBatSeqId() {
        return minTempBatSeqId;
    }

    public void setMinTempBatSeqId(Integer minTempBatSeqId) {
        this.minTempBatSeqId = minTempBatSeqId;
    }

    public Integer getMinTempValue() {
        return minTempValue;
    }

    public void setMinTempValue(Integer minTempValue) {
        this.minTempValue = minTempValue;
    }

    public Integer getBatMaxTempDiff() {
        return batMaxTempDiff;
    }

    public void setBatMaxTempDiff(Integer batMaxTempDiff) {
        this.batMaxTempDiff = batMaxTempDiff;
    }

    public Integer getMaxVoltBatSeqId() {
        return maxVoltBatSeqId;
    }

    public void setMaxVoltBatSeqId(Integer maxVoltBatSeqId) {
        this.maxVoltBatSeqId = maxVoltBatSeqId;
    }

    public Double getMaxVoltValue() {
        return maxVoltValue;
    }

    public void setMaxVoltValue(Double maxVoltValue) {
        this.maxVoltValue = maxVoltValue;
    }

    public Integer getMinVoltBatSeqId() {
        return minVoltBatSeqId;
    }

    public void setMinVoltBatSeqId(Integer minVoltBatSeqId) {
        this.minVoltBatSeqId = minVoltBatSeqId;
    }

    public Double getMinVoltValue() {
        return minVoltValue;
    }

    public void setMinVoltValue(Double minVoltValue) {
        this.minVoltValue = minVoltValue;
    }

    public Double getBatMaxVoltDiff() {
        return batMaxVoltDiff;
    }

    public void setBatMaxVoltDiff(Double batMaxVoltDiff) {
        this.batMaxVoltDiff = batMaxVoltDiff;
    }

    public Double getLimitDcVolt() {
        return limitDcVolt;
    }

    public void setLimitDcVolt(Double limitDcVolt) {
        this.limitDcVolt = limitDcVolt;
    }

    public Double getLimitDcCurr() {
        return limitDcCurr;
    }

    public void setLimitDcCurr(Double limitDcCurr) {
        this.limitDcCurr = limitDcCurr;
    }

    public String getMotorWorkModeOrder() {
        return motorWorkModeOrder;
    }

    public void setMotorWorkModeOrder(String motorWorkModeOrder) {
        this.motorWorkModeOrder = motorWorkModeOrder;
    }

    public String getBatWorkModeOrder() {
        return batWorkModeOrder;
    }

    public void setBatWorkModeOrder(String batWorkModeOrder) {
        this.batWorkModeOrder = batWorkModeOrder;
    }

    public Integer getVcuCtrlLife() {
        return vcuCtrlLife;
    }

    public void setVcuCtrlLife(Integer vcuCtrlLife) {
        this.vcuCtrlLife = vcuCtrlLife;
    }

    public Double getVehicleSpeed() {
        return vehicleSpeed;
    }

    public void setVehicleSpeed(Double vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    public Long getDistanceTotal() {
        return distanceTotal;
    }

    public void setDistanceTotal(Long distanceTotal) {
        this.distanceTotal = distanceTotal;
    }

    public Double getAccPedalPosition() {
        return accPedalPosition;
    }

    public void setAccPedalPosition(Double accPedalPosition) {
        this.accPedalPosition = accPedalPosition;
    }

    public Double getBrakePedalPosition() {
        return brakePedalPosition;
    }

    public void setBrakePedalPosition(Double brakePedalPosition) {
        this.brakePedalPosition = brakePedalPosition;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getGearStatus() {
        return gearStatus;
    }

    public void setGearStatus(String gearStatus) {
        this.gearStatus = gearStatus;
    }

    public Integer getMotorTargetOutputTorque() {
        return motorTargetOutputTorque;
    }

    public void setMotorTargetOutputTorque(Integer motorTargetOutputTorque) {
        this.motorTargetOutputTorque = motorTargetOutputTorque;
    }

    public Integer getMotorTargetOutputRpm() {
        return motorTargetOutputRpm;
    }

    public void setMotorTargetOutputRpm(Integer motorTargetOutputRpm) {
        this.motorTargetOutputRpm = motorTargetOutputRpm;
    }

    public String getMotorCtrlReqType() {
        return motorCtrlReqType;
    }

    public void setMotorCtrlReqType(String motorCtrlReqType) {
        this.motorCtrlReqType = motorCtrlReqType;
    }

    public String getAmtReqResp() {
        return amtReqResp;
    }

    public void setAmtReqResp(String amtReqResp) {
        this.amtReqResp = amtReqResp;
    }

    public Double getTireSize() {
        return tireSize;
    }

    public void setTireSize(Double tireSize) {
        this.tireSize = tireSize;
    }

    public Double getDriveTrainAxleRatio() {
        return driveTrainAxleRatio;
    }

    public void setDriveTrainAxleRatio(Double driveTrainAxleRatio) {
        this.driveTrainAxleRatio = driveTrainAxleRatio;
    }

    public String getAmtStatus() {
        return amtStatus;
    }

    public void setAmtStatus(String amtStatus) {
        this.amtStatus = amtStatus;
    }

    public String getAmtChangeGear() {
        return amtChangeGear;
    }

    public void setAmtChangeGear(String amtChangeGear) {
        this.amtChangeGear = amtChangeGear;
    }

    public Integer getAmtOutputRpm() {
        return amtOutputRpm;
    }

    public void setAmtOutputRpm(Integer amtOutputRpm) {
        this.amtOutputRpm = amtOutputRpm;
    }

    public Integer getAmtCtrlLife() {
        return amtCtrlLife;
    }

    public void setAmtCtrlLife(Integer amtCtrlLife) {
        this.amtCtrlLife = amtCtrlLife;
    }

    public Double getAmtVehicleSpeed() {
        return amtVehicleSpeed;
    }

    public void setAmtVehicleSpeed(Double amtVehicleSpeed) {
        this.amtVehicleSpeed = amtVehicleSpeed;
    }

    public Long getAmtDistance() {
        return amtDistance;
    }

    public void setAmtDistance(Long amtDistance) {
        this.amtDistance = amtDistance;
    }

    public Integer getMotorTargetOutputTorqueRpm() {
        return motorTargetOutputTorqueRpm;
    }

    public void setMotorTargetOutputTorqueRpm(Integer motorTargetOutputTorqueRpm) {
        this.motorTargetOutputTorqueRpm = motorTargetOutputTorqueRpm;
    }

    public Integer getVcuCtrlLife2() {
        return vcuCtrlLife2;
    }

    public void setVcuCtrlLife2(Integer vcuCtrlLife2) {
        this.vcuCtrlLife2 = vcuCtrlLife2;
    }

    public Integer getMaxTempProbeSeqId() {
        return maxTempProbeSeqId;
    }

    public void setMaxTempProbeSeqId(Integer maxTempProbeSeqId) {
        this.maxTempProbeSeqId = maxTempProbeSeqId;
    }

    public Integer getMinTempProbeSeqId() {
        return minTempProbeSeqId;
    }

    public void setMinTempProbeSeqId(Integer minTempProbeSeqId) {
        this.minTempProbeSeqId = minTempProbeSeqId;
    }

    public Integer getInsulationResistance() {
        return insulationResistance;
    }

    public void setInsulationResistance(Integer insulationResistance) {
        this.insulationResistance = insulationResistance;
    }

    public String getRecableEnergyCode() {
        return recableEnergyCode;
    }

    public void setRecableEnergyCode(String recableEnergyCode) {
        this.recableEnergyCode = recableEnergyCode;
    }

    public Integer getRecableEnergyNum() {
        return recableEnergyNum;
    }

    public void setRecableEnergyNum(Integer recableEnergyNum) {
        this.recableEnergyNum = recableEnergyNum;
    }

    public Integer getSingleBatteryTotal() {
        return singleBatteryTotal;
    }

    public void setSingleBatteryTotal(Integer singleBatteryTotal) {
        this.singleBatteryTotal = singleBatteryTotal;
    }

    public Integer getRecableTempProbeNum() {
        return recableTempProbeNum;
    }

    public void setRecableTempProbeNum(Integer recableTempProbeNum) {
        this.recableTempProbeNum = recableTempProbeNum;
    }

    public Integer getRecableSubFaultTotal() {
        return recableSubFaultTotal;
    }

    public void setRecableSubFaultTotal(Integer recableSubFaultTotal) {
        this.recableSubFaultTotal = recableSubFaultTotal;
    }

    public Integer getRecableModuleTotal() {
        return recableModuleTotal;
    }

    public void setRecableModuleTotal(Integer recableModuleTotal) {
        this.recableModuleTotal = recableModuleTotal;
    }

    public String getDcWorkOrder() {
        return dcWorkOrder;
    }

    public void setDcWorkOrder(String dcWorkOrder) {
        this.dcWorkOrder = dcWorkOrder;
    }

    public String getCarVcuStatus() {
        return carVcuStatus;
    }

    public void setCarVcuStatus(String carVcuStatus) {
        this.carVcuStatus = carVcuStatus;
    }

    public String getRunMode() {
        return runMode;
    }

    public void setRunMode(String runMode) {
        this.runMode = runMode;
    }

    public Double getDcOutputVolt() {
        return dcOutputVolt;
    }

    public void setDcOutputVolt(Double dcOutputVolt) {
        this.dcOutputVolt = dcOutputVolt;
    }

    public Double getDcOutputCurr() {
        return dcOutputCurr;
    }

    public void setDcOutputCurr(Double dcOutputCurr) {
        this.dcOutputCurr = dcOutputCurr;
    }

    public Double getDcInputVolt() {
        return dcInputVolt;
    }

    public void setDcInputVolt(Double dcInputVolt) {
        this.dcInputVolt = dcInputVolt;
    }

    public Integer getDcTemp() {
        return dcTemp;
    }

    public void setDcTemp(Integer dcTemp) {
        this.dcTemp = dcTemp;
    }

    public Integer getDcLife() {
        return dcLife;
    }

    public void setDcLife(Integer dcLife) {
        this.dcLife = dcLife;
    }

    public String getDcStatus() {
        return dcStatus;
    }

    public void setDcStatus(String dcStatus) {
        this.dcStatus = dcStatus;
    }

    public String getCheckSumVcu1() {
        return checkSumVcu1;
    }

    public void setCheckSumVcu1(String checkSumVcu1) {
        this.checkSumVcu1 = checkSumVcu1;
    }

    public Integer getReadyLedStatus() {
        return readyLedStatus;
    }

    public void setReadyLedStatus(Integer readyLedStatus) {
        this.readyLedStatus = readyLedStatus;
    }

    public Long getPanelMileage() {
        return panelMileage;
    }

    public void setPanelMileage(Long panelMileage) {
        this.panelMileage = panelMileage;
    }

    public Long getEnduranceMileage() {
        return enduranceMileage;
    }

    public void setEnduranceMileage(Long enduranceMileage) {
        this.enduranceMileage = enduranceMileage;
    }

    public Integer getChargeDischargeStatus() {
        return chargeDischargeStatus;
    }

    public void setChargeDischargeStatus(Integer chargeDischargeStatus) {
        this.chargeDischargeStatus = chargeDischargeStatus;
    }

    public Integer getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(Integer accStatus) {
        this.accStatus = accStatus;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

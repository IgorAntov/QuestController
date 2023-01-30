package org.autoquest.quest;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.connections.adapters.Adapter;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Transition extends Thread {
    private final int scanRate = 300;
    private boolean passed = false;
    private cnd checkCND;
    private MBParameter bypass;
    private MBParameter bypassCFM;
    private MBParameter status;
    private MBParameter bypassVisible;
    private String name;
    private String desc = "";
    private int bypassButtonX = 10;
    private int bypassButtonY = 10;
    private MBParameter enabled;
    private MBParameter enabledConfirm;
    private final Object lock = new Object();

    public Transition(String name) {
        setName(name);
        this.name = name;
        initTransition();
    }

    private void initTransition() {
        MBParameter status = new MBParameter(String.format("TransitStatus%s", name), WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        MBParameter bypass = new MBParameter(String.format("Bypass%s", name), WS_MB_UNIT_SLAVE, true, ParamType.READ, MembershipType.GROUP);
        MBParameter bypassCFM = new MBParameter(String.format("BypassCFM%s", name), WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.GROUP);
        MBParameter bypassVisible = new MBParameter(String.format("BypassVisible%s", name), WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.GROUP);
        setStatusParam(status);
        setBypassParam(bypass);
        setBypassCFM(bypassCFM);
        setBypassVisible(bypassVisible);
        Adapter.setAdapter(bypass, bypassCFM);
        MBParameter transition1Enabled = new MBParameter(String.format("ActEnabled%s", name), WS_MB_UNIT_SLAVE, true, ParamType.READ, MembershipType.GROUP);
        MBParameter transitionEnabledConfirm = new MBParameter(String.format("ActEnabledCFM%s", name), WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.GROUP);
        setEnabled(transition1Enabled);
        StateStore.addParameter(transition1Enabled);
        setEnabledConfirm(transitionEnabledConfirm);
        StateStore.addParameter(transitionEnabledConfirm);
        Adapter.setAdapter(transition1Enabled, transitionEnabledConfirm);
    }

    @Override
    public void run() {
        do {
            try {
                passed = false;
                status.setValue(false);
                if (!bypassVisible.getBoolValue() && enabled.getBoolValue()) {
                    bypassVisible.setValue(true);
                }
                sleep(scanRate);
                if (checkCND.apply() || !bypass.getBoolValue() || !enabled.getBoolValue() || !StepsExecutor.isQuestRunning()) {
                    passed = true;
                    System.out.println("transition done " + getTransitionName());
                    status.setValue(true);
                    bypassVisible.setValue(false);
                    synchronized (lock) {
                        lock.wait();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

    public void condition(cnd<Boolean> cnd) {
        checkCND = cnd;
    }

    public String getTransitionName() {
        return name;
    }

    public MBParameter getBypassCFM() {
        return bypassCFM;
    }

    public MBParameter getBypassVisible() {
        return bypassVisible;
    }

    public void setBypassCFM(MBParameter bypassCFM) {
        this.bypassCFM = bypassCFM;
    }

    public void setBypassVisible(MBParameter bypassVisible) {
        this.bypassVisible = bypassVisible;
    }
    public MBParameter getBypass() {
        return bypass;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getBypassButtonX() {
        return bypassButtonX;
    }

    public int getBypassButtonY() {
        return bypassButtonY;
    }

    public void setBypassButtonXY(int bypassButtonX, int bypassButtonY) {
        this.bypassButtonX = bypassButtonX;
        this.bypassButtonY = bypassButtonY;
    }

    public Object getLock() {
        return lock;
    }

    public MBParameter getStatus() {
        return status;
    }

    public void setStatus(MBParameter status) {
        this.status = status;
    }

    @FunctionalInterface
    public interface cnd<R> {
        boolean apply();
    }

    public boolean isPassed() {
        return this.passed;
    }

    public void setBypassParam(MBParameter bypass) {
        this.bypass = bypass;
    }

    public void setStatusParam(MBParameter status) {
        this.status = status;
    }

    public void setEnabled(MBParameter enabled) {
        this.enabled = enabled;
    }

    public MBParameter getEnabled() {
        return enabled;
    }

    public void setEnabledConfirm(MBParameter enabledConfirm) {
        this.enabledConfirm = enabledConfirm;
    }

    public MBParameter getEnabledConfirm() {
        return enabledConfirm;
    }
}

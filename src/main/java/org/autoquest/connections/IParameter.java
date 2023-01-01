package org.autoquest.connections;

public interface IParameter {
    void setValue(boolean value);
    void setValue(int value);
    void setValue(float value);
    boolean getBoolValue();
    int getInt32Value();
    float getFloatValue();
    int getChannelNumber();
    void setChannelNumber(int channelNumber);

}

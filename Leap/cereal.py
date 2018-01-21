import serial
import struct;
ser1 = serial.Serial('COM4', 9600)
while(True):
    ser1.write(struct.pack('>B', int(input())))

'''
Created on Jan 20, 2018

@author: MLH Admin
'''
bpm = 120;
import serial
import struct;
ser1 = serial.Serial('Com3', 9600)
while(True):
    bpm = int(input())
    ser1.write(struct.pack('>B', bpm));




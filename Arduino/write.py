import serial
ser = serial.Serial("COM4")
#ser.close()
try:
    while(True):
        time.sleep(.2)
        ser.write(bytearray([50]))
except:
    ser.close()

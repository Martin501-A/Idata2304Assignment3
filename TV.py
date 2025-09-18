

class TV:
    """Represents a TV"""

    def __init__(self, channels):
        """Constructs a TV server with a host ip address"""
        self.powerStatus = False
        self.createChannels(channels)

    def createChannels(self, channels):
        """Creates N channels"""
        self.channels = []
        for i in range(1,channels+1):
            self.channels.append(i)
        self.activeChannel = self.channels[0]

    def isOn(self):
        """Checks if the television is on"""
        return self.powerStatus

    def turnOff(self):
        """Turns off the television"""
        self.powerStatus = False

    def turnOn(self):
        """Turns on the television"""
        self.powerStatus = True

    def getChannels(self):
        """Gets the channels"""
        return self.channels

    def getActiveChannel(self):
        """Gets the current channel"""
        return self.activeChannel

    def setActiveChannel(self, channel):
        if channel < len(self.channels):
            self.activeChannel = self.channels[channel]
            return 1
        return 0
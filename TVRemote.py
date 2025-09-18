class TVRemote:

    def __init__(self):
        self.currentChannel = 0

    def setCurrentChannel(self, channel):
        self.currentChannel = channel

    def getCurrentChannel(self):
        return self.currentChannel

    def power(self):
        return True

    def channelUp(self):
        return True

    def channelDown(self):
        return True
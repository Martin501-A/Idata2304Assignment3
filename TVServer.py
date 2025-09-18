import threading
from Server import Server
from TV import TV

class TVServer(Server, TV):
    """Represents """

    def __init__(self, host, channel):
        Server.__init__(self, host)
        TV.__init__(self, channel)

    def handleClient(self, connection):
        """Handles the client connection for the TV from a client"""



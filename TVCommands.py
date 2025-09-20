from enum import Enum

class FirstCommand(Enum):
    TURN = "TURN"
    SWITCH = "SWITCH"
    GET = "GET"
    INVALID = False

class SecondCommand(Enum):
    ON = "ON"
    OFF = "OFF"
    UP = "UP"
    DOWN = "DOWN"
    CHANNEL = "CHANNEL"
    CHANNELS = "CHANNELS"
    INVALID = False

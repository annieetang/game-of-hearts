class Initiator():
    def __init__(self, trustworthy):
        self.trustworthy = trustworthy
    
    def isTrustworthy(self):
        return self.trustworthy

class Recipient():
    def __init__(self, trusting):
        self.trusting = trusting
    
    def isTrusting(self):
        return self.trusting

    def updateTrust(self, value):
        self.trusting += value
        if self.trusting > 1: 
            self.trusting = 1
        elif self.trusting < 0:
            self.trusting = 0
            self.trusting = 0


from .. import db
from .. models.OrderItems import OrderItems

class OrderItemsController():

    def __init__(self, orderId, productId):
        self.orderId = orderId
        self.productId = productId
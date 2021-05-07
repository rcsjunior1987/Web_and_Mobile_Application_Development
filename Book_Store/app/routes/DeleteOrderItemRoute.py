from .. import render_template, session, sessionKey, redirect, url_for
from ..controllers.OrderController import OrderController

class DeleteOrderItemRoute():

    def __init__(self, orderId, productId):
        self.orderId = orderId
        self.productId = productId

    def getRenderTemplate(self):
        orderId=None
        if sessionKey in session.keys():
            orderId = session[sessionKey]
            order = OrderController(orderId, self.productId).deleteOrderItem()
            totalPrice = OrderController(order.id, '').calcTotalPrice(order)

        return render_template('order.html', order=order, totalPrice=totalPrice)

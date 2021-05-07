from .. import render_template, session, sessionKey
from ..controllers.OrderController import OrderController

class OrderRoute():

    def __init__(self, orderId, idProduct):
        self.orderId = orderId
        self.idProduct = idProduct

    def getRenderTemplate(self):

        order = None
        if sessionKey in session.keys():
            order = OrderController(session[sessionKey], '').getOrderById()
            
        if order is None:
            order = OrderController(None, self.idProduct).createNewOrder()
            session[sessionKey] = order.id

        if order is not None:
            OrderController(order.id, self.idProduct).insertOrderItems(order)
            totalPrice = OrderController(order.id, '').calcTotalPrice(order)

        return render_template('order.html', order=order, totalPrice=totalPrice)


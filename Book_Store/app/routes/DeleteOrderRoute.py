from .. import session, sessionKey, redirect, url_for
from ..controllers.OrderController import OrderController

class DeleteOrderRoute():

    def getRenderTemplate(self):
        orderId=None
        if sessionKey in session.keys():
            orderId = session[sessionKey]
            isDeleted = OrderController(orderId, '').deleteAllOrderItems()
            
            if isDeleted==True:
                OrderController(orderId, '').deleteOrder()
                del session[sessionKey]

        return redirect(url_for('main.index'))

from .. import render_template, session, sessionKey, redirect, url_for
from ..controllers.OrderController import OrderController
from .. import CheckOutForm
from datetime import datetime

class CheckOutRoute():

    def getRenderTemplate(self):
        form = CheckOutForm()
        if sessionKey in session.keys():
            orderId = session[sessionKey]

            order = OrderController(orderId, '').getOrderById()
            if form.validate_on_submit():
                order.status = True
                order.firstname = form.firstname.data
                order.surname = form.surname.data
                order.email = form.email.data
                order.phone = form.phone.data
                order.totalcost = OrderController(orderId, '').calcTotalPrice(order)
                order.date = datetime.now()

                if OrderController(orderId, '').checkOutOrder(order):
                    del session[sessionKey]
                    return redirect(url_for('main.home'))
                else:
                    return 'There was an issue completing your order'
                    
        return render_template('checkout.html', form=form)
from .. import db, Flask
from datetime import datetime
from .. models.Order import Order, OrderItems
from ..models.Product import Product

class OrderController():

    def __init__(self, orderId, productId):
        self.orderId = orderId
        self.productId = productId

    def getOrderById(self):
        return Order.query.get(self.orderId)

    def createNewOrder(self):
        order = Order(status=False
                    , firstname=''
                    , surname=''
                    , email=''
                    , phone=''
                    , totalCost=0
                    , date=datetime.now())

        try:
            db.session.add(order)
            db.session.commit()
        except:
            print('failed at creating a new order')
            order = None
        return order

    def insertOrderItems(self, order):
        product = Product.query.get(self.productId)

        if product not in order.products:
            try:
                order.products.append(product)
                db.session.commit()
            except:
                return 'There was an issue adding the item to your basket'
        else:
            Flask('Product already in basket')
            #return redirect(url_for('main.order'))

        #else:
        #    flash('Product already in basket')
        #    return redirect(url_for('main.order'))

        #if (len(order.products) == 0):
        #    if self.productId in session:
        #        flash('No products on the basket!')
        #        return redirect(url_for('main.home'))
        # products = Product.query.order_by(Product.name).all()

    def checkOutOrder(self, order):
        try:
            db.session.commit()            
            return True
        except:
            return False

    def calcTotalPrice(self, order):
        totalPrice = 0
        if order is not None:
            for product in order.products:
                totalPrice = totalPrice + product.price
        return totalPrice

    def deleteOrderItem(self):                     
        order = Order.query.get_or_404(self.orderId)
        productToBeDeleted = Product.query.get(self.productId)
        try:
            order.products.remove(productToBeDeleted)
            db.session.commit()            
        except:
            return 'Problem deleting item from order'
        return order

    def deleteAllOrderItems(self):
        try:
            deleteOrderItems = OrderItems.delete().where(OrderItems.c.orderId==self.orderId)
            db.session.execute(deleteOrderItems)
            db.session.commit()
            return True
        except:
            return False

    def deleteOrder(self):
        try:
            order = Order.query.get_or_404(self.orderId)
            db.session.delete(order)
            db.session.commit()
        except:
            return 'Problem deleting the order'
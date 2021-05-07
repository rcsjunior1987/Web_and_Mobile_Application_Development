from .. import db

OrderItems = db.Table('orderItems'
                     , db.Column('orderId'
                                , db.Integer
                                , db.ForeignKey('orders.id')
                                , nullable=False)
                     , db.Column('productId'
                                 , db.Integer
                                 , db.ForeignKey('products.id')
                                 , nullable=False)
                     , db.PrimaryKeyConstraint('orderId'
                                             , 'productId'))

class Order(db.Model):

    #----------------  DEFINING THE NAME OF THE TABLE ----------------#
    __tablename__ = 'orders'

    #---------------- DEFINING THE  ATTRIBUTES ----------------#
    id = db.Column(db.Integer
                 , primary_key=True)

    status = db.Column(db.Boolean
                     , default=False)

    firstname = db.Column(db.String(64))

    surname = db.Column(db.String(64))

    email = db.Column(db.String(128))

    phone = db.Column(db.String(32))

    totalCost = db.Column(db.Float)

    date = db.Column(db.DateTime)

    products = db.relationship("Product"
                              , secondary=OrderItems
                              , backref="orders")

    def __repr__(self):
        str = ("   id: {}"
             + ", Status: {}"
             + ", Firstname: {}"
             + ", Surname: {}"
             + ", Email: {}"
             + ", Phone: {}"
             + ", Date: {}"
             + ", Products: {}"
             + ", Total Cost: {}\n")

        str = str.format(self.id
                       , self.status
                       , self.firstname
                       , self.surname
                       , self.email
                       , self.phone
                       , self.date
                       , self.products
                       , self.totalCost)
                       
        return str

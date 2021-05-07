from .. import db

class Product(db.Model):

    #----------------  DEFINING THE NAME OF THE TABLE ----------------#
    __tablename__ = 'products'

    #---------------- DEFINING THE  ATTRIBUTES ----------------#
    id = db.Column(db.Integer
                 , primary_key=True)
                     
    name = db.Column(db.String(64)
                   , unique=True)

    image = db.Column(db.String(60)
                    , nullable=False
                    , default='most_popular1.jpg')

    price = db.Column(db.Float)

    emptystar = db.Column(db.Integer)

    halfstar = db.Column(db.Boolean
                       , default=False)

    fullstar = db.Column(db.Integer)

    numreviews = db.Column(db.Integer)
    
    shortdescription = db.Column(db.String(60)
                               , nullable=False
                               , default='')

    fulldescription = db.Column(db.String(500)
                              , nullable=False
                              , default='')

    specification = db.Column(db.String(500)
                            , nullable=False
                            , default='')

    def __repr__(self):
        str = ("  Id: {}"
            + ", Name: {}"
            + ", Price: {}"
            + ", Image: {}"
            + ", NumOfReviews: {}"
            + ", ShortDescription: {}"
            + ", FullDescription: {}"
            + ", Specification: {} \n")
    
        str = str.format(self.id
                       , self.name
                       , self.price
                       , self.image
                       , self.numreviews
                       , self.shortdescription
                       , self.fulldescription
                       , self.specification)

        return str
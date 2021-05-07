from ..models.Product import Product

class ProductController():

    __listAllProducts__ = []

    def __init__(self):
        self.__listAllProducts__ = Product.query.order_by(Product.numreviews).all() 

    def getListAllMostPopularProducts(self):
        mostpopular = []

        for x in range(4):
            mostpopular.append(self.__listAllProducts__[x])
        return mostpopular

    def getProductById(productId):
        return Product.query.get(productId)

    def getListProductsByName(productName):
        return Product.query.filter(Product.name.like(productName)).all()

    def sortByNumReviews():
        return Product.query.order_by(Product.numreviews.desc()).all()

    def sortByPrice():
        return Product.query.order_by(Product.price).all()

    def sortByReview():
        return Product.query.order_by(Product.fullstar.desc()).all()
        
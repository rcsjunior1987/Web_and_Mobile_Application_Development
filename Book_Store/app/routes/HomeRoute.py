from .. import render_template
from ..controllers.ProductController import ProductController

class HomeRoute():

    def __init__(self) :
        self.listAllMostPopularProducts = ProductController().getListAllMostPopularProducts()

    def getRenderTemplate(self) :
        return render_template('home.html', products=self.listAllMostPopularProducts)
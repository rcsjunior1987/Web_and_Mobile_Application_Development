from .. import db, render_template

from ..controllers.ProductController import ProductController

class ProductRoute():

    def getProductById(productId):
        product = ProductController.getProductById(productId)
        return render_template('product.html', product=product)
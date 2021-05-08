# import flask - from the package import class
from flask import Blueprint, Flask, render_template, request, session,redirect, url_for
from flask_bootstrap import Bootstrap
from flask_sqlalchemy import SQLAlchemy
from .forms.CheckOutForm import CheckOutForm
from .forms.SupportForm import SupportForm

db = SQLAlchemy()
app = Flask(__name__)

sessionKey = 'orderId'

def create_app():
    app.debug = True
    app.secret_key = 'BetterSecretNeeded123'

    # set the app configuration data
    app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///dataBase.sqlite'

    # initialize db with flask app
    db.init_app(app)

    bootstrap = Bootstrap(app)

    from . import views
    app.register_blueprint(views.bp) 
      
    return app

@app.errorhandler(404)
def not_found(e):
    return render_template("404.html")

@app.errorhandler(500)
def internal_error(e):
    return render_template("500.html")

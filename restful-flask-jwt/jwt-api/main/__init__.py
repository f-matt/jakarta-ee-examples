from flask import Flask
from flask_restful import Api
from flask_jwt_extended import JWTManager
from pathlib import Path
import configparser

BASE_DIR = Path(__file__).resolve().parent.parent

config = configparser.ConfigParser()
config.read(f'{BASE_DIR}/.env')

app = Flask(__name__)
app.config['JWT_SECRET_KEY'] = config['KEYS']['SECRET_KEY']
api = Api(app)
jwt = JWTManager(app)

from main.views import IndexEndpoint, JwtEndpoint, LoginEndpoint

api.add_resource(IndexEndpoint, '/')
api.add_resource(JwtEndpoint, '/restricted')
api.add_resource(LoginEndpoint, '/login')

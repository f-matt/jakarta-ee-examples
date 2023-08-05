from flask import Flask
from pathlib import Path
import os
import configparser

BASE_DIR = Path(__file__).resolve().parent.parent

config = configparser.ConfigParser()
config.read(f'{BASE_DIR}/.env')

SECRET_KEY = config['KEYS']['SECRET_KEY']

app = Flask(__name__)

from main.views import *

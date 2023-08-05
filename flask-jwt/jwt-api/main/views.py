from flask import jsonify
from helpers.helper import token_required
from main import app

@app.route("/")
def index():
    return jsonify({"message":"online"})

@app.route("/restricted")
@token_required
def restricted():
    return jsonify({"message":"Access granted"})

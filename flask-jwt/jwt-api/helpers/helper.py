from base64 import b64decode
from functools import wraps
from flask import request, jsonify
from main import app, SECRET_KEY

import jwt


def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        if not request.headers.get("Authorization"):
            return jsonify({"message": "Authorization header not found"}), 401
        token = request.headers.get("Authorization").split(" ")[1]
        print (token)
        if not token:
            return jsonify({"message": "Token not found"}), 401
        try:
            print (SECRET_KEY)
            data = jwt.decode(token, SECRET_KEY, algorithms=["HS256"])
        except Exception as e:
            print (e)
            return jsonify({"message": "Invalid token"}), 401
        return f(*args, **kwargs)
    return decorated
